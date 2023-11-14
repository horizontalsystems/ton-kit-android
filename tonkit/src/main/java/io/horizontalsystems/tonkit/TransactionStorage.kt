package io.horizontalsystems.tonkit

class TransactionStorage(private val transactionDao: TonTransactionDao) {
    fun getLatestTransaction(): TonTransaction? {
        return transactionDao.getLatest()
    }

    fun getEarliestTransaction(): TonTransaction? {
        return transactionDao.getEarliest()
    }

    fun getTransactions(
        fromTransactionHash: String?,
        type: TransactionType?,
        limit: Long,
    ) = when (type) {
        null -> getTransactions(fromTransactionHash, limit)
        else -> getTransactionsByType(fromTransactionHash, type, limit)
    }

    private fun getTransactions(
        fromTransactionHash: String?,
        limit: Long,
    ): List<TonTransaction> {
        if (fromTransactionHash == null) {
            return transactionDao.getAll(limit)
        } else {
            val fromTransaction = transactionDao.getByHash(fromTransactionHash) ?: return listOf()
            return transactionDao.selectEarlierThan(
                fromTransaction.timestamp,
                fromTransaction.lt,
                limit
            )
        }
    }

    private fun getTransactionsByType(
        fromTransactionHash: String?,
        type: TransactionType,
        limit: Long,
    ): List<TonTransaction> {
        if (fromTransactionHash == null) {
            return transactionDao.getAllByType(type, limit)
        } else {
            val fromTransaction = transactionDao.getByHash(fromTransactionHash) ?: return listOf()
            return transactionDao.selectEarlierThanByType(
                fromTransaction.timestamp,
                fromTransaction.lt,
                type,
                limit
            )
        }
    }

    fun add(transactions: List<TonTransaction>) {
        transactionDao.insertAll(transactions)
    }
}
