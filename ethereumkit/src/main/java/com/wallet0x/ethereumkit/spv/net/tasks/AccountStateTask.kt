package com.wallet0x.ethereumkit.spv.net.tasks

import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.spv.core.ITask
import com.wallet0x.ethereumkit.spv.models.BlockHeader

class AccountStateTask(val address: Address, val blockHeader: BlockHeader) : ITask
