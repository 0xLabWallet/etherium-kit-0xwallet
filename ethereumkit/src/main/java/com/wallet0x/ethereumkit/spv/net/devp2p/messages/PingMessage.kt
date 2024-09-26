package com.wallet0x.ethereumkit.spv.net.devp2p.messages

import com.wallet0x.ethereumkit.core.hexStringToByteArray
import com.wallet0x.ethereumkit.spv.net.IInMessage

class PingMessage() : IInMessage {

    constructor(payload: ByteArray) : this()

    override fun toString(): String {
        return "Ping"
    }

    companion object {
        val payload = "C0".hexStringToByteArray()
    }
}
