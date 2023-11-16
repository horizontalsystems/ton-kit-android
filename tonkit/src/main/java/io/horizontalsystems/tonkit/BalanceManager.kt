package io.horizontalsystems.tonkit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

class BalanceManager(private val adnl: TonApiAdnl) {

    private val _balanceFlow = MutableStateFlow<BigDecimal?>(null)
    val balanceFlow: Flow<BigDecimal?>
        get() = _balanceFlow.asStateFlow()

    suspend fun sync() = flow {
        emit(SyncState.Syncing())

        try {
            _balanceFlow.update { adnl.getBalance() }
            emit(SyncState.Synced())
        } catch (t: Throwable) {
            emit(SyncState.NotSynced(t))
        }
    }
}
