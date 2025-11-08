# 📦 Publishing Guide - Ethereum Kit Android

Пошаговая инструкция по публикации библиотеки на GitHub и JitPack.

## 📋 Предварительные требования

- [x] Аккаунт на GitHub
- [x] Git установлен локально
- [x] Доступ к репозиторию 0xLabWallet

## 🚀 Шаг 1: Подготовка репозитория на GitHub

### 1.1 Создайте новый репозиторий

**ПРИМЕЧАНИЕ**: Репозиторий уже существует по адресу:
https://github.com/0xLabWallet/etherium-kit-0xwallet

Мы будем обновлять существующий репозиторий до версии 1.5.

## 🔧 Шаг 2: Инициализация Git репозитория

Откройте PowerShell в папке `D:\Projects\ethereum-kit-android` и выполните:

```powershell
# Инициализация репозитория
git init

# Добавление всех файлов
git add .

# Первый коммит
git commit -m "Initial commit: Ethereum Kit Android v1.5"

# Подключение к СУЩЕСТВУЮЩЕМУ репозиторию
git remote add origin https://github.com/0xLabWallet/etherium-kit-0xwallet.git

# Получение существующей истории
git fetch origin

# Создание ветки release/v1.5 от main
git checkout -b release/v1.5 origin/main

# Отправка в GitHub
git push -u origin release/v1.5
```

## 🏷️ Шаг 3: Создание релиза v1.5

### 3.1 Создание тега

```powershell
# Создание тега для версии 1.5
git tag -a 1.5 -m "Release v1.5 - First public release via JitPack"

# Отправка тега на GitHub
git push origin 1.5
```

### 3.2 Создание релиза через GitHub UI

1. Перейдите на https://github.com/0xLabWallet/etherium-kit-0xwallet
2. Нажмите "Releases" → "Create a new release"
3. **Tag**: выберите `1.5`
4. **Title**: `v1.5 - First Public Release`
5. **Description**: 

```markdown
# 🎉 First Public Release

Ethereum Kit Android is now available via JitPack!

## 📦 Installation

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.0xLabWallet.ethereum-kit-android:ethereumkit:1.5'
}
```

## ✨ What's Included

- **ethereumkit** - Core Ethereum functionality
- **erc20kit** - ERC20 token support
- **nftkit** - NFT (ERC721/ERC1155) support
- **uniswapkit** - Uniswap integration
- **oneinchkit** - 1inch DEX aggregator

## 📚 Documentation

