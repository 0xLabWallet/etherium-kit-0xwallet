package com.wallet0x.erc20kit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class ApproveMethod(val spender: Address, val value: BigInteger) : ContractMethod() {

    override val methodSignature =
        com.wallet0x.erc20kit.contract.ApproveMethod.Companion.methodSignature
    override fun getArguments() = listOf(spender, value)

    companion object {
        const val methodSignature = "approve(address,uint256)"
    }

}
