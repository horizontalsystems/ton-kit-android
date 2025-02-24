package io.horizontalsystems.tonkit.tonconnect

import android.content.Context
import android.net.Uri
import android.util.Log
import com.tonapps.blockchain.ton.TonNetwork
import com.tonapps.blockchain.ton.contract.WalletVersion
import com.tonapps.blockchain.ton.extensions.base64
import com.tonapps.network.get
import com.tonapps.security.CryptoBox
import com.tonapps.wallet.api.API
import com.tonapps.wallet.data.account.Wallet
import com.tonapps.wallet.data.account.WalletProof
import com.tonapps.wallet.data.account.entities.ProofDomainEntity
import com.tonapps.wallet.data.account.entities.WalletEntity
import com.tonapps.wallet.data.core.entity.SendRequestEntity
import com.tonapps.wallet.data.tonconnect.entities.DAppEntity
import com.tonapps.wallet.data.tonconnect.entities.DAppItemEntity
import com.tonapps.wallet.data.tonconnect.entities.DAppManifestEntity
import com.tonapps.wallet.data.tonconnect.entities.DAppRequestEntity
import com.tonapps.wallet.data.tonconnect.entities.reply.DAppAddressItemEntity
import com.tonapps.wallet.data.tonconnect.entities.reply.DAppEventSuccessEntity
import com.tonapps.wallet.data.tonconnect.entities.reply.DAppProofItemReplySuccess
import com.tonapps.wallet.data.tonconnect.entities.reply.DAppReply
import io.horizontalsystems.tonkit.core.TonWallet
import io.horizontalsystems.tonkit.tonconnect.event.EventHandlerSendTransaction
import io.horizontalsystems.tonkit.tonconnect.event.TonConnectEventManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.json.JSONObject
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.api.pub.PublicKeyEd25519
import org.ton.block.AddrStd
import org.ton.block.StateInit
import org.ton.crypto.base64

