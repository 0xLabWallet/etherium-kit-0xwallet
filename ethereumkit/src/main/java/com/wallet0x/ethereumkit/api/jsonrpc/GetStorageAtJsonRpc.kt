package com.wallet0x.ethereumkit.api.jsonrpc

import com.wallet0x.ethereumkit.models.Address
import com.wallet0x.ethereumkit.models.DefaultBlockParameter

class GetStorageAtJsonRpc(
        @Transient val contractAddress: Address,
        @Transient val position: ByteArray,
        @Transient val defaultBlockParameter: DefaultBlockParameter
) : DataJsonRpc(
        method = "eth_getStorageAt",
        params = listOf(contractAddress, position, defaultBlockParameter)
)
