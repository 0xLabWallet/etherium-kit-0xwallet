package com.wallet0x.ethereumkit.models

class TransactionSource(val name: String, val type: SourceType) {

    fun transactionUrl(hash: String) =
        when (type) {
            is SourceType.Etherscan -> "${type.txBaseUrl}/tx/$hash"
        }

    sealed class SourceType {
        class Etherscan(val apiBaseUrl: String, val txBaseUrl: String, val apiKey: String, val chainId: Int) : SourceType()
    }

    companion object {
        // Etherscan API V2 - единый base URL для всех сетей
        private const val ETHERSCAN_API_V2_BASE_URL = "https://api.etherscan.io"

        fun ethereumEtherscan(apiKey: List<String>): TransactionSource {
            return TransactionSource(
                "etherscan.io",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://etherscan.io", apiKey.random(), 1)
            )
        }

        fun goerliEtherscan(apiKey: String): TransactionSource {
            return TransactionSource(
                "etherscan.io",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://goerli.etherscan.io", apiKey, 5)
            )
        }

        fun bscscan(apiKey: List<String>): TransactionSource {
            return TransactionSource(
                "bscscan.com",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://bscscan.com", apiKey.random(), 56)
            )
        }

        fun polygonscan(apiKey: String): TransactionSource {
            return TransactionSource(
                "polygonscan.com",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://polygonscan.com", apiKey, 137)
            )
        }

        fun optimisticEtherscan(apiKey: String): TransactionSource {
            return TransactionSource(
                "optimistic.etherscan.io",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://optimistic.etherscan.io", apiKey, 10)
            )
        }

        fun arbiscan(apiKey: String): TransactionSource {
            return TransactionSource(
                "arbiscan.io",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://arbiscan.io", apiKey, 42161)
            )
        }

        fun snowtrace(apiKey: String): TransactionSource {
            return TransactionSource(
                "snowtrace.io",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://snowtrace.io", apiKey, 43114)
            )
        }

        fun gnosis(apiKey: String): TransactionSource {
            return TransactionSource(
                "gnosisscan.io",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://gnosisscan.io", apiKey, 100)
            )
        }

        fun fantom(apiKey: String): TransactionSource {
            return TransactionSource(
                "ftmscan.com",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://ftmscan.com", apiKey, 250)
            )
        }

        fun dexnet(apiKey: String = ""): TransactionSource {
            return TransactionSource(
                "dexnetchain.com",
                SourceType.Etherscan(
                    "https://explorer.dexnetchain.com/api/v2/",
                    "https://explorer.dexnetchain.com",
                    apiKey,
                    0  // Dexnet - custom explorer, chainId не используется
                )
            )
        }

    }

}
