package com.wallet0x.nftkit.core

import com.wallet0x.ethereumkit.core.EthereumKit
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.TransactionData
import com.wallet0x.nftkit.contracts.Eip1155SafeTransferFromMethod
import com.wallet0x.nftkit.contracts.Eip721SafeTransferFromMethod
import java.math.BigInteger

class TransactionManager(ethereumKit: EthereumKit) {
    private val address: Address = ethereumKit.receiveAddress

    fun transferEip721TransactionData(contractAddress: Address, to: Address, tokenId: BigInteger) =
        TransactionData(
            to = contractAddress,
            value = BigInteger.ZERO,
            input = Eip721SafeTransferFromMethod(address, to, tokenId, byteArrayOf()).encodedABI()
        )

    fun transferEip1155TransactionData(contractAddress: Address, to: Address, tokenId: BigInteger, value: BigInteger) =
        TransactionData(
            to = contractAddress,
            value = BigInteger.ZERO,
            input = com.wallet0x.nftkit.contracts.Eip1155SafeTransferFromMethod(
                address,
                to,
                tokenId,
                value,
                byteArrayOf()
            ).encodedABI()
        )
}