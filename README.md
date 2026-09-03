# 🔧 MechanicHub

MechanicHub is a modern Android application built with **Jetpack Compose** that allows users to discover local auto mechanics, filter services, and submit service requests.

## 📱 Features

* Mechanic Discovery
* Service Filtering
* Service Requests
* Form Validation
* Offline Fallback
* Type-Safe Navigation
* Dark Mode

## 🏗️ Architecture

**MVVM (Model-View-ViewModel)**

## 🛠️ Tech Stack

* Kotlin
* Jetpack Compose
* Material 3
* MVVM
* Dagger Hilt
* Retrofit 2
* OkHttp
* Kotlin Coroutines
* Kotlin Flow
* Navigation Compose

## 🔗 Backend

MechanicHub uses a custom-built **Python FastAPI backend** for its network operations.

> ⚠️ **Backend setup is optional for testing the UI.** The Android application automatically falls back to local `SampleData` when the API cannot be reached.

For the complete network experience, set up the backend first:

**Backend Repository:** `https://github.com/lakshaykarad/MechanicHub_Backend`

## 🎥 Demo

<video src="https://github.com/lakshaykarad/MechanicHub/blob/main/GarageGo/app/src/main/java/com/example/systemmonitor/DEMO.mp4" width="320" height="640" controls></video>

