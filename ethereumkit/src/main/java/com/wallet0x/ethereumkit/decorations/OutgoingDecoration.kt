package com.wallet0x.ethereumkit.decorations

import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.TransactionTag
import java.math.BigInteger

class OutgoingDecoration(
    val to: Address,
    val value: BigInteger,
    val sentToSelf: Boolean
) : TransactionDecoration() {

    override fun tags(): List<String> {
        val tags = mutableListOf(TransactionTag.EVM_COIN, TransactionTag.EVM_COIN_OUTGOING, TransactionTag.OUTGOING)

        if (sentToSelf) {
            tags += listOf(TransactionTag.EVM_COIN_INCOMING, TransactionTag.INCOMING)
        }

        return tags
    }

}
