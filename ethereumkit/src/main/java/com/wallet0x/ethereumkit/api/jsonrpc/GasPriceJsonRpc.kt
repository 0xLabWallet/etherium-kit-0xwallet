package com.wallet0x.ethereumkit.api.jsonrpc

class GasPriceJsonRpc : LongJsonRpc(
        method = "eth_gasPrice",
        params = listOf()
)
