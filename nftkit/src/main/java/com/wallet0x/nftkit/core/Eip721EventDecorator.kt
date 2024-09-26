package com.wallet0x.nftkit.core

import com.wallet0x.ethereumkit.contracts.ContractEventInstance
import com.wallet0x.ethereumkit.core.IEventDecorator
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.Transaction
import com.wallet0x.ethereumkit.models.TransactionLog
import com.wallet0x.nftkit.events.Eip721TransferEventInstance
import com.wallet0x.nftkit.models.TokenInfo

class Eip721EventDecorator(
    private val userAddress: Address,
    private val storage: Storage
) : IEventDecorator {

    override fun contractEventInstancesMap(transactions: List<Transaction>): Map<String, List<ContractEventInstance>> {
        val events = if (transactions.size > 100) {
            storage.eip721Events()
        } else {
            storage.eip721Events(transactions.map(Transaction::hash))
        }

        val map = mutableMapOf<String, List<ContractEventInstance>>()

        for (event in events) {
            val tokenInfo = when {
                event.tokenName.isEmpty() && event.tokenSymbol.isEmpty() -> {
                    null
                }
                else -> TokenInfo(
                    event.tokenName,
                    event.tokenSymbol,
                    event.tokenDecimal
                )
            }
            val eventInstance = Eip721TransferEventInstance(
                event.contractAddress,
                event.from,
                event.to,
                event.tokenId,
                tokenInfo
            )

            map[event.hashString] = (map[event.hashString] ?: listOf()) + listOf(eventInstance)
        }

        return map
    }

    override fun contractEventInstances(logs: List<TransactionLog>): List<ContractEventInstance> {
        return logs.mapNotNull { log ->
            val eventInstance = log.getEip721EventInstance() ?: return@mapNotNull null

            when (eventInstance) {
                is Eip721TransferEventInstance -> {
                    if (eventInstance.from == userAddress || eventInstance.to == userAddress) {
                        return@mapNotNull eventInstance
                    }
                }
            }

            return@mapNotNull null
        }
    }

}
