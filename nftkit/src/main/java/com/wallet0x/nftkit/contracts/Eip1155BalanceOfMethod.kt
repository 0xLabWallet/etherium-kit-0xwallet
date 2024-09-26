package com.wallet0x.nftkit.contracts

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class Eip1155BalanceOfMethod(
    private val owner: Address,
    private val tokenId: BigInteger
) : ContractMethod() {

    override val methodSignature =
        com.wallet0x.nftkit.contracts.Eip1155BalanceOfMethod.Companion.methodSignature

    override fun getArguments() = listOf(owner, tokenId)

    companion object {
        const val methodSignature = "balanceOf(address,uint256)"
    }
}