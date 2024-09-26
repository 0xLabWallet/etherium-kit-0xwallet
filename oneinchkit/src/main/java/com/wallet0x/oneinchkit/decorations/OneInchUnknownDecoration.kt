package com.wallet0x.oneinchkit.decorations

import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.TransactionTag
import java.math.BigInteger

class OneInchUnknownDecoration(
    override val contractAddress: Address,
    val tokenAmountIn: com.wallet0x.oneinchkit.decorations.OneInchUnknownDecoration.TokenAmount?,
    val tokenAmountOut: com.wallet0x.oneinchkit.decorations.OneInchUnknownDecoration.TokenAmount?
) : com.wallet0x.oneinchkit.decorations.OneInchDecoration(contractAddress) {

    class TokenAmount(val token: com.wallet0x.oneinchkit.decorations.OneInchDecoration.Token, val value: BigInteger)

    override fun tags(): List<String> {
        val tags = super.tags().toMutableList()

        listOf(contractAddress.hex, TransactionTag.SWAP)

        if (tokenAmountIn != null) {
            tags.addAll(getTags(tokenAmountIn.token, TransactionTag.OUTGOING))
        }

        if (tokenAmountOut != null) {
            tags.addAll(getTags(tokenAmountOut.token, TransactionTag.INCOMING))
        }

        return tags
    }

}
