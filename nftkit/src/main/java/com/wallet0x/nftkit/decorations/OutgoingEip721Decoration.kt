package com.wallet0x.nftkit.decorations

import com.wallet0x.ethereumkit.decorations.TransactionDecoration
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.TransactionTag
import com.wallet0x.nftkit.models.TokenInfo
import java.math.BigInteger

class OutgoingEip721Decoration(
    val contractAddress: Address,
    val to: Address,
    val tokenId: BigInteger,
    val sentToSelf: Boolean,
    val tokenInfo: TokenInfo?,
) : TransactionDecoration() {

    override fun tags(): List<String> =
        listOf(contractAddress.hex, EIP721_TRANSFER, TransactionTag.tokenOutgoing(contractAddress.hex), TransactionTag.OUTGOING)

    companion object {
        const val EIP721_TRANSFER = "eip721Transfer"
    }
}