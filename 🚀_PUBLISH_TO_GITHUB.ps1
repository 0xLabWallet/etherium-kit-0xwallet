# =====================================================
# Скрипт для публикации Ethereum Kit v1.5
# на существующий репозиторий etherium-kit-0xwallet
# =====================================================

Write-Host "🚀 Публикация Ethereum Kit Android v1.5" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""

# Проверка текущей директории
$currentDir = Get-Location
Write-Host "📁 Текущая директория: $currentDir" -ForegroundColor Cyan

# Переход в папку проекта
$projectPath = "D:\Projects\ethereum-kit-android"
if (-not (Test-Path $projectPath)) {
    Write-Host "❌ Ошибка: папка $projectPath не найдена!" -ForegroundColor Red
    exit 1
}

Set-Location $projectPath
Write-Host "✅ Перешли в папку проекта" -ForegroundColor Green
Write-Host ""

# Шаг 1: Инициализация Git
Write-Host "📝 Шаг 1: Инициализация Git..." -ForegroundColor Yellow
if (Test-Path ".git") {
    Write-Host "⚠️  Git репозиторий уже инициализирован" -ForegroundColor Yellow
} else {
    git init
    Write-Host "✅ Git инициализирован" -ForegroundColor Green
}
Write-Host ""

# Шаг 2: Добавление remote (если не существует)
Write-Host "📝 Шаг 2: Проверка remote..." -ForegroundColor Yellow
$remoteUrl = "https://github.com/0xLabWallet/etherium-kit-0xwallet.git"
$existingRemote = git remote get-url origin 2>$null

if ($existingRemote) {
    Write-Host "⚠️  Remote 'origin' уже существует: $existingRemote" -ForegroundColor Yellow
    $changeRemote = Read-Host "Хотите изменить на $remoteUrl? (y/n)"
    if ($changeRemote -eq 'y') {
        git remote set-url origin $remoteUrl
        Write-Host "✅ Remote обновлён" -ForegroundColor Green
    }
} else {
    git remote add origin $remoteUrl
    Write-Host "✅ Remote добавлен: $remoteUrl" -ForegroundColor Green
}
Write-Host ""

# Шаг 3: Получение истории из GitHub
Write-Host "📝 Шаг 3: Получение истории из GitHub..." -ForegroundColor Yellow
Write-Host "⏳ Это может занять некоторое время..." -ForegroundColor Cyan
git fetch origin
if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ История получена" -ForegroundColor Green
} else {
    Write-Host "❌ Ошибка при получении истории" -ForegroundColor Red
    exit 1
}
Write-Host ""

# Шаг 4: Добавление файлов
Write-Host "📝 Шаг 4: Добавление файлов..." -ForegroundColor Yellow
git add .
Write-Host "✅ Файлы добавлены" -ForegroundColor Green
Write-Host ""

# Шаг 5: Коммит
Write-Host "📝 Шаг 5: Создание коммита..." -ForegroundColor Yellow
$commitMessage = @"
Release v1.5 - Enhanced Documentation and JitPack Ready

✨ New Features:
- Complete README with usage examples
- Comprehensive CHANGELOG
- Detailed PUBLISHING guide
- MIT License

🔧 Improvements:
- Unified version management (1.5 for all modules)
- Enhanced POM metadata
- Better Maven publication setup
- JitPack optimization

📚 Documentation:
- Installation instructions
- Quick start guide
- API examples for all modules
- Network configuration guide
- RxJava reactive streams examples

🏗️ Project Structure:
- Organized gradle build files
- Proper .gitignore
- Clean project structure

📦 Modules:
- ethereumkit (Core)
- erc20kit (ERC20 tokens)
- nftkit (NFT support)
- uniswapkit (Uniswap DEX)
- oneinchkit (1inch aggregator)

🔗 Ready for JitPack publication
"@

git commit -m $commitMessage
if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Коммит создан" -ForegroundColor Green
} else {
    Write-Host "⚠️  Нет изменений для коммита или ошибка" -ForegroundColor Yellow
}
Write-Host ""

# Шаг 6: Создание ветки release/v1.5
Write-Host "📝 Шаг 6: Создание ветки release/v1.5..." -ForegroundColor Yellow
git checkout -b release/v1.5
if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Ветка release/v1.5 создана" -ForegroundColor Green
} else {
    Write-Host "⚠️  Ветка уже существует или ошибка" -ForegroundColor Yellow
}
Write-Host ""

# Шаг 7: Пуш в GitHub
Write-Host "📝 Шаг 7: Отправка в GitHub..." -ForegroundColor Yellow
Write-Host "⏳ Отправка на origin/release/v1.5..." -ForegroundColor Cyan
git push -u origin release/v1.5
if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Код успешно отправлен в GitHub!" -ForegroundColor Green
} else {
    Write-Host "❌ Ошибка при отправке в GitHub" -ForegroundColor Red
    Write-Host "💡 Возможно, нужно авторизоваться в GitHub" -ForegroundColor Cyan
    exit 1
}
Write-Host ""

# Шаг 8: Создание тега
Write-Host "📝 Шаг 8: Создание тега v1.5..." -ForegroundColor Yellow
git tag -a 1.5 -m "Release v1.5 - Enhanced Documentation and JitPack Ready"
if ($LASTEXITCODE -eq 0) {
    Write-Host "✅ Тег 1.5 создан" -ForegroundColor Green
    
    # Отправка тега
    Write-Host "⏳ Отправка тега в GitHub..." -ForegroundColor Cyan
    git push origin 1.5
    if ($LASTEXITCODE -eq 0) {
        Write-Host "✅ Тег успешно отправлен!" -ForegroundColor Green
    } else {
        Write-Host "❌ Ошибка при отправке тега" -ForegroundColor Red
        exit 1
    }
} else {
    Write-Host "⚠️  Тег уже существует или ошибка" -ForegroundColor Yellow
}
Write-Host ""

# Завершение
Write-Host "========================================" -ForegroundColor Green
Write-Host "🎉 ПУБЛИКАЦИЯ ЗАВЕРШЕНА!" -ForegroundColor Green
Write-Host "========================================" -ForegroundColor Green
Write-Host ""
Write-Host "📋 Следующие шаги:" -ForegroundColor Cyan
Write-Host "1. Перейдите на https://github.com/0xLabWallet/etherium-kit-0xwallet" -ForegroundColor White
Write-Host "2. Создайте релиз для тега 1.5" -ForegroundColor White
Write-Host "3. Перейдите на https://jitpack.io" -ForegroundColor White
Write-Host "4. Введите: 0xLabWallet/etherium-kit-0xwallet" -ForegroundColor White
Write-Host "5. Нажмите 'Look up' и соберите версию 1.5" -ForegroundColor White
Write-Host ""
Write-Host "🔗 Полезные ссылки:" -ForegroundColor Cyan
Write-Host "   GitHub: https://github.com/0xLabWallet/etherium-kit-0xwallet" -ForegroundColor White
Write-Host "   JitPack: https://jitpack.io/#0xLabWallet/etherium-kit-0xwallet" -ForegroundColor White
Write-Host ""

# Открыть браузер (опционально)
$openBrowser = Read-Host "Открыть GitHub в браузере? (y/n)"
if ($openBrowser -eq 'y') {
    Start-Process "https://github.com/0xLabWallet/etherium-kit-0xwallet"
}

Write-Host "✅ Готово!" -ForegroundColor Green

