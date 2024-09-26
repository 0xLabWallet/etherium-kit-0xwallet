package com.wallet0x.uniswapkit.v3.pool

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class GetPoolMethod(
    private val tokenA: Address,
    private val tokenB: Address,
    private val fee: BigInteger
) : ContractMethod() {

    override val methodSignature = "getPool(address,address,uint24)"
    override fun getArguments() = listOf(tokenA, tokenB, fee)

}
