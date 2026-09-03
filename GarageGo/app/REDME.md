# 🔧 MechanicHub

**MechanicHub** is a modern Android application built with **Jetpack Compose** that helps users discover local auto mechanics, filter shops by available services, and submit service requests.

The project follows a clean **MVVM architecture** and uses a custom-built **FastAPI backend** for real network communication. It also includes repository-level offline fallback, allowing the application to remain functional even when the backend is unavailable.

---

## 📱 Features

### 🔍 Mechanic Discovery

Browse available mechanic shops fetched dynamically from the backend API.

### 🏷️ Smart Filtering

Filter mechanics based on the services they provide, such as:

* Oil Change
* Brake Repair
* AC Repair
* Engine Repair
* And more

### 📝 Service Requests

Submit a service request directly from the application.

The request flow includes validation for important vehicle and service information, including:

* Vehicle VIN
* Issue description
* Required service information

Validated requests are sent to the backend API.

### 📴 Offline Fallback

MechanicHub is designed to remain usable when the API is unavailable.

If a network request fails, the **Repository layer automatically falls back to local `SampleData`** instead of allowing the application to crash.

This provides two modes of operation:

```text
Backend Available
       ↓
   API Request
       ↓
   Remote Data
       ↓
   Android UI


Backend Unavailable
       ↓
   API Failure
       ↓
 Repository Fallback
       ↓
   SampleData
       ↓
   Android UI
```

### 🧭 Type-Safe Navigation

Navigation is implemented using **type-safe navigation patterns** with sealed classes, reducing the possibility of invalid routes and runtime navigation errors.

### 🌙 Dark Mode

The application uses **Material Design 3 theming** and supports dark mode based on the device's system preference.

### 🛡️ Network Error Resilience

Network failures are handled at the Repository layer so that UI components do not need to directly manage networking failures.

---

## 🏗️ Architecture

MechanicHub follows the **MVVM (Model-View-ViewModel)** architecture.

```text
┌─────────────────────────┐
│     Jetpack Compose     │
│          UI             │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│       ViewModel         │
│   State + UI Logic      │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│       Repository        │
│                         │
│ API Success → API Data  │
│ API Failure → SampleData│
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│ Retrofit + OkHttp       │
│      Network Layer      │
└────────────┬────────────┘
             │
             ▼
┌─────────────────────────┐
│     FastAPI Backend     │
│       REST API          │
└─────────────────────────┘
```

This separation keeps the UI independent from the networking implementation and makes the application easier to maintain and test.

---

## 🛠️ Tech Stack

| Category                 | Technology         |
| ------------------------ | ------------------ |
| UI                       | Jetpack Compose    |
| Design System            | Material 3         |
| Language                 | Kotlin             |
| Architecture             | MVVM               |
| Dependency Injection     | Dagger Hilt        |
| Networking               | Retrofit 2         |
| HTTP Client              | OkHttp             |
| Asynchronous Programming | Kotlin Coroutines  |
| Reactive State           | Kotlin Flow        |
| Navigation               | Navigation Compose |
| Backend                  | Python FastAPI     |

---

## 🔗 Backend

MechanicHub uses a custom-built **Python FastAPI backend** for its network operations.

> ⚠️ **Backend setup is optional for testing the UI.**
> The Android application automatically falls back to local `SampleData` when the API cannot be reached.

For the complete network experience, set up the backend first:

**Backend Repository:**
`[Replace with your actual FastAPI repository URL]`

---

# 🚀 Getting Started

## 1. Clone the Repository

```bash
git clone [YOUR_ANDROID_REPOSITORY_URL]
```

Open the project in **Android Studio**.

---

## 2. Run Without the Backend

If you only want to explore the Android UI, no backend setup is required.

Simply:

1. Open the project in Android Studio.
2. Allow Gradle to sync.
3. Select an emulator or connected Android device.
4. Click **Run ▶**.

If the API is unavailable, MechanicHub automatically uses its local `SampleData`.

---

# 🌐 Connecting to the Backend

For live API requests, start the FastAPI backend before running the Android application.

The correct API base URL depends on whether you are using an emulator or a physical Android device.

---

## Android Emulator

If your FastAPI server is running on your computer at:

