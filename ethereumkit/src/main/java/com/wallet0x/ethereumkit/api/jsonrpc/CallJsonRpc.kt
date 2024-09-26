package com.wallet0x.ethereumkit.api.jsonrpc

import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.DefaultBlockParameter

class CallJsonRpc(
        @Transient val contractAddress: Address,
        @Transient val data: ByteArray,
        @Transient val defaultBlockParameter: DefaultBlockParameter
) : DataJsonRpc(
        method = "eth_call",
        params = listOf(mapOf("to" to contractAddress, "data" to data), defaultBlockParameter)
)
