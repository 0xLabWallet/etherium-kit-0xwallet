package com.wallet0x.nftkit.models

import com.wallet0x.ethereumkit.models.Address
import java.math.BigInteger
import java.util.*

data class Nft(
    val type: NftType,
    val contractAddress: Address,
    val tokenId: BigInteger,
    val tokenName: String
) {
    override fun equals(other: Any?): Boolean {
        return other is Nft && other.type == type && other.tokenId == tokenId && other.tokenName == tokenName
    }

    override fun hashCode(): Int {
        return Objects.hash(type, contractAddress, tokenId)
    }
}