package com.wallet0x.nftkit.core

import com.wallet0x.nftkit.models.Nft
import com.wallet0x.nftkit.models.NftType

interface ITransactionSyncerListener {
    fun didSync(nfts: List<Nft>, type: NftType)
}

interface IBalanceSyncManagerListener {
    fun didFinishSyncBalances()
}