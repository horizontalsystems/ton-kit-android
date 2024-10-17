package io.horizontalsystems.tonkit.core

import android.content.Context
import com.tonapps.wallet.data.core.entity.SendRequestEntity
import io.horizontalsystems.tonkit.Address
import io.horizontalsystems.tonkit.FriendlyAddress
import io.horizontalsystems.tonkit.api.TonApi
import io.horizontalsystems.tonkit.api.TonApiListener
import io.horizontalsystems.tonkit.models.Event
import io.horizontalsystems.tonkit.models.EventInfo
import io.horizontalsystems.tonkit.models.Jetton
import io.horizontalsystems.tonkit.models.Network
import io.horizontalsystems.tonkit.models.TagQuery
import io.horizontalsystems.tonkit.models.TagToken
import io.horizontalsystems.tonkit.storage.KitDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.math.BigInteger

class TonKit(
    private val address: Address,
    private val apiListener: TonApiListener,
    private val accountManager: AccountManager,
    private val jettonManager: JettonManager,
    private val eventManager: EventManager,
    private val transactionSender: TransactionSender?,
    val network: Network,
    private val transactionSigner: TransactionSigner,
) {
    val receiveAddress get() = address

    val syncStateFlow by accountManager::syncStateFlow
    val accountFlow by accountManager::accountFlow
    val jettonSyncStateFlow by jettonManager::syncStateFlow
    val jettonBalanceMapFlow by jettonManager::jettonBalanceMapFlow
    val eventSyncStateFlow by eventManager::syncStateFlow

    val account get() = accountFlow.value
    val jettonBalanceMap get() = jettonBalanceMapFlow.value

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    init {
        coroutineScope.launch {
            apiListener.transactionFlow.collect {
                handleEvent(it)
            }
        }
    }

    suspend fun refresh() {
        sync()
    }

    suspend fun start() = coroutineScope {
        listOf(
            async {
                sync()
            },
            async {
                startListener()
            }
        ).awaitAll()
    }

    fun stop() {
        this.stopListener()
    }

    private suspend fun handleEvent(eventId: String) {
        repeat(3) {
            delay(5000)
            if (eventManager.isEventCompleted(eventId)) {
                return
            }

            sync()
        }
    }

    fun events(tagQuery: TagQuery, beforeLt: Long? = null, limit: Int? = null): List<Event> {
        return eventManager.events(tagQuery, beforeLt, limit)
    }

    fun eventFlow(tagQuery: TagQuery): Flow<EventInfo> {
        return eventManager.eventFlow(tagQuery)
    }

    fun tagTokens(): List<TagToken> {
        return eventManager.tagTokens()
    }

    suspend fun estimateFee(
        recipient: FriendlyAddress,
        amount: SendAmount,
        comment: String?,
    ): BigInteger {
        return transactionSender?.estimateFee(recipient, amount, comment)
            ?: throw WalletError.WatchOnly
    }

    suspend fun estimateFee(
        jettonWallet: Address,
        recipient: FriendlyAddress,
        amount: BigInteger,
        comment: String?,
    ): BigInteger {
        return transactionSender?.estimateFee(jettonWallet, recipient, amount, comment)
            ?: throw WalletError.WatchOnly
    }

    suspend fun send(recipient: FriendlyAddress, amount: SendAmount, comment: String?) {
        transactionSender?.send(recipient, amount, comment) ?: throw WalletError.WatchOnly
    }

    suspend fun send(
        jettonWallet: Address,
        recipient: FriendlyAddress,
        amount: BigInteger,
        comment: String?,
    ) {
        transactionSender?.send(jettonWallet, recipient, amount, comment)
            ?: throw WalletError.WatchOnly
    }

    suspend fun send(boc: String) {
        transactionSender?.send(boc) ?: throw WalletError.WatchOnly
    }

    fun startListener() {
        apiListener.start(address = address)
    }

    fun stopListener() {
        apiListener.stop()
    }

    suspend fun sync() = coroutineScope {
        listOf(
            async {
                accountManager.sync()
            },
            async {
                jettonManager.sync()
            },
            async {
                eventManager.sync()
            },
        ).awaitAll()
    }

    suspend fun sign(request: SendRequestEntity, tonWallet: TonWallet): String {
        check(tonWallet is TonWallet.FullAccess)

        return transactionSigner.sign(request, tonWallet)
    }

    suspend fun getDetails(request: SendRequestEntity, tonWallet: TonWallet): Event {
        check(tonWallet is TonWallet.FullAccess)

        return transactionSigner.getDetails(request, tonWallet)
    }

//    enum WalletVersion {
//        case v3
//        case v4
//        case v5
//    }

    sealed class SyncError : Error() {
        data object NotStarted : SyncError() {
            override val message = "Not Started"
        }
    }

    sealed class WalletError : Error() {
        data object WatchOnly : WalletError()
    }

//    enum SendAmount {
//        case amount(value: BigUInt)
//        case max
//    }

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            val logging = HttpLoggingInterceptor()
            logging.level = Level.NONE
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
        }

        fun getInstance(
            tonWallet: TonWallet,
            network: Network,
            context: Context,
            walletId: String,
        ): TonKit {
            val address = tonWallet.address
            val privateKey = tonWallet.privateKey

            val database = KitDatabase.getInstance(context, "${walletId}-${network.name}")

            val api = getTonApi(network)
            val transactionSigner = getTransactionSigner(api)

            val accountManager = AccountManager(address, api, database.accountDao())
            val jettonManager = JettonManager(address, api, database.jettonDao())
            val eventManager = EventManager(address, api, database.eventDao())

            val transactionSender = privateKey?.let {
                TransactionSender(api, address, it)
            }

            val apiListener = TonApiListener(network, okHttpClient)

            return TonKit(
                address,
                apiListener,
                accountManager,
                jettonManager,
                eventManager,
                transactionSender,
                network,
                transactionSigner
            )
        }

        fun getTonApi(network: Network) = TonApi(network, okHttpClient)
        fun getTransactionSigner(api: TonApi) = TransactionSigner(api)

        suspend fun getJetton(network: Network, address: Address): Jetton {
            return getTonApi(network).getJettonInfo(address)
        }

        fun validateAddress(address: String) {
            Address.parse(address)
        }
    }

    sealed class SendAmount {
        data class Amount(val value: BigInteger) : SendAmount()
        data object Max : SendAmount()
    }

}