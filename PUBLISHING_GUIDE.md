# 📚 Ethereum Kit 0xWallet - Руководство по публикации на JitPack

> Пошаговая инструкция по публикации мультимодульной библиотеки ethereum-kit-0xwallet через JitPack

## 🆕 Version 1.3 Updates

- ✅ Migrated to Etherscan API V2
- ✅ Added API key rotation support
- ✅ Added namespace to all modules
- ✅ Improved rate limit handling

See [CHANGELOG.md](CHANGELOG.md) for full details.

---

## 📦 О проекте

**Ethereum Kit 0xWallet** - это мультимодульная библиотека для работы с Ethereum и EVM-совместимыми блокчейнами.

### Модули библиотеки:

| Модуль | Описание | Зависимости |
|--------|----------|-------------|
| **ethereumkit** | Основной модуль для работы с Ethereum | - |
| **erc20kit** | Работа с ERC-20 токенами | ethereumkit |
| **nftkit** | Работа с NFT (ERC-721, ERC-1155) | ethereumkit |
| **oneinchkit** | Интеграция с 1inch DEX | ethereumkit, erc20kit |
| **uniswapkit** | Интеграция с Uniswap V2/V3 | ethereumkit, erc20kit |

---

## ⚙️ Технические характеристики

- **Язык:** Kotlin 1.7.10
- **Gradle:** 7.2
- **Android Gradle Plugin:** 7.1.3
- **compileSdk:** 34
- **minSdk:** 26
- **targetSdk:** 34
- **Java:** 11 (runtime), 17 (JitPack build)

---

## 🚀 Шаги публикации на JitPack

### Шаг 1: Подготовка проекта ✅

Все файлы уже настроены:
- ✅ Maven-publish плагин добавлен во все модули
- ✅ Конфигурация `android.publishing` с `singleVariant("release")`
- ✅ Публикации настроены с groupId, artifactId и version
- ✅ jitpack.yml обновлён (Java 17)

### Шаг 2: Тестирование локально

Перед публикацией проверьте сборку локально:

```bash
# Перейдите в директорию проекта
cd etherium-kit-0xwallet-1.2

# Очистка и сборка
./gradlew clean build

# Публикация в Maven Local
./gradlew publishToMavenLocal
```

Проверьте файлы в `~/.m2/repository/com/github/0xLabWallet/`:
- `ethereumkit/1.2/`
- `erc20kit/1.2/`
- `nftkit/1.2/`
- `oneinchkit/1.2/`
- `uniswapkit/1.2/`

### Шаг 3: Загрузка на GitHub

```bash
# Инициализируйте Git (если ещё не сделано)
git init

# Добавьте удалённый репозиторий
git remote add origin https://github.com/0xLabWallet/etherium-kit-0xwallet.git

# Добавьте все файлы
git add .

# Сделайте коммит
git commit -m "Update publishing configuration for JitPack v1.2"

# Отправьте на GitHub
git push -u origin main
```

### Шаг 4: Создание релиза

#### Вариант A: Через Git командную строку

```bash
# Создайте тег версии
git tag -a v1.3 -m "Release version 1.3 - Etherscan API V2 + Key Rotation"

# Отправьте тег на GitHub
git push origin v1.3
```

#### Вариант B: Через GitHub веб-интерфейс

1. Откройте репозиторий на GitHub
2. Перейдите в **Releases** → **Create a new release**
3. **Tag version:** `v1.3`
4. **Release title:** `Version 1.3 - Etherscan API V2 + Key Rotation`
5. **Description:** 
   ```markdown
   ## 🚀 Major Improvements
   
   ### Etherscan API V2
   - Migrated to unified Etherscan API V2 endpoint
   - Added chainId support for all networks
   - Improved reliability and performance
   
   ### API Key Rotation
   - Support for multiple API keys
   - Automatic load distribution
   - Better rate limit handling
   
   ### Technical Updates
   - Added namespace to all modules
   - Updated version to 1.3
   - Enhanced documentation
   
   ## 📦 Modules
   - ethereumkit v1.3
   - erc20kit v1.3
   - nftkit v1.3
   - oneinchkit v1.3
   - uniswapkit v1.3
   
   ## 📖 Documentation
   - [README.md](README.md) - Usage examples
   - [CHANGELOG.md](CHANGELOG.md) - Full changelog
   - [PUBLISHING_GUIDE.md](PUBLISHING_GUIDE.md) - Publication guide
   
   ## 🔄 Migration from v1.2
   See [CHANGELOG.md](CHANGELOG.md#migration-guide) for migration instructions.
   ```
