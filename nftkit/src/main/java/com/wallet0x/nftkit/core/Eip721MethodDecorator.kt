package com.wallet0x.nftkit.core

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.contracts.ContractMethodFactories
import com.wallet0x.ethereumkit.core.IMethodDecorator

class Eip721MethodDecorator(
    private val contractMethodFactories: ContractMethodFactories
) : IMethodDecorator {
    override fun contractMethod(input: ByteArray): ContractMethod? {
        return contractMethodFactories.createMethodFromInput(input)
    }
}