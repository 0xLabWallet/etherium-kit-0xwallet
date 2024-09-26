package com.wallet0x.ethereumkit.spv.core

import com.wallet0x.ethereumkit.core.ISpvStorage
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.spv.models.AccountStateSpv
import com.wallet0x.ethereumkit.spv.models.BlockHeader
import com.wallet0x.ethereumkit.spv.net.handlers.AccountStateTaskHandler
import com.wallet0x.ethereumkit.spv.net.tasks.AccountStateTask

class AccountStateSyncer(private val storage: ISpvStorage,
                         private val address: Address) : AccountStateTaskHandler.Listener {

    interface Listener {
        fun onUpdate(accountState: AccountStateSpv)
    }

    var listener: com.wallet0x.ethereumkit.spv.core.AccountStateSyncer.Listener? = null

    fun sync(taskPerformer: com.wallet0x.ethereumkit.spv.core.ITaskPerformer, blockHeader: BlockHeader) {
        taskPerformer.add(AccountStateTask(address, blockHeader))
    }

    override fun didReceive(accountState: AccountStateSpv, address: Address, blockHeader: BlockHeader) {
        storage.saveAccountSate(accountState)
        listener?.onUpdate(accountState)
    }

}
