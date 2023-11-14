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
    private val tonKit = TonKitFactory().createWatch(watchAddress, application)

    val address = tonKit.receiveAddress

    private var balance: String? = null
    private var syncState = tonKit.balanceSyncStateFlow.value
    private var txSyncState = tonKit.transactionsSyncStateFlow.value
    private var transactionList: List<TonTransaction>? = null

    var uiState by mutableStateOf(
        MainUiState(
            balance = balance,
            syncState = syncState,
            txSyncState = txSyncState,
            transactionList = transactionList
        )
    )
        private set

    init {
        tonKit.start()
        viewModelScope.launch {
            tonKit.balanceFlow.collect {
                updateBalance(it)
            }
        }
        viewModelScope.launch {
            tonKit.balanceSyncStateFlow.collect {
                updateSyncState(it)
            }
        }
        viewModelScope.launch {
            tonKit.transactionsSyncStateFlow.collect {
                updateTxSyncState(it)
            }
        }

        loadNextTransactionsPage()

        viewModelScope.launch(Dispatchers.IO) {
            tonKit.newTransactionsFlow.collect {
                transactionList = null
                loadNextTransactionsPage()
            }
        }
    }

    fun loadNextTransactionsPage() {
        viewModelScope.launch(Dispatchers.IO) {
            var list = transactionList ?: listOf()
            list += tonKit.transactions(transactionList?.lastOrNull()?.hash, 10)

            transactionList = list

            emitState()
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
                txSyncState = txSyncState,
                transactionList = transactionList
            )
        }
    }
}

data class MainUiState(
    val balance: String?,
    val syncState: SyncState,
    val txSyncState: SyncState,
    val transactionList: List<TonTransaction>?,
)

fun SyncState.toStr() = when (this) {
    is SyncState.NotSynced -> "NotSynced ${error.javaClass.simpleName} - message: ${error.message}"
    is SyncState.Synced -> "Synced"
    is SyncState.Syncing -> "Syncing"
}