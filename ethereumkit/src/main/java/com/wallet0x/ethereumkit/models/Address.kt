package com.wallet0x.ethereumkit.models

import com.wallet0x.ethereumkit.core.AddressValidator
import com.wallet0x.ethereumkit.core.hexStringToByteArray
import com.wallet0x.ethereumkit.core.toHexString
import com.wallet0x.ethereumkit.utils.EIP55

data class Address(var raw: ByteArray) {
    init {
        if (raw.size == 32) {
            raw = raw.copyOfRange(12, raw.size)
        }
        com.wallet0x.ethereumkit.core.AddressValidator.validate(hex)
    }

    constructor(hex: String) : this(hex.hexStringToByteArray())

    val hex: String
        get() = raw.toHexString()

    val eip55: String
        get() = EIP55.format(hex)

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        return if (other is Address)
            raw.contentEquals(other.raw)
        else false
    }

    override fun hashCode(): Int {
        return raw.contentHashCode()
    }

    override fun toString(): String {
        return hex
    }

}
