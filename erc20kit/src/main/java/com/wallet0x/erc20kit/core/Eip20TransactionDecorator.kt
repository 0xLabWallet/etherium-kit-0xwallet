package com.wallet0x.erc20kit.core

import com.wallet0x.erc20kit.contract.ApproveMethod
import com.wallet0x.erc20kit.contract.TransferMethod
import com.wallet0x.erc20kit.decorations.ApproveEip20Decoration
import com.wallet0x.erc20kit.decorations.OutgoingEip20Decoration
import com.wallet0x.erc20kit.events.TransferEventInstance
import com.wallet0x.ethereumkit.contracts.ContractEventInstance
import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.core.ITransactionDecorator
import com.wallet0x.ethereumkit.decorations.TransactionDecoration
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.InternalTransaction
import java.math.BigInteger

class Eip20TransactionDecorator(
    private val userAddress: Address
) : ITransactionDecorator {

    override fun decoration(from: Address?, to: Address?, value: BigInteger?, contractMethod: ContractMethod?, internalTransactions: List<InternalTransaction>, eventInstances: List<ContractEventInstance>): TransactionDecoration? {
        if (from == null || to == null || value == null || contractMethod == null) return null

        if (contractMethod is TransferMethod && from == userAddress) {
            return OutgoingEip20Decoration(
                to,
                contractMethod.to,
                contractMethod.value,
                contractMethod.to == userAddress,
                eventInstances.mapNotNull { it as? TransferEventInstance }.firstOrNull { it.contractAddress == to }?.tokenInfo
            )
        }

        if (contractMethod is ApproveMethod) {
            return ApproveEip20Decoration(
                to,
                contractMethod.spender,
                contractMethod.value
            )
        }

        return null
    }

}
