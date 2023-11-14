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

        return create(context, adnl, receiveAddress)
    }

    fun createWatch(address: String, context: Context): TonKit {
        val addrStd = AddrStd.parse(address)
        val receiveAddress = MsgAddressInt.toString(addrStd, bounceable = false)
        val adnl = TonApiAdnl(addrStd)

        return create(context, adnl, receiveAddress)
    }

    private fun create(context: Context, adnl: TonApiAdnl, receiveAddress: String): TonKit {
        val db = Room.databaseBuilder(context, KitDatabase::class.java, "ton-kit").build()
        val transactionManager =
            TransactionManager(adnl, TransactionStorage(db.tonTransactionDao()))
        val balanceManager = BalanceManager(adnl)

        val syncer = Syncer(transactionManager, balanceManager)
        return TonKit(transactionManager, balanceManager, receiveAddress, syncer)
    }
}
