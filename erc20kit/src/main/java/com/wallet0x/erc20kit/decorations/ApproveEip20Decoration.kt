package com.wallet0x.erc20kit.decorations

import com.wallet0x.ethereumkit.contracts.ContractEvent
import com.wallet0x.ethereumkit.decorations.TransactionDecoration
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.TransactionTag
import java.math.BigInteger

class ApproveEip20Decoration(
    val contractAddress: Address,
    val spender: Address,
    val value: BigInteger
) : TransactionDecoration() {

    override fun tags(): List<String> =
        listOf(contractAddress.hex, TransactionTag.EIP20_APPROVE)

    companion object {
        val signature = ContractEvent(
            "Approval",
            listOf(
                ContractEvent.Argument.Address,
                ContractEvent.Argument.Address,
                ContractEvent.Argument.Uint256
            )
        ).signature
    }
}
