package com.tonapps.blockchain.ton.contract.w5

import com.tonapps.blockchain.ton.TonNetwork
import com.tonapps.blockchain.ton.contract.BaseWalletContract
import com.tonapps.blockchain.ton.contract.MessageType
import com.tonapps.blockchain.ton.contract.SignaturePosition
import com.tonapps.blockchain.ton.contract.WalletVersion
import com.tonapps.blockchain.ton.contract.w5.W5Context.Companion.getWorkchain
import org.ton.api.pub.PublicKeyEd25519
import org.ton.bigint.BigInt
import org.ton.bigint.toBigInt
import org.ton.boc.BagOfCells
import org.ton.cell.Cell
import org.ton.cell.CellBuilder
import org.ton.contract.wallet.WalletTransfer
import org.ton.crypto.hex

class WalletV5R1Contract(
    publicKey: PublicKeyEd25519,
    private val context: W5Context,
) : BaseWalletContract(context.getWorkchain(), publicKey) {

    constructor(publicKey: PublicKeyEd25519, network: TonNetwork) : this(
        publicKey, W5Context.Client(networkGlobalId = network.value)
    )

    override fun getStateCell(): Cell {
        return CellBuilder.createCell {
            storeUInt(1, 1)
            storeUInt(0, 32)
            storeInt(context.networkGlobalId.toBigInt().xor(context.walletId), 32)
            storeBits(publicKey.key)
            storeBit(false)
        }
    }

    override fun getWalletVersion() = WalletVersion.V5R1

    override fun getSignaturePosition(): SignaturePosition {
        return SignaturePosition.Tail
    }

    override fun getCode(): Cell {
        return CODE
    }

    override fun createTransferUnsignedBody(
        validUntil: Long,
        seqno: Int,
        messageType: MessageType,
        queryId: BigInt?,
        vararg gifts: WalletTransfer
    ): Cell {
        if (gifts.size > 255) {
            throw IllegalArgumentException("Maximum number of messages in a single transfer is 255")
        }

        if (messageType == MessageType.Extension) {
            return CellBuilder.createCell {
                storeUInt(W5OpCodes.AuthExtension.code, 32)
                storeUInt(queryId ?: BigInt.ZERO, 64)
                storeRef(W5Action.storeOutListExtendedV5R1(gifts.toList(), messageType))
            }
        }

        val opCode = if (messageType == MessageType.Internal) {
            W5OpCodes.AuthSignedInternal
        } else {
            W5OpCodes.AuthSignedExternal
        }

        val signingMessage = CellBuilder.beginCell().storeUInt(opCode.code, 32).storeSlice(context.cell().beginParse())
        if (seqno == 0) {
            for (i in 0 until 32) {
                signingMessage.storeBit(true)
            }
        } else {
            signingMessage.storeUInt(validUntil, 32)
        }

        signingMessage.storeUInt(seqno, 32)
        signingMessage.storeSlice(W5Action.storeOutListExtendedV5R1(gifts.toList(), messageType).beginParse())
        return signingMessage.endCell()
    }

    companion object {

        @JvmField
        val CODE =
            BagOfCells(hex("b5ee9c7241021401000281000114ff00f4a413f4bcf2c80b01020120020d020148030402dcd020d749c120915b8f6320d70b1f2082106578746ebd21821073696e74bdb0925f03e082106578746eba8eb48020d72101d074d721fa4030fa44f828fa443058bd915be0ed44d0810141d721f4058307f40e6fa1319130e18040d721707fdb3ce03120d749810280b99130e070e2100f020120050c020120060902016e07080019adce76a2684020eb90eb85ffc00019af1df6a2684010eb90eb858fc00201480a0b0017b325fb51341c75c875c2c7e00011b262fb513435c280200019be5f0f6a2684080a0eb90fa02c0102f20e011e20d70b1f82107369676ebaf2e08a7f0f01e68ef0eda2edfb218308d722028308d723208020d721d31fd31fd31fed44d0d200d31f20d31fd3ffd70a000af90140ccf9109a28945f0adb31e1f2c087df02b35007b0f2d0845125baf2e0855036baf2e086f823bbf2d0882292f800de01a47fc8ca00cb1f01cf16c9ed542092f80fde70db3cd81003f6eda2edfb02f404216e926c218e4c0221d73930709421c700b38e2d01d72820761e436c20d749c008f2e09320d74ac002f2e09320d71d06c712c2005230b0f2d089d74cd7393001a4e86c128407bbf2e093d74ac000f2e093ed55e2d20001c000915be0ebd72c08142091709601d72c081c12e25210b1e30f20d74a111213009601fa4001fa44f828fa443058baf2e091ed44d0810141d718f405049d7fc8ca0040048307f453f2e08b8e14038307f45bf2e08c22d70a00216e01b3b0f2d090e2c85003cf1612f400c9ed54007230d72c08248e2d21f2e092d200ed44d0d2005113baf2d08f54503091319c01810140d721d70a00f2e08ee2c8ca0058cf16c9ed5493f2c08de20010935bdb31e1d74cd0b4d6c35e")).first()
    }

}