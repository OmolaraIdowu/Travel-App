package com.swancodes.travelapp.data.remote

import com.swancodes.travelapp.data.model.Trip
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TravelApiService {
    @POST("trips")
    suspend fun createTrip(@Body trip: Trip): Trip

    @GET("trips/{id}")
    suspend fun getTrip(@Path("id") id: String): Trip

    @GET("api/trips")
    suspend fun getAllTrips(): List<Trip>
}