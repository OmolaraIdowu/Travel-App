# Travel App

A simple Android app built with **Jetpack Compose** to plan trips, select cities and dates, and view trips from an API. Demonstrates **UI design, API integration, and state management** in Kotlin.

---

## Features

- Plan trips by selecting **city**, **start date**, and **end date**.
- Fetch and display trips from a REST API.
- Filter trips by categories: Planned, Invited, All Trips.
- Calculate trip duration automatically.
- Display trip images.
- Trip details displayed in a **bottom sheet** (currently in progress).

---

## Screens

1. **Plan Trip Screen** – Select city and date range; view trips below.
2. **Date Picker Screen** – Pick a date range; selected dates update Plan Trip screen.
3. **Trip Card** – Shows title, location, image, and duration.
4. **Trip Details Screen** – **Under development**; shows placeholders for now.

---

## Tech Stack

- Kotlin, Jetpack Compose
- Retrofit + OkHttp for API
- Gson for JSON parsing
- Coil for image loading
- ViewModel + StateFlow for state management

---

## API Integration

- **Base URL**: `https://beeceptor.com/crud-api/`
- **Endpoints**:
    - `POST /api/trips` → Create a new trip
    - `GET /api/trips/{id}` → Get a trip by ID

---

## APK




## How to Run

1. **Clone the repository**

```bash
git clone [https://github.com/<OmolaraIdowu>/travel-app.git](https://github.com/OmolaraIdowu/Travel-App.git)]
```

3. **Check your dependencies in `app/build.gradle`:**  

```gradle
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")
implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
implementation("io.coil-kt:coil-compose:2.4.0")
```
4. **Sync Gradle**
    - Click *Sync Now* if prompted to ensure all dependencies are downloaded.

5. **Run the app**
    - Use an Android emulator or connect a physical device.
    - Click the **Run** button (green play icon) in Android Studio.

6. **Test the functionality**
    - On the Plan Trip screen, select a city and date range.
    - Trips will fetch from the API.
    - Tap on a trip to view its details (bottom sheet; Trip Details screen is under development).

7. **Optional:**
    - Add your own API endpoints if you exceed Beeceptor request limits.
    - Modify trip images by updating the `imageUrl` in the `Trip` data class.
   
## To-Do / Roadmap

- ⚠️ Trip Details screen – placeholders displayed
- ⚠️ Complete booking functionality
- ⚠️ Improve error handling and user feedback
