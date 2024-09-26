package com.wallet0x.ethereumkit.core.eip1559

import com.wallet0x.ethereumkit.api.jsonrpc.JsonRpc
import com.wallet0x.ethereumkit.models.DefaultBlockParameter

class FeeHistoryJsonRpc(
        @Transient val blocksCount: Long,
        @Transient val defaultBlockParameter: DefaultBlockParameter,
        @Transient val rewardPercentile: List<Int>,
) : JsonRpc<FeeHistory>(
        method = "eth_feeHistory",
        params = listOf(blocksCount, defaultBlockParameter, rewardPercentile)
) {
    @Transient
    override val typeOfResult = FeeHistory::class.java
}
