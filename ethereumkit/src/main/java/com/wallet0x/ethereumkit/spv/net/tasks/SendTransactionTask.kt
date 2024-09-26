package com.wallet0x.ethereumkit.spv.net.tasks

import com.wallet0x.ethereumkit.spv.core.ITask
import com.wallet0x.ethereumkit.models.RawTransaction
import com.wallet0x.ethereumkit.models.Signature

class SendTransactionTask(val sendId: Int,
                          val rawTransaction: RawTransaction,
                          val signature: Signature) : ITask
