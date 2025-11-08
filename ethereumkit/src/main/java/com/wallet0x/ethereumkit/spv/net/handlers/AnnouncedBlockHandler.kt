package com.wallet0x.ethereumkit.spv.net.handlers

import com.wallet0x.ethereumkit.spv.core.IMessageHandler
import com.wallet0x.ethereumkit.spv.core.IPeer
import com.wallet0x.ethereumkit.spv.net.IInMessage
import com.wallet0x.ethereumkit.spv.net.les.messages.AnnounceMessage

class AnnouncedBlockHandler(private var listener: Listener? = null) : IMessageHandler {

    interface Listener {
        fun didAnnounce(peer: IPeer, blockHash: ByteArray, blockHeight: Long)
    }

    override fun handle(peer: IPeer, message: IInMessage): Boolean {
        if (message !is AnnounceMessage) {
            return false
        }

        listener?.didAnnounce(peer, message.blockHash, message.blockHeight)

        return true
    }
}
