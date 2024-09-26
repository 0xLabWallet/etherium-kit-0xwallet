package com.wallet0x.erc20kit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod

class NameMethod: ContractMethod() {
    override var methodSignature = "name()"
    override fun getArguments() = listOf<Any>()
}
