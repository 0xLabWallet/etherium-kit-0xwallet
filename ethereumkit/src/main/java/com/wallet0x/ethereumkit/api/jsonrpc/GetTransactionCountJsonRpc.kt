package com.wallet0x.ethereumkit.api.jsonrpc

import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.DefaultBlockParameter

class GetTransactionCountJsonRpc(
        @Transient val address: Address,
        @Transient val defaultBlockParameter: DefaultBlockParameter
) : LongJsonRpc(
        method = "eth_getTransactionCount",
        params = listOf(address, defaultBlockParameter)
)
