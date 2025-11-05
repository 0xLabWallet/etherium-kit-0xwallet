package com.wallet0x.oneinchkit.contracts.v4

import com.wallet0x.ethereumkit.contracts.Bytes32Array
import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.contracts.ContractMethodFactory
import com.wallet0x.ethereumkit.contracts.ContractMethodHelper
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class UnoswapMethodFactoryV4 : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(UnoswapMethodV4.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val argumentTypes = listOf(Address::class, BigInteger::class, BigInteger::class, Bytes32Array::class)

        val parsedArguments = ContractMethodHelper.decodeABI(inputArguments, argumentTypes)

        val srcToken = parsedArguments[0] as Address
        val amount = parsedArguments[1] as BigInteger
        val minReturn = parsedArguments[2] as BigInteger
        val params = parsedArguments[3] as Bytes32Array

        return UnoswapMethodV4(srcToken, amount, minReturn, params)
    }

}
