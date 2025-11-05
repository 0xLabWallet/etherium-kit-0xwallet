package com.wallet0x.uniswapkit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.contracts.ContractMethodFactory
import com.wallet0x.ethereumkit.contracts.ContractMethodHelper
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

object SwapExactETHForTokensSupportingFeeOnTransferTokensMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(com.wallet0x.uniswapkit.contract.SwapExactETHForTokensMethod.Companion.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val parsedArguments = ContractMethodHelper.decodeABI(inputArguments, listOf(BigInteger::class, List::class, Address::class, BigInteger::class))
        val amountOutMin = parsedArguments[0] as BigInteger
        val path = parsedArguments[1] as List<Address>
        val to = parsedArguments[2] as Address
        val deadline = parsedArguments[3] as BigInteger
        return com.wallet0x.uniswapkit.contract.SwapExactETHForTokensMethod(
            amountOutMin,
            path,
            to,
            deadline
        )
    }

}
