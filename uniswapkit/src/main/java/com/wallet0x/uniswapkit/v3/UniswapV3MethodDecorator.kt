package com.wallet0x.uniswapkit.v3

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.core.IMethodDecorator
import com.wallet0x.uniswapkit.v3.contract.UniswapV3ContractMethodFactories

class UniswapV3MethodDecorator(private val contractMethodFactories: UniswapV3ContractMethodFactories) :
    IMethodDecorator {

    override fun contractMethod(input: ByteArray): ContractMethod? =
        contractMethodFactories.createMethodFromInput(input)

}
