package com.wallet0x.nftkit.contracts

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class Eip721SafeTransferFromMethod(
    val from: Address,
    val to: Address,
    val tokenId: BigInteger,
    val data: ByteArray
) : ContractMethod() {

    override val methodSignature = Companion.methodSignature
    override fun getArguments() = listOf(from, to, tokenId, data)

    companion object {
        const val methodSignature = "safeTransferFrom(address,address,uint256,bytes)"
    }

}