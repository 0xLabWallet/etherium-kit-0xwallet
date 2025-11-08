package com.wallet0x.ethereumkit.decorations

import com.wallet0x.ethereumkit.contracts.ContractEventInstance
import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.contracts.EmptyMethod
import com.wallet0x.ethereumkit.core.ITransactionDecorator
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.InternalTransaction
import java.math.BigInteger

class EthereumDecorator(private val address: Address) : ITransactionDecorator {

    override fun decoration(from: Address?, to: Address?, value: BigInteger?, contractMethod: ContractMethod?, internalTransactions: List<InternalTransaction>, eventInstances: List<ContractEventInstance>): TransactionDecoration? {
        if (from == null || value == null) return null
        if (to == null) return ContractCreationDecoration()

        if (contractMethod != null && contractMethod is EmptyMethod) {
            if (from == address) {
                return OutgoingDecoration(to, value, to == address)
            }

            if (to == address) {
                return IncomingDecoration(from, value)
            }
        }

        return null
    }

}
