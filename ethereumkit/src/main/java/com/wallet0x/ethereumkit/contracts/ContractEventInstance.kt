package com.wallet0x.ethereumkit.contracts

import com.wallet0x.ethereumkit.models.Address

open class ContractEventInstance(val contractAddress: Address) {

    open fun tags(userAddress: Address): List<String> = listOf()

}