```text
http://localhost:8001/
```

you **cannot** use `localhost` directly from the Android emulator.

Use:

```text
http://10.0.2.2:8001/
```

### Example

```kotlin
BASE_URL = "http://10.0.2.2:8001/"
```

`10.0.2.2` is the Android Emulator's special address for accessing the host computer's localhost.

---

## Physical Android Device

If you are running the application on a physical Android phone:

1. Connect your phone and computer to the **same Wi-Fi network**.
2. Find your computer's local IPv4 address.
3. Start the FastAPI server so it accepts connections from the network.
4. Use your computer's IPv4 address in the Android application.

For example:

```text
http://192.168.1.100:8001/
```

### Example

```kotlin
BASE_URL = "http://192.168.1.100:8001/"
```

Replace `192.168.1.100` with the actual IPv4 address of your development machine.

---

## ⚙️ Configure the API URL

Locate your Retrofit configuration, such as:

```text
MechanicApi.kt
```

or your networking/DI module.

Update the base URL according to your environment:

```kotlin
const val BASE_URL = "http://10.0.2.2:8001/"
```

For a physical device:

```kotlin
const val BASE_URL = "http://YOUR_COMPUTER_IPV4:8001/"
```

> **Do not use `localhost` or `127.0.0.1` for a physical Android device.** Those addresses refer to the phone itself, not your development computer.

---

# 🔄 Application Data Flow

A typical mechanic discovery request follows this flow:

```text
User opens Discovery screen
          ↓
       ViewModel
          ↓
      Repository
          ↓
   Retrofit API call
          ↓
      FastAPI API
          ↓
     Response Data
          ↓
      Repository
          ↓
       ViewModel
          ↓
    Compose UI State
          ↓
      UI updates
```

If the request fails:

```text
Retrofit API call
       ↓
   Network Error
       ↓
   Repository
       ↓
   SampleData
       ↓
   ViewModel
       ↓
   Compose UI
```

This keeps network failure handling outside the UI layer.

---

# 📂 Project Structure

A typical project structure is organized around the application's architecture:

```text
app/
└── src/
    └── main/
        └── java/
            └── ...
                ├── data/
                │   ├── api/
                │   ├── model/
                │   └── repository/
                │
                ├── di/
                │
                ├── navigation/
                │
                ├── ui/
                │   ├── screens/
                │   ├── components/
                │   └── theme/
                │
                └── viewmodel/
```

The exact package structure may vary depending on the current implementation.

---

# 🧪 Testing the Application

### Without Backend

Use this mode when you only want to evaluate the Android UI and application flow.

```text
Android App
    ↓
Repository
    ↓
SampleData
```

### With Backend

Use this mode to test actual API communication.

```text
Android App
    ↓
Retrofit
    ↓
FastAPI
    ↓
Database / Backend Logic
```

---

# ⚠️ Troubleshooting

### API connection fails on Emulator

Make sure you are using:

```text
http://10.0.2.2:8001/
```

instead of:

```text
http://localhost:8001/
```

---

### API connection fails on Physical Device

Check that:

* Phone and computer are on the same Wi-Fi network.
* The FastAPI server is running.
* The configured IPv4 address is correct.
* Port `8001` is accessible through the computer's firewall.
* FastAPI is listening on an address accessible from the local network.

For example, the server may need to be started with a host configuration that allows network access rather than binding only to `localhost`.

---

### Backend is not running

No problem for UI testing.

MechanicHub will use its repository-level offline fallback and load local `SampleData`.

---

# 🎯 Project Goals

MechanicHub was built to demonstrate practical Android development beyond simply creating UI screens.

The project focuses on:

* Modern Android UI development with Jetpack Compose
* MVVM architecture
* Dependency injection with Hilt
* REST API integration
* Retrofit and OkHttp networking
* Kotlin Coroutines and Flow
* Type-safe navigation
* Form validation
* Repository-level error handling
* Offline resilience
* Material 3 theming
* Android ↔ FastAPI communication

---

# 👨‍💻 Author

**[Your Name]**

Android Developer focused on building modern, scalable applications with **Kotlin, Jetpack Compose, and backend API integration**.

---

## 📄 License

This project is available under the terms of the license included in this repository.