class TonConnectKit(
    private val dAppManager: DAppManager,
    private val tonConnectEventManager: TonConnectEventManager,
    private val api: API,
    private val eventHandlerSendTransaction: EventHandlerSendTransaction,
    private val appName: String,
    private val appVersion: String,
) {
    val sendRequestFlow by eventHandlerSendTransaction::sendRequestFlow

    suspend fun reject(request: SendRequestEntity) {
        eventHandlerSendTransaction.reject(request)
    }

    suspend fun badRequest(request: SendRequestEntity) {
        eventHandlerSendTransaction.badRequest(request)
    }

    suspend fun approve(request: SendRequestEntity, boc: String) {
        eventHandlerSendTransaction.approve(request, boc)
    }

    fun readData(uriString: String): DAppRequestEntity {
        val uri = Uri.parse(uriString)
        return DAppRequestEntity(uri)
    }

    fun disconnect(dAppEntity: DAppEntity) {
        val disconnect = object : DAppReply() {
            override fun toJSON(): JSONObject {
                val json = JSONObject()
                json.put("event", "disconnect")
                json.put("id", System.currentTimeMillis())
                json.put("payload", "{ }")
                return json
            }
        }
        tonConnectEventManager.responseToDApp(dAppEntity, disconnect)
        dAppManager.remove(dAppEntity)
    }

    suspend fun connect(
        dAppRequestEntity: DAppRequestEntity,
        manifest: DAppManifestEntity,
        walletId: String,
        tonWallet: TonWallet.FullAccess
    ): DAppEventSuccessEntity {
        val privateKey = tonWallet.privateKey

        val walletEntity = WalletEntity(
            id = walletId,
            publicKey = privateKey.publicKey(),
            type = Wallet.Type.Default,
            version = WalletVersion.V4R2,
            label = Wallet.Label("", "", 0)
        )
        return connect(walletEntity, privateKey, manifest, dAppRequestEntity.id, dAppRequestEntity.payload.items)
    }

    private suspend fun connect(
        wallet: WalletEntity,
        privateKey: PrivateKeyEd25519,
        manifest: DAppManifestEntity,
        clientId: String,
        requestItems: List<DAppItemEntity>,
//        firebaseToken: String?,
    ): DAppEventSuccessEntity = withContext(Dispatchers.IO) {
//        val enablePush = firebaseToken != null
        val app = newApp(manifest, wallet.accountId, wallet.testnet, clientId, wallet.id, false)

        dAppManager.addApp(app)

        val items = createItems(app, wallet, privateKey, requestItems)
        val res = DAppEventSuccessEntity(items, appName, appVersion, wallet.maxMessages)
        send(app, res.toJSON())
//        firebaseToken?.let {
//            subscribePush(wallet, app, it)
//        }
        res.copy()
    }

    suspend fun send(
        app: DAppEntity,
        body: JSONObject,
    ) = send(app, body.toString())

    suspend fun send(
        app: DAppEntity,
        body: String,
    ) = withContext(Dispatchers.IO) {
        Log.i("AAA", "send body: $body")
        val encrypted = app.encrypt(body)
        api.tonconnectSend(app.publicKeyHex, app.clientId, base64(encrypted))
    }

    private fun createItems(
        app: DAppEntity,
        wallet: WalletEntity,
        privateKey: PrivateKeyEd25519,
        items: List<DAppItemEntity>
    ): List<DAppReply> {
        val result = mutableListOf<DAppReply>()
        for (requestItem in items) {
            if (requestItem.name == DAppItemEntity.TON_ADDR) {
                result.add(createAddressItem(
                    accountId = wallet.accountId,
                    testnet = wallet.testnet,
                    publicKey = wallet.publicKey,
                    stateInit = wallet.contract.stateInit
                ))
            } else if (requestItem.name == DAppItemEntity.TON_PROOF) {
                result.add(createProofItem(
                    payload = requestItem.payload ?: "",
                    domain = app.domain,
                    address = wallet.contract.address,
                    privateWalletKey = privateKey,
                ))
            }
        }
        return result
    }

    private fun createProofItem(
        payload: String,
        domain: ProofDomainEntity,
        address: AddrStd,
        privateWalletKey: PrivateKeyEd25519,
    ): DAppProofItemReplySuccess {
        val proof = WalletProof.sign(
            address,
            privateWalletKey,
            payload,
            domain,
        )
        return DAppProofItemReplySuccess(proof = proof)
    }

    private fun createAddressItem(
        accountId: String,
        testnet: Boolean,
        publicKey: PublicKeyEd25519,
        stateInit: StateInit
    ): DAppAddressItemEntity {
        return DAppAddressItemEntity(
            address = accountId,
            network = if (testnet) TonNetwork.TESTNET else TonNetwork.MAINNET,
            walletStateInit = stateInit,
            publicKey = publicKey
        )
    }

    suspend fun newApp(
        manifest: DAppManifestEntity,
        accountId: String,
        testnet: Boolean,
        clientId: String,
        walletId: String,
        enablePush: Boolean,
    ): DAppEntity = withContext(Dispatchers.IO) {
        val keyPair = CryptoBox.keyPair()
        val app = DAppEntity(
            url = manifest.url,
            accountId = accountId,
            testnet = testnet,
            clientId = clientId,
            keyPair = keyPair,
            walletId = walletId,
            enablePush = enablePush,
            manifest = manifest,
        )
//        localDataSource.addApp(app)
//        val oldValue = _appsFlow.value ?: emptyList()
//        _appsFlow.value = oldValue.plus(app)
        app
    }



    fun getManifest(manifestUrl: String): DAppManifestEntity {
        //            val local = localDataSource.getManifest(sourceUrl)
        //            if (local == null) {
        val remote = loadManifest(manifestUrl)
        //                localDataSource.setManifest(sourceUrl, remote)
        return remote
        //            } else {
        //                local
        //            }
    }

    private fun loadManifest(url: String): DAppManifestEntity {
        val response = api.defaultHttpClient.get(url)
        Log.d("APINewLog", "loadManifest: $response")
        return DAppManifestEntity(JSONObject(response))
    }

    fun getDApps(): Flow<List<DAppEntity>> {
        return dAppManager.getAllFlow()
    }

    fun start() {
        tonConnectEventManager.start()
    }

    fun stop() {
        tonConnectEventManager.stop()
    }

    companion object {
        fun getInstance(context: Context, appName: String, appVersion: String): TonConnectKit {
            val api = API()
            val database = TonConnectKitDatabase.getInstance(context, "ton-connect")
            val dAppManager = DAppManager(database.dAppDao())
            val localStorage = LocalStorage(database.keyValueDao())
            val tonConnectEventManager = TonConnectEventManager(dAppManager, api, localStorage)

            val handler = EventHandlerDisconnect(dAppManager, tonConnectEventManager)
            tonConnectEventManager.registerHandler(handler)

            val eventHandlerSendTransaction = EventHandlerSendTransaction(
                tonConnectEventManager,
                database.sendRequestDao()
            )
            tonConnectEventManager.registerHandler(eventHandlerSendTransaction)

            return TonConnectKit(
                dAppManager,
                tonConnectEventManager,
                api,
                eventHandlerSendTransaction,
                appName,
                appVersion
            )
        }
    }
}

sealed class TonConnectError : Error()

class UriError(override val message: String) : TonConnectError()

