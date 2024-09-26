package com.wallet0x.ethereumkit.spv.net.les.messages

import com.wallet0x.ethereumkit.core.toHexString
import com.wallet0x.ethereumkit.spv.core.toBigInteger
import com.wallet0x.ethereumkit.spv.core.toLong
import com.wallet0x.ethereumkit.spv.net.IInMessage
import com.wallet0x.ethereumkit.spv.rlp.RLP
import com.wallet0x.ethereumkit.spv.rlp.RLPList
import java.math.BigInteger

class AnnounceMessage(payload: ByteArray) : IInMessage {

    val blockHash: ByteArray
    val blockHeight: Long
    val blockTotalDifficulty: BigInteger
    val reorganizationDepth: Long

    init {
        val params = RLP.decode2(payload)[0] as RLPList
        blockHash = params[0].rlpData ?: byteArrayOf()
        blockHeight = params[1].rlpData.toLong()
        blockTotalDifficulty = params[2].rlpData.toBigInteger()
        reorganizationDepth = params[3].rlpData.toLong()
    }

    override fun toString(): String {
        return "Announce [blockHash: ${blockHash.toHexString()}; " +
                "blockHeight: $blockHeight; " +
                "blockTotalDifficulty: $blockTotalDifficulty; " +
                "reorganizationDepth: $reorganizationDepth]"
    }
}
