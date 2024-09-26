package com.wallet0x.ethereumkit.spv.net.tasks

import com.wallet0x.ethereumkit.network.INetwork
import com.wallet0x.ethereumkit.spv.core.ITask
import com.wallet0x.ethereumkit.spv.models.BlockHeader
import java.math.BigInteger

class HandshakeTask(val peerId: String, network: INetwork, blockHeader: BlockHeader) : ITask {
    val networkId: Int = network.id
    val genesisHash: ByteArray = network.genesisBlockHash
    val headTotalDifficulty: BigInteger = blockHeader.totalDifficulty
    val headHash: ByteArray = blockHeader.hashHex
    val headHeight: Long = blockHeader.height
}
