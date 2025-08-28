<img src="https://img.shields.io/badge/made%20with-kotlin-blue.svg?style=plastic"> <img src="https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=plastic">
<br>

# Travel App

A simple Android app built with **Jetpack Compose** to plan trips, select cities and dates, and view trips from an API. Demonstrates **UI design, API integration, and state management** in Kotlin.

## SDK Requirements
- Minimum SDK Requirement - android API 26
- Target SDK - android API 36 

## Installation
 - To run this code, clone this repository using this command

`git clone https://github.com/OmolaraIdowu/Travel-App.git`

## Features

- Plan trips by selecting **city**, **start date**, and **end date**.
- Fetch and display trips from a REST API.
- Filter trips by categories: Planned, Invited, All Trips.
- Calculate trip duration automatically.
- Display trip images.
- Trip details displayed in a **bottom sheet** (currently in progress).

## APK file

You can download the APK file for this project [here](https://drive.google.com/file/d/1ps3i5alHHwElhF2ZgggE3JeajVVjyZHf/view?usp=drivesdk)

## Architecture 
The app follows the **Clean Architecture + MVVM** pattern, separating UI, business logic, and data:

- **Domain:** Pure business logic and entities (`Trip`). Includes UseCases for creating and fetching trips.
- **Data:** Handles API communication (`TravelApiService`) and repository implementation (`TravelRepository`). Maps DTOs to domain models.
- **Presentation:** UI built with Jetpack Compose. ViewModels expose state to the UI using `StateFlow`.

## Screens

1. **Plan Trip Screen** – Select city and date range; view trips below.
2. **Date Picker Screen** – Pick a date range; selected dates update Plan Trip screen.
3. **Trip Card** – Shows title, location, image, and duration.
4. **Trip Details Screen** – **Under development**; shows placeholders for now.

---

## API Integration

- **Base URL**: `https://beeceptor.com/crud-api/`
- **Endpoints**:
    - `POST /api/trips` → Create a new trip
    - `GET /api/trips/{id}` → Get a trip by ID

---

## Libraries
* [Jetpack Compose](https://developer.android.com/jetpack/compose) – UI toolkit
* [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/) – Networking
* [Gson](https://github.com/google/gson) – JSON parsing
* [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) – Async programming
* [Coil](https://coil-kt.github.io/coil/) – Image loading (optional)

## Screenshots
PLAN TRIP | CREATE TRIP | SELECT CITY |
| :---------------: | :---------------: | :---------------: |
| <img src="https://github.com/OmolaraIdowu/Travel-App/blob/main/screenshots/img.png" align="center" width="100%" alt="Screen 1"> | <img src="https://github.com/OmolaraIdowu/Travel-App/blob/main/screenshots/img_1.png" align="center" width="100%" alt="Screen 2"> | <img src="https://github.com/OmolaraIdowu/Travel-App/blob/main/screenshots/img_2.png" align="center" width="100%" alt="Screen 3">

## To-Do / Roadmap

- ⚠️ Trip Details screen – placeholders displayed
- ⚠️ Complete booking functionality
- ⚠️ Improve error handling and user feedback


## Author

* **OmolaraIdowu**  
 - [LinkedIn](https://www.linkedin.com/in/omolara-idowu-0273661b4/)
