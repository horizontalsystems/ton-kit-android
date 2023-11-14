package io.horizontalsystems.tonkit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TonTransactionDao {
    @Query("SELECT * FROM TonTransaction ORDER BY timestamp DESC, lt DESC LIMIT 0, 1")
    fun getLatest(): TonTransaction?

    @Query("SELECT * FROM TonTransaction ORDER BY timestamp ASC, lt ASC LIMIT 0, 1")
    fun getEarliest(): TonTransaction?

    @Query("SELECT * FROM TonTransaction ORDER BY timestamp DESC, lt DESC LIMIT 0, :limit")
    fun getAll(limit: Long): List<TonTransaction>

    @Query("SELECT * FROM TonTransaction WHERE type = :type ORDER BY timestamp DESC, lt DESC LIMIT 0, :limit")
    fun getAllByType(type: TransactionType, limit: Long): List<TonTransaction>

    @Query("SELECT * FROM TonTransaction WHERE hash = :hash")
    fun getByHash(hash: String): TonTransaction?

    @Query("SELECT * FROM TonTransaction WHERE timestamp < :timestamp OR (timestamp = :timestamp AND lt < :lt) ORDER BY timestamp DESC LIMIT 0, :limit")
    fun selectEarlierThan(timestamp: Long, lt: Long, limit: Long): List<TonTransaction>

    @Query("SELECT * FROM TonTransaction WHERE type = :type AND timestamp < :timestamp OR (timestamp = :timestamp AND lt < :lt) ORDER BY timestamp DESC LIMIT 0, :limit")
    fun selectEarlierThanByType(timestamp: Long, lt: Long, type: TransactionType, limit: Long): List<TonTransaction>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(transactions: List<TonTransaction>)
}
