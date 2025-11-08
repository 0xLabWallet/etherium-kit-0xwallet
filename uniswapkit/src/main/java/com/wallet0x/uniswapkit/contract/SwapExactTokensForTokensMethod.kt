package com.wallet0x.uniswapkit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class SwapExactTokensForTokensMethod(
        val amountIn: BigInteger,
        val amountOutMin: BigInteger,
        val path: List<Address>,
        val to: Address,
        val deadline: BigInteger
) : ContractMethod() {

    override val methodSignature =
        com.wallet0x.uniswapkit.contract.SwapExactTokensForTokensMethod.Companion.methodSignature
    override fun getArguments() = listOf(amountIn, amountOutMin, path, to, deadline)

    companion object {
        const val methodSignature = "swapExactTokensForTokens(uint256,uint256,address[],address,uint256)"
    }

}
