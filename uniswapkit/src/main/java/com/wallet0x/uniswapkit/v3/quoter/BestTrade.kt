package com.wallet0x.uniswapkit.v3.quoter

import com.wallet0x.uniswapkit.models.*
import com.wallet0x.uniswapkit.v3.SwapPath
import java.math.BigInteger

data class BestTrade(
    val tradeType: TradeType,
    val swapPath: SwapPath,
    val amountIn: BigInteger,
    val amountOut: BigInteger,
    val tokenIn: Token,
    val tokenOut: Token
) {
    val singleSwap = swapPath.items.size == 1
    val singleSwapFee get() = swapPath.items.single().fee
}
