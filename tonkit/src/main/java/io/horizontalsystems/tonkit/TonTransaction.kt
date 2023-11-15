package io.horizontalsystems.tonkit

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
data class TonTransaction(
    @PrimaryKey
    val hash: String,
    val lt: Long,
    val timestamp: Long,
    val value: BigDecimal?,
    val type: TransactionType?,
    val src: String?,
    val dest: String?,
)

enum class TransactionType {
    Incoming, Outgoing
}
