package com.wallet0x.uniswapkit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.contracts.ContractMethodFactory
import com.wallet0x.ethereumkit.contracts.ContractMethodHelper
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

object SwapExactTokensForTokensMethodFactory : ContractMethodFactory {
    override val methodId = ContractMethodHelper.getMethodId(com.wallet0x.uniswapkit.contract.SwapExactTokensForTokensMethod.Companion.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val parsedArguments = ContractMethodHelper.decodeABI(inputArguments, listOf(BigInteger::class, BigInteger::class, List::class, Address::class, BigInteger::class))
        val amountIn = parsedArguments[0] as BigInteger
        val amountOutMin = parsedArguments[1] as BigInteger
        val path = parsedArguments[2] as List<Address>
        val to = parsedArguments[3] as Address
        val deadline = parsedArguments[4] as BigInteger
        return com.wallet0x.uniswapkit.contract.SwapExactTokensForTokensMethod(
            amountIn,
            amountOutMin,
            path,
            to,
            deadline
        )
    }

}
