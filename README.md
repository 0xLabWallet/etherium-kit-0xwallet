# Ethereum Kit Android

[![](https://jitpack.io/v/0xLabWallet/etherium-kit-0xwallet.svg)](https://jitpack.io/#0xLabWallet/etherium-kit-0xwallet)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Comprehensive Ethereum blockchain integration library for Android applications. This library provides a complete set of tools for interacting with Ethereum and EVM-compatible networks.

## 🌟 Features

- ✅ Full Ethereum and EVM-compatible networks support
- ✅ ERC20 token management
- ✅ NFT support (ERC721/ERC1155)
- ✅ Uniswap V2/V3 integration
- ✅ 1inch DEX aggregator integration
- ✅ Transaction management and signing
- ✅ Smart contract interaction
- ✅ RxJava2 reactive streams
- ✅ Room database for caching
- ✅ EIP-1559 support (dynamic gas fees)
- ✅ Multiple RPC providers support

## 📦 Modules

### **ethereumkit** - Core Ethereum functionality
- Blockchain connectivity via RPC
- Transaction management and signing
- Account state management
- Gas price estimation
- Smart contract calls

### **erc20kit** - ERC20 Token Support
- Token balance tracking
- Transfer operations
- Approve/Allowance management
- Transaction history

### **nftkit** - NFT Support
- ERC721 and ERC1155 support
- NFT balance and metadata
- Transfer operations

### **uniswapkit** - Uniswap Integration
- Uniswap V2/V3 swaps
- Price quotes
- Liquidity operations

### **oneinchkit** - 1inch Integration
- Best price aggregation
- Multi-protocol swaps
- Gas optimization

## 📥 Installation

Add JitPack repository to your root `build.gradle`:

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Add dependencies to your app `build.gradle`:

```gradle
dependencies {
    // Core Ethereum functionality
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:ethereumkit:1.5'
    
    // ERC20 tokens (depends on ethereumkit)
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:erc20kit:1.5'
    
    // NFT support (depends on ethereumkit)
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:nftkit:1.5'
    
    // Uniswap integration (depends on ethereumkit, erc20kit)
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:uniswapkit:1.5'
    
    // 1inch integration (depends on ethereumkit, erc20kit)
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:oneinchkit:1.5'
}
```

## 🚀 Quick Start

### Initialize Ethereum Kit

```kotlin
import com.wallet0x.ethereumkit.core.EthereumKit
import com.wallet0x.ethereumkit.models.Chain
import com.wallet0x.ethereumkit.models.RpcSource
import com.wallet0x.ethereumkit.models.TransactionSource

// Initialize the library
EthereumKit.init()

// Create an instance
val ethereumKit = EthereumKit.getInstance(
    application = application,
    words = listOf("word1", "word2", ...), // Mnemonic phrase
    passphrase = "",
    chain = Chain.Ethereum,
    rpcSource = RpcSource.Http(listOf("https://mainnet.infura.io/v3/YOUR_KEY")),
    transactionSource = TransactionSource.ethereumEtherscan("YOUR_ETHERSCAN_KEY"),
    walletId = "unique-wallet-id"
)

// Start syncing
ethereumKit.start()
```

### Work with ERC20 Tokens

```kotlin
import com.wallet0x.erc20kit.core.Erc20Kit
import com.wallet0x.ethereumkit.models.Address

// Initialize ERC20 kit
val erc20Kit = Erc20Kit.getInstance(
    context = context,
    ethereumKit = ethereumKit,
    contractAddress = Address("0x...") // Token contract address
)

// Start syncing
erc20Kit.start()

// Get balance
val balance = erc20Kit.balance // BigInteger

// Build transfer transaction
val transactionData = erc20Kit.buildTransferTransactionData(
    to = Address("0x..."),
    value = BigInteger("1000000000000000000") // 1 token with 18 decimals
)

// Send transaction through EthereumKit
val gasPrice = ... // Get gas price
val gasLimit = ... // Estimate gas
val rawTransaction = ethereumKit.rawTransaction(transactionData, gasPrice, gasLimit).blockingGet()
val signature = ... // Sign transaction
ethereumKit.send(rawTransaction, signature).subscribe()
```

### Swap tokens with Uniswap

```kotlin
import com.wallet0x.uniswapkit.UniswapKit
import com.wallet0x.uniswapkit.models.TradeOptions

val uniswapKit = UniswapKit.getInstance(ethereumKit)

// Get swap quote
val tradeData = uniswapKit.swapData(
    tokenIn = tokenInAddress,
    tokenOut = tokenOutAddress,
    amountIn = amount,
    tradeOptions = TradeOptions()
).blockingGet()

// Execute swap
val transactionData = tradeData.transactionData
// ... send transaction
```

## 🔧 Configuration

### Supported Networks

```kotlin
// Ethereum Mainnet
Chain.Ethereum

// Binance Smart Chain
Chain.BinanceSmartChain

// Polygon
Chain.Polygon

// Arbitrum
Chain.ArbitrumOne

// Optimism
Chain.Optimistic

// Custom network
Chain.custom(
    id = 1,
    coinType = 60,
    syncInterval = 15,
    isEIP1559Supported = true
)
```

### RPC Sources

```kotlin
// HTTP RPC
RpcSource.Http(
    uris = listOf("https://mainnet.infura.io/v3/YOUR_KEY"),
    auth = null // Optional authentication
)

// WebSocket RPC
RpcSource.WebSocket(
    uri = URI("wss://mainnet.infura.io/ws/v3/YOUR_KEY"),
    auth = null
)
```

## 📊 Observing Changes

```kotlin
// Observe sync state
ethereumKit.syncStateFlowable
    .subscribe { syncState ->
        when (syncState) {
            is EthereumKit.SyncState.Synced -> { /* Synced */ }
            is EthereumKit.SyncState.Syncing -> { /* Syncing */ }
            is EthereumKit.SyncState.NotSynced -> { /* Error */ }
        }
    }

// Observe balance changes
ethereumKit.accountStateFlowable
    .subscribe { accountState ->
        val balance = accountState.balance
    }

// Observe transactions
ethereumKit.allTransactionsFlowable
    .subscribe { (transactions, initial) ->
        // Handle transactions
    }
```

## 🔐 Security

- Private keys are never exposed
- Mnemonic phrases should be stored securely
- Use hardware-backed keystore when possible
- Validate all user inputs
- Use HTTPS/WSS for RPC connections

## 📝 Requirements

- Android SDK 26+
- Java 11+
- Kotlin 1.8.0+

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

This library is available under the MIT License.

```
MIT License

Copyright (c) 2025 0xLab Wallet

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 📞 Support

For questions and support, please open an issue on GitHub.

## 🔗 Links

- [GitHub Repository](https://github.com/0xLabWallet/etherium-kit-0xwallet)
- [JitPack](https://jitpack.io/#0xLabWallet/etherium-kit-0xwallet)

## ⭐ Star History

If you find this library useful, please give it a star!

