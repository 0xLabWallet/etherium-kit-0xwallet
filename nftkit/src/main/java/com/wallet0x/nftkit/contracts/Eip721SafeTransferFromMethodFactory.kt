package com.wallet0x.nftkit.contracts

import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.contracts.ContractMethodFactory
import com.wallet0x.ethereumkit.contracts.ContractMethodHelper
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.spv.core.toBigInteger

class Eip721SafeTransferFromMethodFactory : ContractMethodFactory {
    override val methodId = ContractMethodHelper.getMethodId(Eip721SafeTransferFromMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ContractMethod {
        val from = Address(inputArguments.copyOfRange(12, 32))
        val to = Address(inputArguments.copyOfRange(44, 64))
        val tokenId = inputArguments.copyOfRange(64, 96).toBigInteger()
        val data = inputArguments.copyOfRange(96, 128)

        return Eip721SafeTransferFromMethod(from, to, tokenId, data)
    }
}