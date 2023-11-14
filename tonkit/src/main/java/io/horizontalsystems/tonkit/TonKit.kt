package io.horizontalsystems.tonkit

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.AddrStd
import org.ton.block.AddrVar
import org.ton.block.MsgAddressInt
import org.ton.contract.wallet.WalletV4R2Contract
import org.ton.mnemonic.Mnemonic

class TonKit(
    private val transactionManager: TransactionManager,
    private val balanceManager: BalanceManager,
    val receiveAddress: String
) {
    val newTransactionsFlow by transactionManager::newTransactionsFlow
    val balanceFlow by balanceManager::balanceFlow

    private val _syncStateFlow = MutableStateFlow<SyncState>(SyncState.NotSynced(SyncError.NotStarted()))
    val syncStateFlow: StateFlow<SyncState>
        get() = _syncStateFlow

    private val _transactionsSyncStateFlow = MutableStateFlow<SyncState>(SyncState.NotSynced(SyncError.NotStarted()))
    val transactionsSyncStateFlow: StateFlow<SyncState>
        get() = _transactionsSyncStateFlow

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun start() {
        sync()
    }

    private fun sync() {
        coroutineScope.launch {
            balanceManager.sync().collect {
                _syncStateFlow.emit(it)
            }
        }

        coroutineScope.launch {
            transactionManager.sync().collect {
                _transactionsSyncStateFlow.emit(it)
            }
        }
    }

    fun stop() {
        coroutineScope.cancel()
    }

    suspend fun transactions(fromTransactionHash: String?, limit: Long): List<TonTransaction> {
        return transactionManager.transactions(fromTransactionHash, limit)
    }
}

sealed class SyncState {
    class Synced : SyncState()
    class NotSynced(val error: Throwable) : SyncState()
    class Syncing() : SyncState()
}

open class SyncError : Exception() {
    class NotStarted : SyncError()
    class NoNetworkConnection : SyncError()
}

class TonKitFactory() {
    fun create(words: List<String>, passphrase: String, context: Context): TonKit {
        return create(Mnemonic.toSeed(words, passphrase), context)
    }

    fun create(seed: ByteArray, context: Context): TonKit {
        val privateKey = PrivateKeyEd25519(seed)
        val publicKey = privateKey.publicKey()
        val wallet = WalletV4R2Contract(0, publicKey)
        val address = wallet.address
        val receiveAddress = MsgAddressInt.toString(address, bounceable = false)

        val adnl = when (address) {
            is AddrStd -> TonApiAdnl(address)
            is AddrVar -> null
        }

        checkNotNull(adnl)

        val db = Room.databaseBuilder(context, KitDatabase::class.java, "ton-kit").build()
        val transactionManager = TransactionManager(adnl, TransactionStorage(db.tonTransactionDao()))
        val balanceManager = BalanceManager(adnl)

        return TonKit(transactionManager, balanceManager, receiveAddress)
    }

    fun createWatch(address: String, context: Context): TonKit {
        val addrStd = AddrStd.parse(address)
        val receiveAddress = MsgAddressInt.toString(addrStd, bounceable = false)
        val adnl = TonApiAdnl(addrStd)

        val db = Room.databaseBuilder(context, KitDatabase::class.java, "ton-kit").build()
        val transactionManager = TransactionManager(adnl, TransactionStorage(db.tonTransactionDao()))
        val balanceManager = BalanceManager(adnl)

        return TonKit(transactionManager, balanceManager, receiveAddress)
    }
}
