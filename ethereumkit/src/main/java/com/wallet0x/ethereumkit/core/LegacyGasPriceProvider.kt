package com.wallet0x.ethereumkit.core

import com.wallet0x.ethereumkit.api.jsonrpc.GasPriceJsonRpc
import io.reactivex.Single

class LegacyGasPriceProvider(
        private val evmKit: EthereumKit
) {
    fun gasPriceSingle(): Single<Long> {
        return evmKit.rpcSingle(GasPriceJsonRpc())
    }
}
