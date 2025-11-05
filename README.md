# Ethereum Kit 0xWallet

[![](https://jitpack.io/v/0xLabWallet/etherium-kit-0xwallet.svg)](https://jitpack.io/#0xLabWallet/etherium-kit-0xwallet)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Version](https://img.shields.io/badge/Version-1.3-green.svg)](CHANGELOG.md)

> Мощная мультимодульная библиотека для работы с Ethereum и EVM-совместимыми блокчейнами

## 🆕 What's New in v1.3

- ✅ **Etherscan API V2** - unified endpoint for all networks
- ✅ **API Key Rotation** - automatic load distribution across multiple keys
- ✅ **chainId Support** - proper network identification
- ✅ **Namespace Support** - modern Android compatibility

See [CHANGELOG.md](CHANGELOG.md) for details.

---

## 🌟 Возможности

- ✅ **Ethereum Mainnet** и тестовые сети (Goerli)
- ✅ **Multi-chain**: BSC, Polygon, Optimism, Arbitrum, Avalanche, Gnosis, Fantom, DexNet
- ✅ **ERC-20 токены** - полная поддержка
- ✅ **NFT** - ERC-721 и ERC-1155
- ✅ **DeFi интеграции** - Uniswap V2/V3, 1inch
- ✅ **RxJava2** для реактивного программирования
- ✅ **Room Database** для локального хранения
- ✅ **Etherscan API** для всех поддерживаемых сетей

---

## 📦 Модули

| Модуль | Описание | Зависимости |
|--------|----------|-------------|
| [ethereumkit](#ethereumkit) | Основной модуль для работы с Ethereum | - |
| [erc20kit](#erc20kit) | Работа с ERC-20 токенами | ethereumkit |
| [nftkit](#nftkit) | Работа с NFT (ERC-721, ERC-1155) | ethereumkit |
| [oneinchkit](#oneinchkit) | Интеграция с 1inch DEX | ethereumkit, erc20kit |
| [uniswapkit](#uniswapkit) | Интеграция с Uniswap V2/V3 | ethereumkit, erc20kit |

---

## 🚀 Установка

### settings.gradle
```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### build.gradle

#### Все модули:
```gradle
dependencies {
    implementation 'com.github.0xLabWallet:etherium-kit-0xwallet:v1.3'
}
```

#### Отдельные модули:
```gradle
dependencies {
    // Основной модуль (обязательно)
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:ethereumkit:v1.3'
    
    // ERC-20 токены
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:erc20kit:v1.3'
    
    // NFT
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:nftkit:v1.3'
    
    // 1inch
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:oneinchkit:v1.3'
    
    // Uniswap
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:uniswapkit:v1.3'
}
```

---

## 📖 Использование

### EthereumKit

Основной модуль для работы с Ethereum blockchain.

#### Инициализация

```kotlin
import com.wallet0x.ethereumkit.core.EthereumKit
import com.wallet0x.ethereumkit.models.*

// 🆕 v1.3: Настройка TransactionSource с API V2 и ротацией ключей
// Рекомендуется использовать несколько API ключей для распределения нагрузки
val etherscanKeys = listOf(
    "YOUR_ETHERSCAN_API_KEY_1",
    "YOUR_ETHERSCAN_API_KEY_2",
    "YOUR_ETHERSCAN_API_KEY_3"
)
val transactionSource = TransactionSource.ethereumEtherscan(etherscanKeys)
// Библиотека автоматически выберет случайный ключ из списка

// Для одного ключа (также поддерживается):
val singleKey = listOf("YOUR_SINGLE_API_KEY")
val transactionSource = TransactionSource.ethereumEtherscan(singleKey)

// Или для других сетей с ротацией:
// val bscKeys = listOf("BSC_KEY_1", "BSC_KEY_2", "BSC_KEY_3")
// val transactionSource = TransactionSource.bscscan(bscKeys)

// Остальные сети (пока без ротации):
// val transactionSource = TransactionSource.polygonscan("YOUR_API_KEY")
// val transactionSource = TransactionSource.arbiscan("YOUR_API_KEY")
// val transactionSource = TransactionSource.dexnet() // Кастомная сеть DexNet

// Инициализация EthereumKit
val ethereumKit = EthereumKit.getInstance(
    context = applicationContext,
    words = listOf("your", "seed", "phrase", "..."),
    passphrase = "",
    networkType = NetworkType.EthMainNet,
    syncMode = SyncMode.ApiSyncMode(),
    transactionSource = transactionSource,
    walletId = "wallet_id"
)

// Запуск синхронизации
ethereumKit.start()
```

#### Получение баланса

```kotlin
// Текущий баланс
val balance = ethereumKit.accountState?.balance
println("Balance: ${balance} Wei")

// Подписка на изменения баланса
ethereumKit.accountStateFlowable
    .subscribe { state ->
        println("New balance: ${state.balance} Wei")
    }
```

#### Отправка транзакции

```kotlin
import java.math.BigInteger

// Адрес получателя
val toAddress = "0x742d35Cc6634C0532925a3b844Bc9e7595f0bEb"

// Сумма в Wei (1 ETH = 10^18 Wei)
val amount = BigInteger.valueOf(1000000000000000000) // 1 ETH

// Отправка ETH
ethereumKit.send(
    address = toAddress,
    value = amount,
    gasPrice = null, // автоматический расчёт
    gasLimit = null  // автоматический расчёт
).subscribe(
    { transaction ->
        println("Transaction sent: ${transaction.hash}")
    },
    { error ->
        println("Error: ${error.message}")
    }
)
```

#### Получение списка транзакций

```kotlin
// Подписка на транзакции
ethereumKit.transactionsFlowable
    .subscribe { transactions ->
        transactions.forEach { tx ->
            println("TX: ${tx.hash}, Value: ${tx.value}")
        }
    }

// Получить URL транзакции для просмотра
val txUrl = transactionSource.transactionUrl("0x1234...")
println("View TX: $txUrl")
```

#### Поддерживаемые сети (Etherscan API V2)

**🆕 v1.3: Все сети используют единый Etherscan API V2 endpoint с chainId**

```kotlin
// ✅ Ethereum Mainnet (chainId: 1) - с ротацией ключей
val ethKeys = listOf("KEY1", "KEY2", "KEY3")
TransactionSource.ethereumEtherscan(ethKeys)

// ✅ Binance Smart Chain (chainId: 56) - с ротацией ключей
val bscKeys = listOf("BSC_KEY1", "BSC_KEY2")
TransactionSource.bscscan(bscKeys)

// Остальные сети (пока без ротации):

// Goerli Testnet (chainId: 5)
TransactionSource.goerliEtherscan("API_KEY")

// Polygon (chainId: 137)
TransactionSource.polygonscan("API_KEY")

// Optimism (chainId: 10)
TransactionSource.optimisticEtherscan("API_KEY")

// Arbitrum (chainId: 42161)
TransactionSource.arbiscan("API_KEY")

// Avalanche (chainId: 43114)
TransactionSource.snowtrace("API_KEY")

// Gnosis Chain (chainId: 100)
TransactionSource.gnosis("API_KEY")

// Fantom (chainId: 250)
TransactionSource.fantom("API_KEY")

// DexNet (custom explorer, chainId: 0)
TransactionSource.dexnet() // API key опционален
```

**Преимущества API V2:**
- ✅ Единый endpoint для всех сетей
- ✅ Автоматическая ротация API ключей
- ✅ Улучшенная надёжность
- ✅ Защита от rate limiting

---

### ERC20Kit

Модуль для работы с ERC-20 токенами.

#### Инициализация

```kotlin
import com.wallet0x.erc20kit.core.Erc20Kit

val erc20Kit = Erc20Kit.getInstance(
    context = applicationContext,
    ethereumKit = ethereumKit,
    contractAddress = "0x..." // Адрес контракта токена
)

erc20Kit.start()
```

#### Получение баланса токена

```kotlin
// Текущий баланс токена
val tokenBalance = erc20Kit.balance
println("Token balance: $tokenBalance")

// Подписка на изменения
erc20Kit.balanceFlowable
    .subscribe { balance ->
        println("New token balance: $balance")
    }
```

#### Отправка токенов

```kotlin
import java.math.BigInteger

val toAddress = "0x742d35Cc6634C0532925a3b844Bc9e7595f0bEb"
val amount = BigInteger.valueOf(100000000000000000) // 0.1 token

erc20Kit.send(
    to = toAddress,
    value = amount,
    gasPrice = null,
    gasLimit = null
).subscribe(
    { transaction ->
        println("Token sent: ${transaction.hash}")
    },
    { error ->
        println("Error: ${error.message}")
    }
)
```

#### Approve токенов (для DeFi)

```kotlin
// Разрешить контракту использовать токены
val spenderAddress = "0x..." // Адрес DEX контракта
val approveAmount = BigInteger.valueOf(Long.MAX_VALUE) // Бесконечное разрешение

erc20Kit.approve(
    spenderAddress = spenderAddress,
    amount = approveAmount
).subscribe(
    { transaction ->
        println("Approve successful: ${transaction.hash}")
    },
    { error ->
        println("Error: ${error.message}")
    }
)
```

#### Проверка allowance

```kotlin
// Проверить разрешённую сумму
val spenderAddress = "0x..."

erc20Kit.getAllowance(spenderAddress)
    .subscribe { allowance ->
        println("Allowance: $allowance")
    }
```

---

### NFTKit

Модуль для работы с NFT (ERC-721 и ERC-1155).

#### Инициализация

```kotlin
import com.wallet0x.nftkit.core.NftKit

val nftKit = NftKit.getInstance(
    context = applicationContext,
    ethereumKit = ethereumKit
)

nftKit.start()
```

#### Получение NFT коллекции

```kotlin
// Получить все NFT пользователя
nftKit.nftBalancesFlowable
    .subscribe { balances ->
        balances.forEach { nft ->
            println("NFT: ${nft.contractAddress}, Token ID: ${nft.tokenId}")
        }
    }
```

#### Отправка NFT

```kotlin
// ERC-721
val contractAddress = "0x..." // Адрес NFT контракта
val toAddress = "0x..." // Адрес получателя
val tokenId = BigInteger.valueOf(1234)

nftKit.transferErc721(
    contractAddress = contractAddress,
    to = toAddress,
    tokenId = tokenId
).subscribe(
    { transaction ->
        println("NFT transferred: ${transaction.hash}")
    },
    { error ->
        println("Error: ${error.message}")
    }
)
```

---

### UniswapKit

Модуль для работы с Uniswap V2 и V3.

#### Инициализация

```kotlin
import com.wallet0x.uniswapkit.UniswapKit

val uniswapKit = UniswapKit.getInstance(
    ethereumKit = ethereumKit
)
```

#### Swap токенов (Uniswap V2)

```kotlin
import com.wallet0x.uniswapkit.models.*

// Настройка swap
val tokenIn = "0x..." // Адрес входного токена (или ETH)
val tokenOut = "0x..." // Адрес выходного токена
val amountIn = BigInteger.valueOf(1000000000000000000) // 1 token
val slippage = 0.5 // 0.5% slippage

// Получить расчётную сумму
uniswapKit.getAmountsOut(
    amountIn = amountIn,
    path = listOf(tokenIn, tokenOut)
).subscribe { amounts ->
    val amountOut = amounts.last()
    println("You will receive: $amountOut")
    
    // Выполнить swap
    uniswapKit.swap(
        tokenIn = tokenIn,
        tokenOut = tokenOut,
        amountIn = amountIn,
        amountOutMin = amountOut.multiply(BigInteger.valueOf((100 - slippage).toLong())).divide(BigInteger.valueOf(100)),
        recipient = ethereumKit.receiveAddress,
        deadline = System.currentTimeMillis() / 1000 + 3600 // 1 час
    ).subscribe { transaction ->
        println("Swap completed: ${transaction.hash}")
    }
}
```

---

### OneInchKit

Модуль для работы с 1inch DEX Aggregator.

#### Инициализация

```kotlin
import com.wallet0x.oneinchkit.OneInchKit

val oneInchKit = OneInchKit.getInstance(
    ethereumKit = ethereumKit
)
```

#### Получение лучшей цены

```kotlin
// Получить лучшую цену через 1inch
val fromToken = "0x..." // USDT
val toToken = "0x..." // DAI
val amount = BigInteger.valueOf(1000000) // 1 USDT

oneInchKit.getQuote(
    fromTokenAddress = fromToken,
    toTokenAddress = toToken,
    amount = amount
).subscribe { quote ->
    println("Best rate: ${quote.toTokenAmount}")
    println("Via: ${quote.protocols}")
}
```

#### Выполнение swap через 1inch

```kotlin
oneInchKit.swap(
    fromTokenAddress = fromToken,
    toTokenAddress = toToken,
    amount = amount,
    fromAddress = ethereumKit.receiveAddress,
    slippage = 1 // 1%
).subscribe { transaction ->
    println("Swap via 1inch: ${transaction.hash}")
}
```

---

## 🔧 Дополнительные возможности

### Расчёт Gas

```kotlin
// Получить текущую цену газа
ethereumKit.getGasPrice()
    .subscribe { gasPrice ->
        println("Gas price: $gasPrice Gwei")
    }

// Расчёт стоимости транзакции
val gasLimit = BigInteger.valueOf(21000) // Стандартная транзакция ETH
val txCost = gasPrice.multiply(gasLimit)
println("TX cost: $txCost Wei")
```

### Управление nonce

```kotlin
// Получить текущий nonce
val nonce = ethereumKit.getNonce()
println("Current nonce: $nonce")
```

### Подпись сообщений

```kotlin
// Подписать произвольное сообщение
val message = "Hello, Ethereum!"
val signature = ethereumKit.sign(message.toByteArray())
println("Signature: ${signature.toHexString()}")
```

---

## 🎯 Примеры использования

### Полный пример: Отправка ERC-20 токена

```kotlin
import com.wallet0x.ethereumkit.core.EthereumKit
import com.wallet0x.erc20kit.core.Erc20Kit
import com.wallet0x.ethereumkit.models.*
import io.reactivex.disposables.CompositeDisposable

class TokenTransferExample(val context: Context) {
    
    private val disposables = CompositeDisposable()
    
    fun sendToken() {
        // 1. Инициализация EthereumKit с API V2 (v1.3)
        val apiKeys = listOf("KEY1", "KEY2", "KEY3") // 🆕 Ротация ключей
        val transactionSource = TransactionSource.ethereumEtherscan(apiKeys)
        val ethereumKit = EthereumKit.getInstance(
            context = context,
            words = listOf("your", "seed", "phrase"),
            passphrase = "",
            networkType = NetworkType.EthMainNet,
            syncMode = SyncMode.ApiSyncMode(),
            transactionSource = transactionSource,
            walletId = "wallet1"
        )
        ethereumKit.start()
        
        // 2. Инициализация Erc20Kit для USDT
        val usdtContract = "0xdAC17F958D2ee523a2206206994597C13D831ec7"
        val erc20Kit = Erc20Kit.getInstance(
            context = context,
            ethereumKit = ethereumKit,
            contractAddress = usdtContract
        )
        erc20Kit.start()
        
        // 3. Проверка баланса
        disposables.add(
            erc20Kit.balanceFlowable
                .subscribe { balance ->
                    if (balance > BigInteger.ZERO) {
                        // 4. Отправка токенов
                        val toAddress = "0x742d35Cc6634C0532925a3b844Bc9e7595f0bEb"
                        val amount = BigInteger.valueOf(1000000) // 1 USDT (6 decimals)
                        
                        erc20Kit.send(toAddress, amount, null, null)
                            .subscribe(
                                { tx -> 
                                    println("Success! TX: ${tx.hash}")
                                },
                                { error -> 
                                    println("Error: ${error.message}")
                                }
                            )
                    }
                }
        )
    }
    
    fun cleanup() {
        disposables.clear()
    }
}
```

---

## ⚙️ Требования

- **Android:** API 26+ (Android 8.0 Oreo)
- **Kotlin:** 1.7.0+
- **Java:** 11+

---

## 📝 Лицензия

MIT License - см. [LICENSE](LICENSE) файл

---

## 🤝 Вклад в проект

Мы приветствуем вклад в проект! Пожалуйста:
1. Форкните репозиторий
2. Создайте feature branch (`git checkout -b feature/AmazingFeature`)
3. Закоммитьте изменения (`git commit -m 'Add AmazingFeature'`)
4. Запушьте в branch (`git push origin feature/AmazingFeature`)
5. Откройте Pull Request

---

## 📞 Поддержка

- **GitHub Issues:** [Открыть issue](https://github.com/0xLabWallet/etherium-kit-0xwallet/issues)
- **Documentation:** [Publishing Guide](PUBLISHING_GUIDE.md)
- **JitPack Status:** [View builds](https://jitpack.io/#0xLabWallet/etherium-kit-0xwallet)

---

## 🔗 Полезные ссылки

- [Ethereum Documentation](https://ethereum.org/developers)
- [Web3j Library](https://docs.web3j.io/)
- [Etherscan API](https://docs.etherscan.io/)
- [JitPack Documentation](https://docs.jitpack.io/)

---

**Made with ❤️ by 0xLab Wallet Team**

[![GitHub stars](https://img.shields.io/github/stars/0xLabWallet/etherium-kit-0xwallet?style=social)](https://github.com/0xLabWallet/etherium-kit-0xwallet)

