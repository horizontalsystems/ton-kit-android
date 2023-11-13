package io.horizontalsystems.tonkit

data class TonTransaction(
    val hash: String,
    val lt: Long,
    val timestamp: Long,
    val value_: String?,
)
