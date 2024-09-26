package com.wallet0x.erc20kit.events

import com.wallet0x.ethereumkit.contracts.ContractEvent
import com.wallet0x.ethereumkit.contracts.ContractEventInstance
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.TransactionTag
import java.math.BigInteger

class TransferEventInstance(
    contractAddress: Address, val from: Address, val to: Address, val value: BigInteger,
    val tokenInfo: TokenInfo?
) : ContractEventInstance(contractAddress) {

    override fun tags(userAddress: Address): List<String> {
        val tags = mutableListOf(contractAddress.hex, TransactionTag.EIP20_TRANSFER)

        if (from == userAddress) {
            tags.add(TransactionTag.tokenOutgoing(contractAddress.hex))
            tags.add(TransactionTag.OUTGOING)
        }

        if (to == userAddress) {
            tags.add(TransactionTag.tokenIncoming(contractAddress.hex))
            tags.add(TransactionTag.INCOMING)
        }

        return tags
    }

    companion object {
        val signature = ContractEvent(
            "Transfer",
            listOf(
                ContractEvent.Argument.Address,
                ContractEvent.Argument.Address,
                ContractEvent.Argument.Uint256
            )
        ).signature
    }
}

data class TokenInfo(val tokenName: String, val tokenSymbol: String, val tokenDecimal: Int)

