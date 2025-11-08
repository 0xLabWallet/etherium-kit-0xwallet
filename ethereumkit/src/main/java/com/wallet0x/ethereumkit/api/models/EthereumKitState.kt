package com.wallet0x.ethereumkit.api.models

class EthereumKitState {
    var accountState: AccountState? = null
    var lastBlockHeight: Long? = null

    fun clear() {
        accountState = null
        lastBlockHeight = null
    }
}
