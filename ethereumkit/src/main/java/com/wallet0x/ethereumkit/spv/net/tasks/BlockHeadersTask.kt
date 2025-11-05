package com.wallet0x.ethereumkit.spv.net.tasks

import com.wallet0x.ethereumkit.spv.core.ITask
import com.wallet0x.ethereumkit.spv.models.BlockHeader

class BlockHeadersTask(val blockHeader: BlockHeader, val limit: Int, val reverse: Boolean = false) : ITask