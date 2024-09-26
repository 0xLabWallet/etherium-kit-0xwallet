package com.wallet0x.uniswapkit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class SwapExactETHForTokensMethod(
        val amountOutMin: BigInteger,
        val path: List<Address>,
        val to: Address,
        val deadline: BigInteger
) : ContractMethod() {

    override val methodSignature =
        com.wallet0x.uniswapkit.contract.SwapExactETHForTokensMethod.Companion.methodSignature
    override fun getArguments() = listOf(amountOutMin, path, to, deadline)

    companion object {
        const val methodSignature = "swapExactETHForTokens(uint256,address[],address,uint256)"
    }
}
