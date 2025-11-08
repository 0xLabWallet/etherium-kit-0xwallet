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
        // ═══════════════════════════════════════════════════════════════════════════
        // Etherscan API V2 - используется ТОЛЬКО для Ethereum и BSC
        // Остальные сети используют свои собственные API V1 endpoints
        // ═══════════════════════════════════════════════════════════════════════════
        private const val ETHERSCAN_API_V2_BASE_URL = "https://api.etherscan.io"

        // ✅ Ethereum Mainnet - API V2 с chainId=1
        fun ethereumEtherscan(apiKey: List<String>): TransactionSource {
            return TransactionSource(
                "etherscan.io",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://etherscan.io", apiKey.random(), 1)
            )
        }

        // ✅ Goerli Testnet - API V2 с chainId=5
        fun goerliEtherscan(apiKey: String): TransactionSource {
            return TransactionSource(
                "etherscan.io",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://goerli.etherscan.io", apiKey, 5)
            )
        }

        // ✅ BSC - API V2 с chainId=56
        fun bscscan(apiKey: List<String>): TransactionSource {
            return TransactionSource(
                "bscscan.com",
                SourceType.Etherscan(ETHERSCAN_API_V2_BASE_URL, "https://bscscan.com", apiKey.random(), 56)
            )
        }

        // ✅ Polygon - свой API V1 (chainId=0 означает не использовать V2)
        fun polygonscan(apiKey: String): TransactionSource {
            return TransactionSource(
                "polygonscan.com",
                SourceType.Etherscan("https://api.polygonscan.com", "https://polygonscan.com", apiKey, 0)
            )
        }

        // ✅ Optimism - свой API V1
        fun optimisticEtherscan(apiKey: String): TransactionSource {
            return TransactionSource(
                "optimistic.etherscan.io",
                SourceType.Etherscan("https://api-optimistic.etherscan.io", "https://optimistic.etherscan.io", apiKey, 0)
            )
        }

        // ✅ Arbitrum - свой API V1
        fun arbiscan(apiKey: String): TransactionSource {
            return TransactionSource(
                "arbiscan.io",
                SourceType.Etherscan("https://api.arbiscan.io", "https://arbiscan.io", apiKey, 0)
            )
        }

        // ✅ Avalanche - свой API V1
        fun snowtrace(apiKey: String): TransactionSource {
            return TransactionSource(
                "snowtrace.io",
                SourceType.Etherscan("https://api.snowtrace.io", "https://snowtrace.io", apiKey, 0)
            )
        }

        // ✅ Gnosis - свой API V1
        fun gnosis(apiKey: String): TransactionSource {
            return TransactionSource(
                "gnosisscan.io",
                SourceType.Etherscan("https://api.gnosisscan.io", "https://gnosisscan.io", apiKey, 0)
            )
        }

        // ✅ Fantom - свой API V1
        fun fantom(apiKey: String): TransactionSource {
            return TransactionSource(
                "ftmscan.com",
                SourceType.Etherscan("https://api.ftmscan.com", "https://ftmscan.com", apiKey, 0)
            )
        }

        // ✅ Dexnet - custom explorer
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
