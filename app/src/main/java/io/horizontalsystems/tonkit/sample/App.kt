package io.horizontalsystems.tonkit.sample

import android.app.Application
import io.horizontalsystems.tonkit.core.TonKit
import io.horizontalsystems.tonkit.core.TonKit.WalletType
import io.horizontalsystems.tonkit.models.Network
import io.horizontalsystems.tonkit.tonconnect.TonConnectKit

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initTonKit()
    }

    private fun initTonKit() {
        val walletId = "wallet-${walletType.javaClass.simpleName}"
//        val walletId = UUID.randomUUID().toString()

        val network = Network.MainNet
        tonKit = TonKit.getInstance(
            walletType,
            network,
            this,
            walletId
        )

        tonConnectKit = TonConnectKit.getInstance(this)
    }

    companion object {
        @OptIn(ExperimentalStdlibApi::class)
        val walletType = WalletType.Seed("4813afb7acab41b48c1bc3d5a19d04979d0d6fafcc517b9ce00be65fc767e7b7".hexToByteArray())
        //        val walletType = WalletType.Watch("EQDfvVvoSX_cDJ_L38Z2hkhA3fitZCPW1WV9mw6CcNbIrH-Q")
//        val words =
//            "used ugly meat glad balance divorce inner artwork hire invest already piano".split(" ")
//        val walletType = WalletType.Mnemonic(words, "")

        lateinit var tonKit: TonKit
        lateinit var tonConnectKit: TonConnectKit
    }
}
