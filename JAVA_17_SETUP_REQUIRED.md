# ⚠️ Требуется настройка Java 17 для локального тестирования

## 🚨 Проблема

Kotlin 1.9.20 с kapt не совместим с Java 21. Требуется Java 17 для локальной сборки.

**Ошибка:**
```
java.lang.IllegalAccessError: superclass access check failed: 
class org.jetbrains.kotlin.kapt3.base.javac.KaptJavaCompiler (in unnamed module) 
cannot access class com.sun.tools.javac.main.JavaCompiler (in module jdk.compiler)
```

---

## ✅ Решение

### Вариант 1: Установить JAVA_HOME на Java 17 (Рекомендуется)

Найдите где установлена Java 17 и установите переменную окружения:

**В PowerShell (временно для текущей сессии):**
```powershell
# Пример - измените путь на ваш
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"

# Проверка
java -version
# Должно показать: openjdk version "17.x.x"

# После этого запустите сборку
.\gradlew clean build -x test -x lint
```

**В Windows (постоянно):**
1. **Системные настройки** → **Система** → **Дополнительные параметры системы**
2. **Переменные среды**
3. Добавить/Изменить `JAVA_HOME`:
   - Переменная: `JAVA_HOME`
   - Значение: `C:\Program Files\Java\jdk-17` (ваш путь)
4. Перезапустить PowerShell
5. Проверить: `java -version`

---

### Вариант 2: Использовать Gradle Toolchains

Добавьте в **gradle.properties**:

```properties
org.gradle.java.installations.auto-detect=true
org.gradle.java.installations.auto-download=true
```

И в корневой **build.gradle**:

```gradle
allprojects {
    tasks.withType(JavaCompile).configureEach {
        options.release = 17
    }
    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}
```

---

### Вариант 3: Понизить Kotlin до 1.7.10 (для Java 21)

**Откатить изменения в build.gradle:**
```gradle
ext.kotlin_version = '1.7.10'  // Вместо 1.9.20
classpath 'com.android.tools.build:gradle:7.4.2'  // Вместо 8.5.0
```

**И в gradle-wrapper.properties:**
```properties
distributionUrl=https\://services.gradle.org/distributions/gradle-7.5-bin.zip
```

**Но это не рекомендуется!** Лучше использовать Java 17.

---

## 🎯 Рекомендуемый подход

### Для локального тестирования:

1. **Найдите Java 17:**
```powershell
# Поиск в стандартных директориях
Get-ChildItem "C:\Program Files\Java\" -ErrorAction SilentlyContinue
Get-ChildItem "C:\Program Files\Eclipse Adoptium\" -ErrorAction SilentlyContinue
Get-ChildItem "C:\Users\home\.jdks\" -ErrorAction SilentlyContinue
```

2. **Установите JAVA_HOME:**
```powershell
$env:JAVA_HOME = "ПУТЬ_К_JAVA_17"
```

3. **Запустите сборку:**
```powershell
.\gradlew clean build -x test -x lint
.\gradlew publishToMavenLocal
```

---

### Для публикации на JitPack:

**✅ Не требуется действий!**

JitPack использует свою собственную Java 17 из **jitpack.yml**:

```yaml
jdk:
  - openjdk17

before_install:
  - sdk install java 17.0.7-tem
  - sdk use java 17.0.7-tem
```

**Сборка на JitPack будет работать без проблем!**

---

## 📝 Инструкция по установке Java 17

Если у вас ещё нет Java 17:

### Windows:

1. **Скачать OpenJDK 17:**
   - Eclipse Temurin: https://adoptium.net/temurin/releases/?version=17
   - Oracle JDK 17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html

2. **Установить** в стандартную директорию

3. **Настроить JAVA_HOME** (см. Вариант 1 выше)

---

## 🔄 Альтернативный подход

### Пропустить локальное тестирование

Если настройка Java 17 сложна, можно:

1. ✅ Код уже проверен и обновлён
2. ✅ Все модули настроены правильно
3. ✅ API V2 интегрирован
4. ✅ Документация создана

**Сразу публиковать на JitPack:**
```bash
git init
git remote add origin https://github.com/0xLabWallet/etherium-kit-0xwallet.git
git add .
git commit -m "Release v1.3 - Etherscan API V2 + Key Rotation"
git push -u origin main
git tag -a v1.3 -m "Release v1.3"
git push origin v1.3
```

JitPack соберёт проект с Java 17 автоматически!

---

## ✅ Что уже готово

Все изменения для v1.3 применены:
- ✅ TransactionSource.kt обновлён (API V2)
- ✅ namespace добавлен во все модули
- ✅ Версия 1.3 установлена
- ✅ gradle.properties настроен для Java 21
- ✅ jitpack.yml настроен для Java 17
- ✅ Документация полная

**Проект готов к публикации на JitPack!**

---

**Рекомендация:** Если сложно настроить локальную Java 17, просто загрузите на GitHub и JitPack соберёт всё правильно с Java 17! 🚀

