package io.horizontalsystems.tonkit.api

import io.horizontalsystems.tonkit.Address
import io.horizontalsystems.tonkit.models.Account
import io.horizontalsystems.tonkit.models.AccountStatus
import io.horizontalsystems.tonkit.models.Action
import io.horizontalsystems.tonkit.models.Event
import io.horizontalsystems.tonkit.models.Jetton
import io.horizontalsystems.tonkit.models.JettonBalance
import io.horizontalsystems.tonkit.models.Network
import io.horizontalsystems.tonkit.models.Network.MainNet
import io.horizontalsystems.tonkit.models.Network.TestNet
import io.tonapi.apis.AccountsApi
import io.tonapi.apis.BlockchainApi
import io.tonapi.apis.EmulationApi
import io.tonapi.apis.JettonsApi
import io.tonapi.apis.LiteServerApi
import io.tonapi.apis.WalletApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.math.BigInteger

class TonApi(network: Network) : IApi {
    private val basePath = when (network) {
        MainNet -> "https://tonapi.io"
        TestNet -> "https://testnet.tonapi.io"
    }

    private val okHttpClient: OkHttpClient by lazy {
        val logging = HttpLoggingInterceptor()
        logging.level = Level.BASIC
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    private val accountsApi = AccountsApi(basePath, okHttpClient)
    private val walletApi = WalletApi(basePath, okHttpClient)
    private val jettonsApi = JettonsApi(basePath, okHttpClient)
    private val liteServerApi = LiteServerApi(basePath, okHttpClient)
    private val emulationApi = EmulationApi(basePath, okHttpClient)
    private val blockchainApi = BlockchainApi(basePath, okHttpClient)

    override suspend fun getAccount(address: Address): Account {
        val account = accountsApi.getAccount(address.toRaw())

        return Account(
            Address.parse(account.address),
            account.balance,
            AccountStatus.fromApi(account.status),
        )
    }

    override suspend fun getAccountJettonBalances(address: Address): List<JettonBalance> {
        val jettonsBalances = accountsApi.getAccountJettonsBalances(address.toRaw())

        return jettonsBalances.balances.map { balance ->
            JettonBalance(
                Jetton.fromPreview(balance.jetton),
                BigInteger(balance.balance),
                Address.parse(balance.walletAddress.address)
            )
        }
    }

    override suspend fun getEvents(
        address: Address,
        beforeLt: Long?,
        startTimestamp: Long?,
        limit: Int,
    ): List<Event> {
        val events = accountsApi.getAccountEvents(
            accountId = address.toRaw(),
            limit = limit,
            beforeLt = beforeLt,
            startDate = startTimestamp
        )

        return events.events.map { event ->
            Event(
                id = event.eventId,
                event.lt,
                event.timestamp,
                event.isScam,
                event.inProgress,
                event.extra,
                event.actions.map { action ->
                    Action.fromApi(action)
                }
            )
        }
    }

    override suspend fun getAccountSeqno(address: Address): Int {
        return walletApi.getAccountSeqno(address.toRaw()).seqno
    }

    override suspend fun getJettonInfo(address: Address): Jetton {
        val jettonInfo = jettonsApi.getJettonInfo(address.toRaw())
        return Jetton.fromJettonInfo(jettonInfo)
    }

    override suspend fun getRawTime(): Int {
        return liteServerApi.getRawTime().time
    }

    override suspend fun estimateFee(boc: String): BigInteger {
        TODO()
//        val result = emulationApi.emulateMessageToWallet(boc)
//        return BigInteger.valueOf(result.trace.transaction.totalFees)
    }

    override suspend fun send(boc: String) {
        TODO()
//        blockchainApi.sendBlockchainMessage(boc)
    }

}