6. Нажмите **Publish release**

### Шаг 5: Сборка на JitPack

1. Откройте https://jitpack.io
2. Введите: `https://github.com/0xLabWallet/etherium-kit-0xwallet`
3. Нажмите **Look up**
4. Найдите версию `v1.3` и нажмите **Get it**
5. Дождитесь завершения сборки (статус станет зелёным ✅)

**Просмотр логов:**
```
https://jitpack.io/com/github/0xLabWallet/etherium-kit-0xwallet/v1.3/build.log
```

---

## 📲 Использование библиотеки

### Настройка проекта

#### settings.gradle:
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

### Подключение модулей

#### Вариант 1: Все модули сразу
```gradle
dependencies {
    implementation 'com.github.0xLabWallet:etherium-kit-0xwallet:v1.2'
}
```

#### Вариант 2: Отдельные модули

**Только ethereumkit:**
```gradle
dependencies {
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:ethereumkit:v1.2'
}
```

**ethereumkit + erc20kit:**
```gradle
dependencies {
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:ethereumkit:v1.2'
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:erc20kit:v1.2'
}
```

**Полный набор:**
```gradle
dependencies {
    // Основной модуль
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:ethereumkit:v1.2'
    
    // ERC-20 токены
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:erc20kit:v1.2'
    
    // NFT поддержка
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:nftkit:v1.2'
    
    // 1inch DEX
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:oneinchkit:v1.2'
    
    // Uniswap V2/V3
    implementation 'com.github.0xLabWallet.etherium-kit-0xwallet:uniswapkit:v1.2'
}
```

---

## 🔧 Troubleshooting

### Проблема: "Could not find com.github.0xLabWallet..."

**Решение:**
1. Проверьте статус сборки на JitPack (должен быть зелёным)
2. Очистите кэш Gradle:
   ```bash
   ./gradlew clean build --refresh-dependencies
   ```
3. Проверьте правильность имени репозитория и версии

### Проблема: Сборка на JitPack не удалась

**Решение:**
1. Проверьте логи: `https://jitpack.io/.../build.log`
2. Убедитесь, что проект собирается локально
3. Авторизуйтесь на JitPack и удалите неуспешную сборку
4. Попробуйте снова

### Проблема: Конфликт зависимостей

**Решение:**
Если в вашем проекте уже есть зависимости от тех же библиотек (Room, RxJava и т.д.), используйте:
```gradle
configurations.all {
    resolutionStrategy {
        force 'androidx.room:room-runtime:2.4.3'
        force 'io.reactivex.rxjava2:rxjava:2.2.19'
    }
}
```

---

## 📊 Поддерживаемые блокчейны

Библиотека поддерживает следующие сети:

- ✅ Ethereum Mainnet
- ✅ Ethereum Goerli (testnet)
- ✅ Binance Smart Chain (BSC)
- ✅ Polygon (Matic)
- ✅ Optimism
- ✅ Arbitrum
- ✅ Avalanche (C-Chain)
- ✅ Gnosis Chain (xDai)
- ✅ Fantom Opera
- ✅ **DexNet** (кастомная сеть)

---

## 🔄 Обновление версии

Для публикации новой версии:

1. Обновите `version` во всех `build.gradle` модулей:
   ```gradle
   version = '1.3'  // новая версия
   ```

2. Создайте новый тег:
   ```bash
   git tag -a v1.3 -m "Release version 1.3"
   git push origin v1.3
   ```

3. JitPack автоматически начнёт сборку новой версии

---

## 📞 Поддержка

- **GitHub Issues:** https://github.com/0xLabWallet/etherium-kit-0xwallet/issues
- **JitPack Status:** https://jitpack.io/#0xLabWallet/etherium-kit-0xwallet

---

## ✅ Checklist публикации

- [ ] Проект собирается локально: `./gradlew clean build`
- [ ] Публикация в mavenLocal работает: `./gradlew publishToMavenLocal`
- [ ] Все изменения закоммичены и запушены на GitHub
- [ ] Создан Git тег с версией (например, `v1.2`)
- [ ] Тег отправлен на GitHub: `git push origin v1.2`
- [ ] Сборка на JitPack запущена
- [ ] Статус сборки на JitPack зелёный ✅
- [ ] Библиотека успешно подключается в тестовом проекте

---

**Версия документа:** 1.0  
**Дата создания:** 05.11.2025  
**Библиотека:** ethereum-kit-0xwallet v1.2

---

*Документация создана на основе [JitPack Documentation](https://docs.jitpack.io/android/)*

