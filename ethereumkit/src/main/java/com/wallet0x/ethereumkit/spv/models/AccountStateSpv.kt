package com.wallet0x.ethereumkit.spv.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wallet0x.ethereumkit.core.toHexString
import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger

@Entity
class AccountStateSpv(
        @PrimaryKey
        val address: Address,
        val nonce: Long,
        val balance: BigInteger,
        val storageHash: ByteArray,
        val codeHash: ByteArray
) {

    override fun toString(): String {
        return "(\n" +
                "  nonce: $nonce\n" +
                "  balance: $balance\n" +
                "  storageHash: ${storageHash.toHexString()}\n" +
                "  codeHash: ${codeHash.toHexString()}\n" +
                ")"
    }
}