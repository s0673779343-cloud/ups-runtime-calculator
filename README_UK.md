# Калькулятор ДБЖ для Android — версія 0.2

Це Android Studio проєкт, який пакує локальну HTML-версію калькулятора у застосунок Android.
Після встановлення калькулятор працює офлайн; для розрахунків інтернет не потрібен.

## Зібрати APK в Android Studio

1. Розпакуйте архів.
2. В Android Studio виберіть **Open** і відкрийте папку `ups_calculator_android`.
3. Дочекайтеся Gradle Sync. Потрібні Android SDK Platform 35 та JDK 17.
4. В меню виберіть **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
5. APK для тестування буде в `app/build/outputs/apk/debug/app-debug.apk`.

## Встановлення на телефон

Передайте `app-debug.apk` на Android-телефон і відкрийте його. Якщо Android запитає дозвіл, дозвольте встановлення застосунків із цього джерела.

## Параметри

- Назва: Калькулятор ДБЖ
- Application ID: `ua.calculator.ups`
- Версія: 0.2 (versionCode 2)
- Мінімальна версія Android: 6.0 (API 23)
- HTML-файл включено в APK; інтернет-дозвіл не потрібен.

## Примітка

Це вихідний Android-проєкт, а не вже зібраний APK. У середовищі, де підготовлено цей архів, немає Android SDK/Gradle, тому APK потрібно зібрати в Android Studio або іншому Android build environment.
