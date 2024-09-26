package com.wallet0x.oneinchkit.contracts

import com.wallet0x.ethereumkit.contracts.ContractMethodFactories
import com.wallet0x.oneinchkit.contracts.v4.SwapMethodFactoryV4
import com.wallet0x.oneinchkit.contracts.v4.UnoswapMethodFactoryV4
import com.wallet0x.oneinchkit.contracts.v4.UnparsedSwapMethodsFactoryV4
import com.wallet0x.oneinchkit.contracts.v5.SwapMethodFactoryV5
import com.wallet0x.oneinchkit.contracts.v5.UnoswapMethodFactoryV5
import com.wallet0x.oneinchkit.contracts.v5.UnparsedSwapMethodsFactoryV5

object OneInchContractMethodFactories : ContractMethodFactories() {

    init {
        val swapContractMethodFactories = listOf(
            UnoswapMethodFactoryV5(),
            SwapMethodFactoryV5(),
            UnparsedSwapMethodsFactoryV5(),
            UnoswapMethodFactoryV4(),
            SwapMethodFactoryV4(),
            UnparsedSwapMethodsFactoryV4()
        )
        registerMethodFactories(swapContractMethodFactories)
    }

}
