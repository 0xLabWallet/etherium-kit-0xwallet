package com.wallet0x.uniswapkit.v3.router

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.contracts.ContractMethodFactory
import com.wallet0x.ethereumkit.contracts.ContractMethodHelper
import com.wallet0x.ethereumkit.core.hexStringToByteArray
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.uniswapkit.v3.SwapPath
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.datatypes.DynamicBytes
import org.web3j.abi.datatypes.DynamicStruct
import org.web3j.abi.datatypes.generated.Uint256
import java.math.BigInteger

class ExactOutputMethod(
    val path: ByteArray,
    val recipient: Address,
    val amountOut: BigInteger,
    val amountInMaximum: BigInteger,
) : ContractMethod() {
    override val methodSignature =
        com.wallet0x.uniswapkit.v3.router.ExactOutputMethod.Companion.methodSignature
    override fun getArguments() = throw Exception("This class has its own implementation of encodedABI()")

    override fun encodedABI(): ByteArray {
        val function = org.web3j.abi.datatypes.Function(
            "exactOutput",
            listOf(
                DynamicStruct(
                    DynamicBytes(path),
                    org.web3j.abi.datatypes.Address(recipient.hex),
                    Uint256(amountOut),
                    Uint256(amountInMaximum)
                )
            ),
            listOf()
        )

        return FunctionEncoder.encode(function).hexStringToByteArray()
    }

    companion object {
        private const val methodSignature = "exactOutput((bytes,address,uint256,uint256))"
    }

    class Factory : ContractMethodFactory {
        override val methodId = ContractMethodHelper.getMethodId(com.wallet0x.uniswapkit.v3.router.ExactOutputMethod.Companion.methodSignature)

        override fun createMethod(inputArguments: ByteArray): ContractMethod {
            val argumentTypes = listOf(
                ContractMethodHelper.DynamicStruct(
                    listOf(
                        ByteArray::class,
                        Address::class,
                        BigInteger::class,
                        BigInteger::class
                    )
                ),
            )
            val dynamicStruct = ContractMethodHelper.decodeABI(inputArguments, argumentTypes)

            val parsedArguments = dynamicStruct.first() as List<Any>

            return com.wallet0x.uniswapkit.v3.router.ExactOutputMethod(
                path = parsedArguments[0] as ByteArray,
                recipient = parsedArguments[1] as Address,
                amountOut = parsedArguments[2] as BigInteger,
                amountInMaximum = parsedArguments[3] as BigInteger,
            )
        }
    }
}
