package com.wallet0x.erc20kit.core

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.contracts.ContractMethodFactories
import com.wallet0x.ethereumkit.core.IMethodDecorator

class Eip20MethodDecorator(
    private val contractMethodFactories: ContractMethodFactories
) : IMethodDecorator {

    override fun contractMethod(input: ByteArray): ContractMethod? =
        contractMethodFactories.createMethodFromInput(input)

}
