# Changelog

All notable changes to the Ethereum Kit Android library will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.5] - 2025-11-08

### 🎉 First Public Release via JitPack

This is the first public release of Ethereum Kit Android, published through JitPack for easy integration.

### ✨ Added
- **Public Maven publication** - Library is now available via JitPack
- **Complete documentation** - Added comprehensive README with usage examples
- **Unified version management** - All modules now share the same version number
- **Enhanced POM metadata** - Better Maven Central compatibility

### 🔧 Changed
- **Reorganized project structure** - Prepared for public GitHub repository
- **Updated build configuration** - Optimized for JitPack building
- **Version bump to 1.5** - Marking the transition to public release

### 📦 Modules Included

#### ethereumkit (Core Module)
- Full Ethereum and EVM-compatible blockchain support
- Transaction management and signing
- RPC connectivity (HTTP and WebSocket)
- EIP-1559 gas price support
- Smart contract interaction
- Account state management

#### erc20kit
- ERC20 token balance tracking
- Transfer and approve operations
- Allowance management
- Transaction synchronization

#### nftkit
- ERC721 and ERC1155 NFT support
- NFT balance and metadata retrieval
- NFT transfer operations

#### uniswapkit
- Uniswap V2 and V3 integration
- Token swap functionality
- Price quote calculation
- Liquidity operations

#### oneinchkit
- 1inch DEX aggregator integration
- Best price discovery
- Multi-protocol swap support
- Gas optimization

### 🛠️ Technical Details
- **Kotlin**: 1.8.0
- **Min SDK**: 26 (Android 8.0)
- **Target SDK**: 34 (Android 14)
- **Java**: 11
- **Room Database**: 2.5.2
- **RxJava**: 2.2.19
- **Coroutines**: 1.6.4

### 📚 Dependencies
- BouncyCastle for cryptography
- Web3j for Ethereum ABI encoding
- Retrofit2 for HTTP networking
- Room for local database
- RxJava2 for reactive programming

### 🔐 Security
- Secure key management
- Hardware-backed keystore support
- Mnemonic phrase handling
- Transaction signing

### 🌐 Supported Networks
- Ethereum Mainnet
- Binance Smart Chain
- Polygon
- Arbitrum One
- Optimism
- Custom EVM networks

---

## [1.4] - Previous Internal Version

### Features
- Internal version used in Maneki Wallet project
- All core functionality implemented
- Local library integration

### Notes
- This version was used internally and not published publicly
- Served as the foundation for version 1.5

---

## Future Plans

### [1.6] - Planned
- [ ] Enhanced error handling
- [ ] More comprehensive logging
- [ ] Additional DEX integrations
- [ ] Layer 2 optimizations
- [ ] Improved gas estimation

### [2.0] - Future
- [ ] Kotlin Coroutines Flow migration
- [ ] Jetpack Compose support
- [ ] Enhanced caching strategies
- [ ] WebSocket reconnection improvements
- [ ] Advanced smart contract features

---

## How to Update

When updating to a new version, simply change the version number in your `build.gradle`:

```gradle
// From
implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:ethereumkit:1.4'

// To
implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:ethereumkit:1.5'
```

## Breaking Changes

None in version 1.5 - this version is fully backward compatible with 1.4 for projects that were using it locally.

## Migration Guide

### From Local Integration to JitPack

If you were using this library as a local module:

**Before:**
```gradle
// settings.gradle
include ':ethereumkit'
project(':ethereumkit').projectDir = new File('libs/ethereum-kits/ethereumkit')

// app/build.gradle
implementation project(':ethereumkit')
```

**After:**
```gradle
// root build.gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

// app/build.gradle
implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:ethereumkit:1.5'
```

No code changes required - all APIs remain the same!

