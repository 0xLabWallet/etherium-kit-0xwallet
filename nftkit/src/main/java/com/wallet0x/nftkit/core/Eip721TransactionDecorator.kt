package com.wallet0x.nftkit.core

import com.wallet0x.ethereumkit.contracts.ContractEventInstance
import com.wallet0x.ethereumkit.contracts.ContractMethod
import com.wallet0x.ethereumkit.core.ITransactionDecorator
import com.wallet0x.ethereumkit.decorations.TransactionDecoration
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.InternalTransaction
import com.wallet0x.nftkit.contracts.Eip721SafeTransferFromMethod
import com.wallet0x.nftkit.decorations.OutgoingEip721Decoration
import com.wallet0x.nftkit.events.Eip721TransferEventInstance
import java.math.BigInteger

class Eip721TransactionDecorator(
    private val userAddress: Address
) : ITransactionDecorator {

    override fun decoration(
        from: Address?,
        to: Address?,
        value: BigInteger?,
        contractMethod: ContractMethod?,
        internalTransactions: List<InternalTransaction>,
        eventInstances: List<ContractEventInstance>
    ): TransactionDecoration? {
        if (from == null || to == null || value == null || contractMethod == null) return null

        return when {
            contractMethod is Eip721SafeTransferFromMethod && from == userAddress -> {
                OutgoingEip721Decoration(
                    contractAddress = to,
                    to = contractMethod.to,
                    tokenId = contractMethod.tokenId,
                    sentToSelf = contractMethod.to == userAddress,
                    tokenInfo = eventInstances.mapNotNull { it as? Eip721TransferEventInstance }.firstOrNull { it.contractAddress == to }?.tokenInfo
                )
            }
            else -> null
        }
    }
}