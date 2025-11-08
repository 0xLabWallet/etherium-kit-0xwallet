package com.wallet0x.erc20kit.contract

import com.wallet0x.ethereumkit.contracts.ContractMethodFactories

object Eip20ContractMethodFactories : ContractMethodFactories() {

    init {
        registerMethodFactories(listOf(TransferMethodFactory,
            com.wallet0x.erc20kit.contract.ApproveMethodFactory
        ))
    }

}
