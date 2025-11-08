package com.wallet0x.erc20kit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address

class BalanceOfMethod(val owner: Address) : ContractMethod() {

    override val methodSignature = "balanceOf(address)"
    override fun getArguments() = listOf(owner)

}
