package com.wallet0x.nftkit.contracts

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.contracts.ContractMethodFactory
import com.wallet0x.ethereumkit.contracts.ContractMethodHelper
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.spv.core.toBigInteger

class Eip1155SafeTransferFromMethodFactory : ContractMethodFactory {
    override val methodId = ContractMethodHelper.getMethodId(com.wallet0x.nftkit.contracts.Eip1155SafeTransferFromMethod.Companion.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val from = Address(inputArguments.copyOfRange(12, 32))
        val to = Address(inputArguments.copyOfRange(44, 64))
        val tokenId = inputArguments.copyOfRange(64, 96).toBigInteger()
        val value = inputArguments.copyOfRange(96, 128).toBigInteger()
        val data = inputArguments.copyOfRange(128, 160)

        return com.wallet0x.nftkit.contracts.Eip1155SafeTransferFromMethod(
            from,
            to,
            tokenId,
            value,
            data
        )
    }
}