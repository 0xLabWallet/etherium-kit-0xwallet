package com.wallet0x.erc20kit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethodFactory
import com.wallet0x.ethereumkit.contracts.ContractMethodHelper
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.spv.core.toBigInteger

object ApproveMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(ApproveMethod.Companion.methodSignature)

    override fun createMethod(inputArguments: ByteArray): ApproveMethod {
        val address = Address(
            inputArguments.copyOfRange(
                12,
                32
            )
        )
        val value = inputArguments.copyOfRange(32, 64).toBigInteger()

        return ApproveMethod(address, value)
    }

}
