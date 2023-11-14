package io.horizontalsystems.tonkit

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val words = "used ugly meat glad balance divorce inner artwork hire invest already piano".split(" ")
    private val passphrase = ""
    private val watchAddress = "UQBpAeJL-VSLCigCsrgGQHCLeiEBdAuZBlbrrUGI4BVQJoPM"

//    private val tonKit = TonKitFactory.create(words, passphrase)
    private val tonKit = TonKitFactory().createWatch(watchAddress)

    val address = tonKit.receiveAddress

    private var balance: String? = null
    private var syncState = tonKit.syncStateFlow.value
    private var txSyncState = tonKit.transactionsSyncStateFlow.value

    var uiState by mutableStateOf(
        MainUiState(
            balance = balance,
            syncState = syncState,
            txSyncState = txSyncState,
        )
    )
        private set

    var transactionList: List<TonTransaction>? by mutableStateOf(null)
        private set

    init {
        tonKit.start()
        viewModelScope.launch {
            tonKit.balanceFlow.collect {
                updateBalance(it)
            }
        }
        viewModelScope.launch {
            tonKit.syncStateFlow.collect {
                updateSyncState(it)
            }
        }
        viewModelScope.launch {
            tonKit.transactionsSyncStateFlow.collect {
                updateTxSyncState(it)
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            tonKit.newTransactionsFlow.collect {
                transactionList = tonKit.transactions(null, 10)
            }
        }
    }

    private fun updateSyncState(syncState: SyncState) {
        this.syncState = syncState

        emitState()
    }

    private fun updateTxSyncState(syncState: SyncState) {
        txSyncState = syncState

        emitState()
    }

    override fun onCleared() {
        tonKit.stop()
    }

    private fun updateBalance(balance: String?) {
        this.balance = balance

        emitState()
    }

    private fun emitState() {
        viewModelScope.launch {
            uiState = MainUiState(
                balance = balance,
                syncState = syncState,
                txSyncState = txSyncState
            )
        }
    }
}

data class MainUiState(
    val balance: String?,
    val syncState: SyncState,
    val txSyncState: SyncState,
)

fun SyncState.toStr() = when (this) {
    is SyncState.NotSynced -> "NotSynced ${error.javaClass.simpleName} - message: ${error.message}"
    is SyncState.Synced -> "Synced"
    is SyncState.Syncing -> "Syncing"
}