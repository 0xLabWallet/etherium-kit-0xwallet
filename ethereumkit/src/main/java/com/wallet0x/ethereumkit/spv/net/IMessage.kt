package com.wallet0x.ethereumkit.spv.net

interface IMessage

interface IInMessage : IMessage

interface IOutMessage : IMessage {
    fun encoded(): ByteArray
}
