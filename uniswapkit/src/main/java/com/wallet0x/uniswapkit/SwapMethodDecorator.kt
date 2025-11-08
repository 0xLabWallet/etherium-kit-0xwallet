package com.wallet0x.uniswapkit

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.core.IMethodDecorator
import com.wallet0x.uniswapkit.contract.SwapContractMethodFactories

class SwapMethodDecorator(private val contractMethodFactories: com.wallet0x.uniswapkit.contract.SwapContractMethodFactories) : IMethodDecorator {

    override fun contractMethod(input: ByteArray): ContractMethod? =
        contractMethodFactories.createMethodFromInput(input)

}
