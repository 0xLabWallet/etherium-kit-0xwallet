package com.wallet0x.erc20kit.core

import com.wallet0x.erc20kit.decorations.ApproveEip20Decoration
import com.wallet0x.erc20kit.events.ApproveEventInstance
import com.wallet0x.erc20kit.events.TransferEventInstance
import com.wallet0x.ethereumkit.contracts.ContractEventInstance
import com.wallet0x.ethereumkit.core.hexStringToByteArrayOrNull
import com.wallet0x.ethereumkit.core.toRawHexString
import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.TransactionLog
import java.math.BigInteger

fun TransactionLog.getErc20EventInstance(): ContractEventInstance? {
    return try {
        if (topics.size != 3) {
            return null
        }

        val signature = topics[0].hexStringToByteArrayOrNull()

        val firstParam = Address(topics[1])
        val secondParam = Address(topics[2])

        when {
            signature.contentEquals(TransferEventInstance.signature) ->
                TransferEventInstance(address, firstParam, secondParam, BigInteger(data.toRawHexString(), 16), null)
            signature.contentEquals(ApproveEip20Decoration.signature) ->
                ApproveEventInstance(address, firstParam, secondParam, BigInteger(data.toRawHexString(), 16))
            else ->
                null
        }
    } catch (error: Throwable) {
        error.printStackTrace()
        null
    }
}
