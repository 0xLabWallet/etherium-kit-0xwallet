package com.wallet0x.erc20kit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethodFactory
import com.wallet0x.ethereumkit.contracts.ContractMethodHelper
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.spv.core.toBigInteger

object TransferMethodFactory : ContractMethodFactory {

    override val methodId = ContractMethodHelper.getMethodId(TransferMethod.methodSignature)

    override fun createMethod(inputArguments: ByteArray): TransferMethod {
        val address = Address(
            inputArguments.copyOfRange(
                12,
                32
            )
        )
        val value = inputArguments.copyOfRange(32, 64).toBigInteger()

        return TransferMethod(address, value)
    }

}
