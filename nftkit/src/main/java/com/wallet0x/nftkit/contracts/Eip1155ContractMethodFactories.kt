package com.wallet0x.nftkit.contracts

import com.wallet0x.ethereumkit.contracts.ContractMethodFactories

object Eip1155ContractMethodFactories : ContractMethodFactories() {
    init {
        registerMethodFactories(listOf(com.wallet0x.nftkit.contracts.Eip1155SafeTransferFromMethodFactory()))
    }
}