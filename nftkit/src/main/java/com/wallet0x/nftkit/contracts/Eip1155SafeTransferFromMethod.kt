package com.wallet0x.nftkit.contracts

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class Eip1155SafeTransferFromMethod(
    val from: Address,
    val to: Address,
    val tokenId: BigInteger,
    val value: BigInteger,
    val data: ByteArray
) : ContractMethod() {

    override val methodSignature =
        com.wallet0x.nftkit.contracts.Eip1155SafeTransferFromMethod.Companion.methodSignature
    override fun getArguments() = listOf(from, to, tokenId, value, data)

    companion object {
        const val methodSignature = "safeTransferFrom(address,address,uint256,uint256,bytes)"
    }

}