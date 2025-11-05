# Changelog - Ethereum Kit 0xWallet

All notable changes to this project will be documented in this file.

---

## [1.4] - 2025-11-05

### 🎯 **Version Alignment**

- Published as version **1.4** (without "v" prefix) for proper Gradle convention
- Identical features and improvements as v1.3
- Same codebase, different tag for JitPack compatibility

**Usage:**
```gradle
implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:ethereumkit:1.4'
```

All features from 1.3 included (see below).

---

## [1.3] - 2025-11-05

### 🚀 **Major Improvements**

#### ⭐ Etherscan API V2 Migration
- **BREAKING CHANGE**: Migrated from Etherscan API V1 to API V2
- Unified API endpoint for all networks: `https://api.etherscan.io`
- Added `chainId` parameter to `SourceType.Etherscan`
- All transactions now use chainId for network identification

#### 🔄 API Key Rotation Support
- Added support for multiple API keys with automatic rotation
- `ethereumEtherscan()` now accepts `List<String>` instead of single `String`
- `bscscan()` now accepts `List<String>` instead of single `String`
- Randomly selects API key from the list to distribute load and prevent rate limiting

### ✨ **New Features**

#### Namespace Support
- Added `namespace` to all modules for better compatibility with modern Android:
  - `com.wallet0x.ethereumkit`
  - `com.wallet0x.erc20kit`
  - `com.wallet0x.nftkit`
  - `com.wallet0x.oneinchkit`
  - `com.wallet0x.uniswapkit`

### 🔧 **Technical Improvements**

#### TransactionSource Changes
```kotlin
// OLD (v1.2):
val source = TransactionSource.ethereumEtherscan("YOUR_API_KEY")

// NEW (v1.3):
val apiKeys = listOf("KEY1", "KEY2", "KEY3")
val source = TransactionSource.ethereumEtherscan(apiKeys)
```

#### API Endpoint Changes
```kotlin
// OLD (v1.2):
// Each network had its own subdomain:
// api.etherscan.io, api-goerli.etherscan.io, api.bscscan.com

// NEW (v1.3):
// Unified endpoint with chainId:
private const val ETHERSCAN_API_V2_BASE_URL = "https://api.etherscan.io"
// Uses chainId to identify network: 1 (ETH), 56 (BSC), 137 (Polygon), etc.
```

### 📦 **Updated Dependencies**

All modules now include:
- JitPack publication ready
- Source JAR generation
- Javadoc JAR generation
- Maven coordinates: `com.github.0xLabWallet:etherium-kit-0xwallet:v1.3`

### 🎯 **Supported Networks**

All networks now use unified Etherscan API V2:
- ✅ Ethereum Mainnet (chainId: 1)
- ✅ Goerli Testnet (chainId: 5)
- ✅ Binance Smart Chain (chainId: 56)
- ✅ Polygon (chainId: 137)
- ✅ Optimism (chainId: 10)
- ✅ Arbitrum (chainId: 42161)
- ✅ Avalanche (chainId: 43114)
- ✅ Gnosis Chain (chainId: 100)
- ✅ Fantom (chainId: 250)
- ✅ DexNet (custom explorer, chainId: 0)

### ⚠️ **Breaking Changes**

#### Method Signature Changes

**ethereumEtherscan:**
```kotlin
// v1.2
fun ethereumEtherscan(apiKey: String): TransactionSource

// v1.3
fun ethereumEtherscan(apiKey: List<String>): TransactionSource
```

**bscscan:**
```kotlin
// v1.2
fun bscscan(apiKey: String): TransactionSource

// v1.3
fun bscscan(apiKey: List<String>): TransactionSource
```

#### SourceType.Etherscan Constructor

```kotlin
// v1.2
class Etherscan(
    val apiBaseUrl: String, 
    val txBaseUrl: String, 
    val apiKey: String
) : SourceType()

// v1.3
class Etherscan(
    val apiBaseUrl: String, 
    val txBaseUrl: String, 
    val apiKey: String, 
    val chainId: Int  // NEW
) : SourceType()
```

### 📝 **Migration Guide**

#### From v1.2 to v1.3

**Single API Key:**
```kotlin
// v1.2
val source = TransactionSource.ethereumEtherscan("YOUR_API_KEY")

// v1.3 (single key as list)
val source = TransactionSource.ethereumEtherscan(listOf("YOUR_API_KEY"))
```

**Multiple API Keys (Recommended):**
```kotlin
// v1.3 (with rotation)
val apiKeys = listOf(
    "ETHERSCAN_KEY_1",
    "ETHERSCAN_KEY_2",
    "ETHERSCAN_KEY_3"
)
val source = TransactionSource.ethereumEtherscan(apiKeys)
// Automatically rotates between keys
```

### 🐛 **Bug Fixes**

- Fixed API rate limiting issues with key rotation
- Improved error handling for network requests
- Better compatibility with Etherscan API V2 response format

### 📚 **Documentation**

- Updated README with API V2 examples
- Added comprehensive CHANGELOG
- Enhanced PUBLISHING_GUIDE with v1.3 instructions
- Added migration guide for existing users

---

## [1.2] - 2024-XX-XX

### Initial JitPack Release

- Added maven-publish configuration
- Added jitpack.yml for Java 17
- Created comprehensive documentation
- Support for 10 EVM-compatible networks
- All 5 modules ready for publication:
  - ethereumkit
  - erc20kit
  - nftkit
  - oneinchkit
  - uniswapkit

---

## Migration Path

### v1.2 → v1.3

**Impact:** MEDIUM
**Effort:** LOW

**Required Changes:**
1. Update API key parameters to `List<String>`
2. Test with updated API V2 endpoints
3. Verify chainId compatibility

**Benefits:**
- ✅ Better rate limit handling
- ✅ Improved reliability
- ✅ Future-proof API structure
- ✅ Automatic key rotation

---

## Links

- **Repository:** https://github.com/0xLabWallet/etherium-kit-0xwallet
- **JitPack:** https://jitpack.io/#0xLabWallet/etherium-kit-0xwallet
- **Documentation:** [README.md](README.md)
- **Publishing Guide:** [PUBLISHING_GUIDE.md](PUBLISHING_GUIDE.md)

---

**Version:** 1.3  
**Release Date:** 2025-11-05  
**JitPack Tag:** `v1.3`

