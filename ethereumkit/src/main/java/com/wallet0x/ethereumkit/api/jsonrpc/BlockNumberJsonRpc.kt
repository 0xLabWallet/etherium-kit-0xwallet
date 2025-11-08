package com.wallet0x.ethereumkit.api.jsonrpc

class BlockNumberJsonRpc : LongJsonRpc(
        method = "eth_blockNumber",
        params = listOf()
)
