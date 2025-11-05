package com.wallet0x.ethereumkit.spv.net.devp2p.messages

import com.wallet0x.ethereumkit.core.hexStringToByteArray
import com.wallet0x.ethereumkit.spv.net.IInMessage
import com.wallet0x.ethereumkit.spv.net.IOutMessage

class PongMessage() : IInMessage, IOutMessage {

    constructor(payload: ByteArray) : this()

    override fun encoded(): ByteArray {
        return payload
    }

    override fun toString(): String {
        return "Pong"
    }

    companion object {
        val payload = "C0".hexStringToByteArray()
    }
}
