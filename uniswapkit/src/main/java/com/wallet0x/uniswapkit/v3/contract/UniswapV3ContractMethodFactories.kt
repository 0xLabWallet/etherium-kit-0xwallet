package com.wallet0x.uniswapkit.v3.contract

import com.wallet0x.ethereumkit.contracts.ContractMethodFactories
import com.wallet0x.uniswapkit.v3.router.*

object UniswapV3ContractMethodFactories : ContractMethodFactories() {
    init {
        val swapContractMethodFactories = listOf(
            ExactInputMethod.Factory(),
            com.wallet0x.uniswapkit.v3.router.ExactOutputMethod.Factory(),
            ExactInputSingleMethod.Factory(),
            ExactOutputSingleMethod.Factory(),
            UnwrapWETH9Method.Factory(),
            MulticallMethod.Factory(UniswapV3ContractMethodFactories),
        )
        registerMethodFactories(swapContractMethodFactories)
    }
}
