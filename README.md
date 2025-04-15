<h1 align="center">GuaPay</h1>

<p align="center">  
GuaPay demonstrates modern Android development with Koin, Coroutines, Flow, Jetpack (Room, ViewModel, Compose) on MVVM architecture.
</p>

<p align="center">
<img src="/img/img_screenshot.png"/>
</p>

## 📦 Download

Go to the [Releases](https://github.com/NickGuaGua/GuaPay/releases) to download the latest APK.

## 🧪 Demo

Clicking the Account button generates random cards with mock data, making it easier to demo.

<img src="/img/demo.gif" align="right" width="320"/>

## 🛠️ Tech stack

- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/)
  based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/)
  for asynchronous.
- Jetpack
    - Compose: Android’s modern, declarative UI toolkit for building native UIs faster and more
      intuitively.
    - ViewModel: Lifecycle-aware component for managing UI-related data in a clean,
      separation-of-concerns architecture.
    - Room: SQLite object mapping library that provides an easy and robust abstraction layer for
      local database access in Android apps.
- Architecture
    - MVVM Architecture
    - Repository Pattern
- DI
    - [Koin](https://insert-koin.io/)

<br><br><br><br><br><br><br><br><br><br><br><br>
## 🌙 Dark Mode & Tablet Support
Supports dark theme based on system settings. The UI adapts to various screen sizes using responsive layouts and window size classes, ensuring a smooth experience on tablets and large-screen devices.

<p align="center">
  <img src="/img/demo_dark_mode.gif" width="33%" style="margin-right:10px;"/>
  <img src="/img/demo_tablet.gif" width="66%"/>
</p>

## 🏗️ Architecture
GuaPay is based on the MVVM architecture and the Repository pattern, and follows the [Google's official architecture guidance](https://developer.android.com/topic/architecture).
<p align="center">
<img src="/img/img_architecture.png"/>
</p>
