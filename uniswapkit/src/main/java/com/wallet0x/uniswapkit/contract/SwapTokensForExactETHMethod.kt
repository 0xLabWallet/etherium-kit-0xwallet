package com.wallet0x.uniswapkit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class SwapTokensForExactETHMethod(
        val amountOut: BigInteger,
        val amountInMax: BigInteger,
        val path: List<Address>,
        val to: Address,
        val deadline: BigInteger
) : ContractMethod() {

    override val methodSignature =
        com.wallet0x.uniswapkit.contract.SwapTokensForExactETHMethod.Companion.methodSignature
    override fun getArguments() = listOf(amountOut, amountInMax, path, to, deadline)

    companion object {
        const val methodSignature = "swapTokensForExactETH(uint256,uint256,address[],address,uint256)"
    }
}
