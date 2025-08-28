package com.swancodes.travelapp.data.repository

import com.swancodes.travelapp.data.model.Trip
import com.swancodes.travelapp.data.remote.TravelApiClient

class TravelRepository {
    private val api = TravelApiClient.apiService

    suspend fun createTrip(trip: Trip): Trip {
        return api.createTrip(trip)
    }

    suspend fun getTrip(id: String): Trip {
        return api.getTrip(id)
    }

    suspend fun getAllTrips(): List<Trip> {
        return api.getAllTrips()
    }
}