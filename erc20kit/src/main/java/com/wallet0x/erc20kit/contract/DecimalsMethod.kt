package com.wallet0x.erc20kit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod

class DecimalsMethod: ContractMethod() {
    override var methodSignature = "decimals()"
    override fun getArguments() = listOf<Any>()
}
