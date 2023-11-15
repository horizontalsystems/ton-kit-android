package io.horizontalsystems.tonkit

import org.ton.api.pk.PrivateKeyEd25519
import org.ton.block.Coins
import org.ton.block.MsgAddressInt
import org.ton.contract.wallet.WalletTransfer
import org.ton.contract.wallet.WalletV4R2Contract
import java.math.BigDecimal

class TransactionSender(
    private val wallet: WalletV4R2Contract,
    private val adnl: TonApiAdnl,
    private val privateKey: PrivateKeyEd25519,
) {
    suspend fun send(dest: String, amount: BigDecimal) {
        val liteApi = adnl.getLiteApi()
        checkNotNull(liteApi)

        val decimals = 9

        wallet.transfer(liteApi, privateKey, WalletTransfer {
            destination = MsgAddressInt.parseUserFriendly(dest)
            coins = Coins.ofNano(amount.movePointRight(decimals).toBigInteger())
        })
    }
}
