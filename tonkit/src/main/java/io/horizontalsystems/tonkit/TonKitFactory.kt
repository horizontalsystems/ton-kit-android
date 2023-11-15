package io.horizontalsystems.tonkit

import android.content.Context
import androidx.room.Room
import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.AddrStd
import org.ton.block.AddrVar
import org.ton.block.MsgAddressInt
import org.ton.contract.wallet.WalletV4R2Contract
import org.ton.mnemonic.Mnemonic

class TonKitFactory {
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

        return createInternal(context, adnl, receiveAddress, wallet, privateKey)
    }

    fun createWatch(address: String, context: Context): TonKit {
        val addrStd = AddrStd.parse(address)
        val receiveAddress = MsgAddressInt.toString(addrStd, bounceable = false)
        val adnl = TonApiAdnl(addrStd)

        return createInternal(context, adnl, receiveAddress, null, null)
    }

    private fun createInternal(
        context: Context,
        adnl: TonApiAdnl,
        receiveAddress: String,
        wallet: WalletV4R2Contract?,
        privateKey: PrivateKeyEd25519?
    ): TonKit {
        val db = Room.databaseBuilder(context, KitDatabase::class.java, "ton-kit").build()
        val transactionStorage = TransactionStorage(db.tonTransactionDao())
        val transactionManager = TransactionManager(adnl, transactionStorage)
        val balanceManager = BalanceManager(adnl)

        val transactionSender = if (wallet != null && privateKey != null) {
            TransactionSender(wallet, adnl, privateKey, )
        } else {
            null
        }

        val syncer = Syncer(transactionManager, balanceManager, ConnectionManager(context))
        return TonKit(transactionManager, balanceManager, receiveAddress, syncer, transactionSender)
    }
}
