package com.wallet0x.ethereumkit.api.jsonrpc.models

import com.google.gson.annotations.SerializedName
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

class RpcTransaction(
        val hash: ByteArray,
        val nonce: Long,
        val blockHash: ByteArray?,
        val blockNumber: Long?,
        val transactionIndex: Int?,
        val from: Address,
        val to: Address?,
        val value: BigInteger,
        val gasPrice: Long,
        val maxFeePerGas: Long?,
        val maxPriorityFeePerGas: Long?,
        @SerializedName("gas")
        val gasLimit: Long,
        val input: ByteArray
)
