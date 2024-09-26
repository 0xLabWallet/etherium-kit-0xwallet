package com.wallet0x.erc20kit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address

class AllowanceMethod(val owner: Address, val spender: Address) : ContractMethod() {

    override val methodSignature = "allowance(address,address)"
    override fun getArguments() = listOf(owner, spender)

}
