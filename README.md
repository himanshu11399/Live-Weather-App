# 🌦 Live Weather App – Kotlin + Retrofit + OpenWeather API

A **beautiful and real-time weather application** built in **Kotlin** for Android.  
The app fetches live weather data using the **OpenWeather API** via **Retrofit**, allows users to **search for cities**, and displays different themed screens for weather conditions like **Sunny**, **Snow**, **Rain**, and **Light Clouds**.

---

## 📸 Features
- 🔍 **City Search** – Search and view weather data for any city.
- 📍 **Default City** – Save your preferred city as default.
- ☀ **Dynamic Weather Screens** – UI changes based on weather conditions:
  - Sunny ☀
  - Rain 🌧
  - Snow ❄
  - Light Clouds ☁
- ⏳ **Live Time** – Real-time clock updates.
- ⚡ **Fast & Efficient** – Uses Retrofit for quick API calls.
- 🎨 **Modern UI** – Clean, minimal, and weather-themed interface.

---

##Dashboard Images
<p align="center">
![WhatsApp Image 2025-08-14 at 00 05 01_7e8eda39](https://github.com/user-attachments/assets/18d3aee2-3b2c-4b76-b26b-699a63704219)

![WhatsApp Image 2025-08-14 at 00 05 00_883bd6e3](https://github.com/user-attachments/assets/af25f0ab-1521-431d-a410-a03f6b587f87)
</p>


![WhatsApp Image 2025-08-14 at 00 04 59_074a0ac0](https://github.com/user-attachments/assets/b3e18e6f-8593-4f54-9caf-bace9f6e8e41)
![WhatsApp Image 2025-08-14 at 00 05 00_6e7692ea](https://github.com/user-attachments/assets/e8cc8c89-54b0-4e96-b66b-1df6440aa74c)




## 🛠 Tech Stack
- **Language:** Kotlin
- **Architecture:** MVVM (recommended for scalability)
- **Networking:** Retrofit + Gson
- **API:** OpenWeather API
- **UI:** XML + ViewBinding
- **Data Storage:** SharedPreferences for default city
- **Async Operations:** Coroutines

---

## 📂 Project Structure

LiveWeatherApp/
- │── app/
- │ ├── src/
- │ │ ├── main/
- │ │ │ ├── java/com/example/weatherapp/
- │ │ │ │ ├── ui/ # Activities, Fragments
- │ │ │ │ ├── network/ # Retrofit API services
- │ │ │ │ ├── model/ # Data classes
- │ │ │ │ ├── utils/ # Helper functions
 -   │ │ ├── res/ # Layouts, Drawables, Values
- │ ├── build.gradle
- │── README.md