See [README.md](https://github.com/0xLabWallet/ethereum-kit-android/blob/main/README.md) for complete documentation.

## 🔗 Links

- [JitPack](https://jitpack.io/#0xLabWallet/ethereum-kit-android/1.5)
- [Documentation](https://github.com/0xLabWallet/ethereum-kit-android)
```

6. Нажмите "Publish release"

## 📊 Шаг 4: Проверка сборки на JitPack

### 4.1 Триггер сборки

1. Перейдите на https://jitpack.io
2. Введите: `0xLabWallet/etherium-kit-0xwallet`
3. Нажмите "Look up"
4. Найдите версию `1.5` и нажмите "Get it"

JitPack автоматически начнёт сборку. Это может занять 5-10 минут.

### 4.2 Проверка статуса

Сборка будет показывать один из статусов:
- 🟢 **Green badge** - Успешно собрано ✅
- 🔴 **Red badge** - Ошибка сборки ❌
- ⚪ **No badge** - Ещё не собрано

### 4.3 Просмотр логов сборки

Если сборка не удалась:
1. Нажмите на версию `1.5`
2. Нажмите "Log" для просмотра ошибок
3. Исправьте ошибки и создайте новый тег (например, `1.5.1`)

## ✅ Шаг 5: Тестирование интеграции

### 5.1 Создайте тестовый проект

```gradle
// build.gradle (project level)
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}

// build.gradle (app level)
dependencies {
    implementation 'com.github.0xLabWallet.ethereum-kit-android:ethereumkit:1.5'
}
```

### 5.2 Проверьте синхронизацию

```powershell
./gradlew clean build
```

Если всё работает - библиотека успешно опубликована! 🎉

## 🔄 Обновление проекта Maneki

После успешной публикации обновите `Maneki-android`:

### 1. Удалите локальные модули из settings.gradle

```gradle
// ❌ Удалить эти строки
include ':ethereumkit'
project(':ethereumkit').projectDir = new File('libs/ethereum-kits/ethereumkit')
// ... и остальные модули
```

### 2. Обновите dependencies в app/build.gradle

```gradle
// ❌ Заменить
implementation project(':ethereumkit')
implementation project(':erc20kit')
implementation project(':uniswapkit')
implementation project(':oneinchkit')
implementation project(':nftkit')

// ✅ На
implementation 'com.github.0xLabWallet.ethereum-kit-android:ethereumkit:1.5'
implementation 'com.github.0xLabWallet.ethereum-kit-android:erc20kit:1.5'
implementation 'com.github.0xLabWallet.ethereum-kit-android:uniswapkit:1.5'
implementation 'com.github.0xLabWallet.ethereum-kit-android:oneinchkit:1.5'
implementation 'com.github.0xLabWallet.ethereum-kit-android:nftkit:1.5'
```

### 3. Добавьте JitPack в repositories

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### 4. Синхронизируйте проект

```powershell
./gradlew clean build
```

### 5. Тестирование

Запустите приложение и убедитесь, что всё работает корректно:
- Создание кошелька
- Отправка транзакций
- Работа с ERC20 токенами
- Свопы через Uniswap/1inch

## 🐛 Решение проблем

### Ошибка: "Failed to resolve"

```
Could not find com.github.0xLabWallet.ethereum-kit-android:ethereumkit:1.5
```

**Решение:**
1. Проверьте, что JitPack успешно собрал библиотеку (зелёный badge) на https://jitpack.io/#0xLabWallet/etherium-kit-0xwallet
2. Убедитесь, что `maven { url 'https://jitpack.io' }` добавлен в repositories
3. Очистите Gradle кэш: `./gradlew clean --refresh-dependencies`

### Ошибка сборки на JitPack

**Решение:**
1. Проверьте логи на JitPack
2. Убедитесь, что все зависимости доступны
3. Проверьте, что `jitpack.yml` настроен правильно
4. Попробуйте локальную сборку: `./gradlew clean build publishToMavenLocal`

### Конфликт версий

**Решение:**
1. Проверьте, что все модули используют одну версию
2. Используйте BOM (Bill of Materials) для управления версиями:

```gradle
dependencies {
    implementation platform('com.github.0xLabWallet.ethereum-kit-android:bom:1.5')
    implementation 'com.github.0xLabWallet.ethereum-kit-android:ethereumkit'
    implementation 'com.github.0xLabWallet.ethereum-kit-android:erc20kit'
}
```

## 📝 Чеклист перед публикацией

- [ ] Все изменения закоммичены
- [ ] Версия обновлена в `build.gradle`
- [ ] CHANGELOG.md обновлён
- [ ] README.md актуален
- [ ] Локальная сборка проходит успешно
- [ ] Build директории удалены (не добавлены в git)
- [ ] Создан и отправлен тег
- [ ] Создан релиз на GitHub
- [ ] JitPack успешно собрал библиотеку
- [ ] Протестирована интеграция в тестовом проекте

## 🎯 Следующие шаги

После успешной публикации:

1. **Объявите о релизе** в социальных сетях
2. **Обновите документацию** проектов, использующих библиотеку
3. **Мониторьте issues** на GitHub
4. **Планируйте следующий релиз** (см. CHANGELOG.md)

## 🔗 Полезные ссылки

- [JitPack Documentation](https://jitpack.io/docs/)
- [GitHub Releases Guide](https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository)
- [Semantic Versioning](https://semver.org/)
- [Keep a Changelog](https://keepachangelog.com/)

---

**Вопросы?** Откройте issue на GitHub!

