package com.wallet0x.oneinchkit.decorations

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.core.IMethodDecorator
import com.wallet0x.oneinchkit.contracts.OneInchContractMethodFactories

class OneInchMethodDecorator(private val contractMethodFactories: OneInchContractMethodFactories) : IMethodDecorator {

    override fun contractMethod(input: ByteArray): ContractMethod? =
        contractMethodFactories.createMethodFromInput(input)

}
