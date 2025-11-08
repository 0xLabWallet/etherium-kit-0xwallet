package com.wallet0x.ethereumkit.spv.net

import com.wallet0x.ethereumkit.core.ISpvStorage
import com.wallet0x.ethereumkit.network.INetwork
import com.wallet0x.ethereumkit.spv.models.BlockHeader

class BlockHelper(val storage: ISpvStorage, val network: INetwork) {

    val lastBlockHeader: BlockHeader
        get() = storage.getLastBlockHeader() ?: network.checkpointBlock

}
