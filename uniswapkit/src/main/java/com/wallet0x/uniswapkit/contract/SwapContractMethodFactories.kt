package com.wallet0x.uniswapkit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethodFactories

object SwapContractMethodFactories : ContractMethodFactories() {
    init {
        val swapContractMethodFactories = listOf(
            com.wallet0x.uniswapkit.contract.SwapETHForExactTokensMethodFactory,
            com.wallet0x.uniswapkit.contract.SwapExactETHForTokensMethodFactory,
            com.wallet0x.uniswapkit.contract.SwapExactETHForTokensSupportingFeeOnTransferTokensMethodFactory,
            com.wallet0x.uniswapkit.contract.SwapExactTokensForETHMethodFactory,
            com.wallet0x.uniswapkit.contract.SwapExactTokensForETHSupportingFeeOnTransferTokensMethodFactory,
            com.wallet0x.uniswapkit.contract.SwapExactTokensForTokensMethodFactory,
            com.wallet0x.uniswapkit.contract.SwapExactTokensForTokensSupportingFeeOnTransferTokensMethodFactory,
            com.wallet0x.uniswapkit.contract.SwapTokensForExactETHMethodFactory,
            com.wallet0x.uniswapkit.contract.SwapTokensForExactTokensMethodFactory
        )
        registerMethodFactories(swapContractMethodFactories)
    }
}
