package io.horizontalsystems.tonkit

import java.math.BigDecimal

class TonKit(
    private val transactionManager: TransactionManager,
    private val balanceManager: BalanceManager,
    val receiveAddress: String,
    private val syncer: Syncer,
    private val transactionSender: TransactionSender?
) {
    val newTransactionsFlow by transactionManager::newTransactionsFlow
    val balanceFlow by balanceManager::balanceFlow

    val balanceSyncStateFlow by syncer::balanceSyncStateFlow
    val transactionsSyncStateFlow by syncer::transactionsSyncStateFlow

    fun start() {
        syncer.start()
    }

    fun stop() {
        syncer.stop()
    }

    suspend fun send(dest: String, amount: BigDecimal) {
        checkNotNull(transactionSender)

        transactionSender.send(dest, amount)
    }

    suspend fun transactions(fromTransactionHash: String?, type: TransactionType?, limit: Long): List<TonTransaction> {
        return transactionManager.transactions(fromTransactionHash, type, limit)
    }
}
