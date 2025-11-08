package com.wallet0x.oneinchkit.contracts.v4

import com.wallet0x.ethereumkit.contracts.Bytes32Array
import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class UnoswapMethodV4(
        val srcToken: Address,
        val amount: BigInteger,
        val minReturn: BigInteger,
        val params: Bytes32Array
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature

    override fun getArguments() = listOf(srcToken, amount, minReturn, params)

    companion object {
        val methodSignature = "unoswap(address,uint256,uint256,bytes32[])"
    }

}
