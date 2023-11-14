package io.horizontalsystems.tonkit

class TransactionStorage(private val transactionDao: TonTransactionDao) {
    fun getLatestTransaction(): TonTransaction? {
        return transactionDao.getLatest()
    }

    fun getEarliestTransaction(): TonTransaction? {
        return transactionDao.getEarliest()
    }

    fun getTransactions(fromTransactionHash: String?, limit: Long): List<TonTransaction> {
        if (fromTransactionHash == null) {
            return transactionDao.getAll(limit)
        } else {
            val fromTransaction = transactionDao.getByHash(fromTransactionHash) ?: return listOf()
            return transactionDao.selectEarlierThan(fromTransaction.timestamp, fromTransaction.lt, limit)
        }
    }

    fun add(transactions: List<TonTransaction>) {
        transactionDao.insertAll(transactions)
    }
}
