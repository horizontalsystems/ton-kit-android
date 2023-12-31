package io.horizontalsystems.tonkit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.ton.api.liteclient.config.LiteClientConfigGlobal
import org.ton.bitstring.BitString
import org.ton.block.AccountInfo
import org.ton.block.AddrStd
import org.ton.block.CommonMsgInfo
import org.ton.block.ExtInMsgInfo
import org.ton.block.ExtOutMsgInfo
import org.ton.block.IntMsgInfo
import org.ton.block.MsgAddressInt
import org.ton.lite.api.LiteApi
import org.ton.lite.api.exception.LiteServerUnknownException
import org.ton.lite.client.LiteClient
import org.ton.lite.client.internal.TransactionId
import org.ton.lite.client.internal.TransactionInfo
import java.math.BigDecimal
import java.net.URL

class TonApiAdnl(private val addrStd: AddrStd) {
    private val json = Json {
        ignoreUnknownKeys = true
    }
    private var liteClient: LiteClient? = null

    init {
        try {
            val configJson = runBlocking(Dispatchers.Default) {
                URL("https://ton.org/global.config.json").readText()
            }
            val config = json.decodeFromString(LiteClientConfigGlobal.serializer(), configJson)
            liteClient = LiteClient(Dispatchers.Default, config)
        } catch (e: Exception) {

        }
    }

    suspend fun getBalance(): BigDecimal? {
        val fullAccountState = getFullAccountStateOrNull() ?: return null
        val account = fullAccountState.account.value
        return if (account is AccountInfo) {
            BigDecimal(account.storage.balance.coins.toString())
        } else {
            null
        }
    }

    suspend fun transactions(transactionHash: String?, lt: Long?, limit: Int): List<TonTransaction> {
        val transactionId = when {
            transactionHash != null && lt != null -> TransactionId(BitString(transactionHash), lt)
            else -> getFullAccountStateOrNull()?.lastTransactionId
        } ?: return listOf()

        try {
            val transactions = liteClient?.getTransactions(addrStd, transactionId, limit)
            return transactions?.map { createTonTransaction(it) } ?: listOf()
        } catch (e: LiteServerUnknownException) {
            return listOf()
        }
    }

    suspend fun getFullAccountStateOrNull() = try {
        liteClient?.getAccountState(addrStd)
    } catch (e: Exception) {
        null
    }

    private fun createTonTransaction(info: TransactionInfo): TonTransaction {
        val inMsg = info.transaction.value.r1.value.inMsg.value?.value?.info
        val outMsgs = info.transaction.value.r1.value.outMsgs

        val transactionType: TransactionType
        val msgInfo: CommonMsgInfo?
        when {
            outMsgs.count() == 1 -> {
                val outMsg = outMsgs.first().second.value.info
                msgInfo = outMsg
                transactionType = TransactionType.Outgoing
            }

            inMsg != null -> {
                msgInfo = inMsg
                transactionType = TransactionType.Incoming
            }

            else -> {
                msgInfo = null
                transactionType = TransactionType.Outgoing
            }
        }

        return TonTransaction(
            hash = info.id.hash.toHex(),
            lt = info.id.lt,
            timestamp = info.transaction.value.now.toLong(),
            value = getValue(msgInfo),
            type = transactionType,
            src = getSrc(msgInfo),
            dest = getDest(msgInfo),
        )
    }

    private fun getValue(msgInfo: CommonMsgInfo?) = when (msgInfo) {
        is IntMsgInfo -> BigDecimal(msgInfo.value.coins.toString())
        is ExtInMsgInfo -> null
        is ExtOutMsgInfo -> null
        null -> null
    }

    private fun getSrc(msgInfo: CommonMsgInfo?) = when (msgInfo) {
        is IntMsgInfo -> MsgAddressInt.toString(msgInfo.src, bounceable = false)
        is ExtInMsgInfo -> null
        is ExtOutMsgInfo -> null
        null -> null
    }

    private fun getDest(msgInfo: CommonMsgInfo?) = when (msgInfo) {
        is IntMsgInfo -> MsgAddressInt.toString(msgInfo.dest, bounceable = false)
        is ExtInMsgInfo -> null
        is ExtOutMsgInfo -> null
        null -> null
    }

    suspend fun getLatestTransactionHash(): String? {
        return getFullAccountStateOrNull()?.lastTransactionId?.hash?.toHex()
    }

    fun getLiteApi(): LiteApi? {
        return liteClient?.liteApi
    }
}
