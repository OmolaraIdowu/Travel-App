package com.swancodes.travelapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swancodes.travelapp.data.model.Trip
import com.swancodes.travelapp.data.repository.TravelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okio.IOException

enum class TravelApiStatus { Loading, Error, Done }

class TravelViewModel(
    private val repository: TravelRepository = TravelRepository()
) : ViewModel() {

    private val _selectedCity = MutableStateFlow("")
    val selectedCity: StateFlow<String> = _selectedCity

    private val _startDate = MutableStateFlow("")
    val startDate: StateFlow<String> = _startDate

    private val _endDate = MutableStateFlow("")
    val endDate: StateFlow<String> = _endDate

    private val _tripName = MutableStateFlow("")
    val tripName: StateFlow<String> = _tripName

    private val _travelStyle = MutableStateFlow("")
    val travelStyle: StateFlow<String> = _travelStyle

    private val _tripDescription = MutableStateFlow("")
    val tripDescription: StateFlow<String> = _tripDescription


    private val _trip = MutableStateFlow<Trip?>(null)
    val trip: StateFlow<Trip?> = _trip

    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips

    private val _status = MutableStateFlow<TravelApiStatus>(TravelApiStatus.Done)
    val status: StateFlow<TravelApiStatus> = _status

    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage: StateFlow<String> = _errorMessage

    fun setCity(city: String) {
        _selectedCity.value = city
    }

    fun setDates(start: String, end: String) {
        _startDate.value = start
        _endDate.value = end
    }

    fun setTripName(name: String) { _tripName.value = name }
    fun setTravelStyle(style: String) { _travelStyle.value = style }
    fun setTripDescription(desc: String) { _tripDescription.value = desc }

    init { loadAllTrips() }

    fun loadAllTrips() {
        viewModelScope.launch {
            _status.value = TravelApiStatus.Loading
            try {
                _trips.value = repository.getAllTrips()
                _status.value = TravelApiStatus.Done
            } catch (e: IOException) {
                _status.value = TravelApiStatus.Error
                _errorMessage.value = "Network error: ${e.message}"
            } catch (e: Exception) {
                _status.value = TravelApiStatus.Error
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    fun createTrip() {
        val city = _selectedCity.value
        val start = _startDate.value
        val end = _endDate.value
        val name = _tripName.value
        val style = _travelStyle.value
        val desc = _tripDescription.value

        if (city.isEmpty() || start.isEmpty() || end.isEmpty()) return

        viewModelScope.launch {
            _status.value = TravelApiStatus.Loading
            try {
                val newTrip = repository.createTrip(
                    Trip(
                        id = "",
                        title = name.ifEmpty { city },
                        location = city,
                        startDate = start,
                        endDate = end,
                        category = style.ifEmpty { "Planned Trips" },
                        imageUrl = "",
                        description = desc
                    )
                )

                _trips.value = _trips.value + newTrip
                _status.value = TravelApiStatus.Done

                // Reset inputs
                _tripName.value = ""
                _travelStyle.value = ""
                _tripDescription.value = ""

            } catch (e: Exception) {
                _status.value = TravelApiStatus.Error
                _errorMessage.value = e.message ?: "Unknown error"
            }
        }
    }

    fun getTripById(id: String) {
        viewModelScope.launch {
            _status.value = TravelApiStatus.Loading
            try {
                _trip.value = repository.getTrip(id)
                _status.value = TravelApiStatus.Done
            } catch (e: IOException) {
                _status.value = TravelApiStatus.Error
                _errorMessage.value = "Network error: ${e.message}"
            } catch (e: Exception) {
                _status.value = TravelApiStatus.Error
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}