package com.wallet0x.erc20kit.decorations

import com.wallet0x.erc20kit.events.TokenInfo
import com.wallet0x.ethereumkit.decorations.TransactionDecoration
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.TransactionTag
import java.math.BigInteger

class OutgoingEip20Decoration(
    val contractAddress: Address,
    val to: Address,
    val value: BigInteger,
    val sentToSelf: Boolean,
    val tokenInfo: TokenInfo?
) : TransactionDecoration() {

    override fun tags(): List<String> =
        listOf(contractAddress.hex, TransactionTag.EIP20_TRANSFER, TransactionTag.tokenOutgoing(contractAddress.hex), TransactionTag.OUTGOING)

}
