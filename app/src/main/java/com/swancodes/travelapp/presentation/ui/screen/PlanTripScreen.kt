package com.swancodes.travelapp.presentation.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.swancodes.travelapp.R
import com.swancodes.travelapp.presentation.ui.component.EmptyContent
import com.swancodes.travelapp.presentation.ui.component.ErrorMessage
import com.swancodes.travelapp.presentation.ui.component.LoadingIndicator
import com.swancodes.travelapp.presentation.ui.navigation.AppScreen
import com.swancodes.travelapp.presentation.ui.widget.TripCard
import com.swancodes.travelapp.presentation.ui.widget.TripPlannerSection
import com.swancodes.travelapp.presentation.ui.widget.TripsSection
import com.swancodes.travelapp.presentation.viewmodel.TravelApiStatus
import com.swancodes.travelapp.presentation.viewmodel.TravelViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanTripScreen(
    navController: NavController,
    viewModel: TravelViewModel = viewModel()
) {
    val trips by viewModel.trips.collectAsState()
    val status by viewModel.status.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    val context = LocalContext.current


    val city by viewModel.selectedCity.collectAsState()
    val startDate by viewModel.startDate.collectAsState()
    val endDate by viewModel.endDate.collectAsState()

    var selectedTripCategory by remember { mutableStateOf<String?>(null) }
    var showSheet by remember { mutableStateOf(false) }

    // Filter trips based on selected category
    val displayedTrips = remember(trips, selectedTripCategory) {
        when {
            selectedTripCategory.isNullOrEmpty() || selectedTripCategory == "All Trips" -> trips
            else -> trips.filter { it.category == selectedTripCategory }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    stringResource(R.string.plan_a_trip),
                    style = MaterialTheme.typography.headlineSmall
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.White,
                titleContentColor = Color.Black
            ))
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 100.dp)
            ) {
                item {
                    TripPlannerSection(
                        selectedCity = city,
                        startDate = startDate,
                        endDate = endDate,
                        onCityClick = { navController.navigate(AppScreen.City.route) },
                        onStartDateClick = { navController.navigate(AppScreen.Date.route) },
                        onEndDateClick = { navController.navigate(AppScreen.Date.route) },
                        onCreateTrip = {
                            if (city.isNotEmpty() && startDate.isNotEmpty() && endDate.isNotEmpty()) {
                                showSheet = true
                            }
                        }
                    )
                }

                item {
                    TripsSection(
                        selectedCategory = selectedTripCategory,
                        onCategoryChange = { selectedTripCategory = it }
                    )
                }

                when (status) {
                    TravelApiStatus.Loading -> item { LoadingIndicator() }
                    TravelApiStatus.Error -> item {
                        ErrorMessage(
                            message = errorMessage,
                            onRetry = {
                                viewModel.loadAllTrips()
                            })
                    }

                    TravelApiStatus.Done -> {
                        if (displayedTrips.isEmpty()) item { EmptyContent() }
                        else items(displayedTrips) { trip ->
                            TripCard(trip = trip, onViewClick = {
                                viewModel.getTripById(trip.id)
                                navController.navigate(AppScreen.Detail.route)
                            })
                        }
                    }
                }
            }
        }

        if (showSheet) {
            ModalBottomSheet(
                onDismissRequest = { showSheet = false },
                containerColor = Color.White,
                shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp),
                tonalElevation = 8.dp,
                dragHandle = {},
            ) { Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding()
                    .navigationBarsPadding()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TripFormBottomSheet(
                    viewModel = viewModel,
                    onNextClick = { viewModel.createTrip() },
                    onBackClick = { if (status != TravelApiStatus.Loading) showSheet = false }
                )

                if (status == TravelApiStatus.Loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingIndicator()
                    }
                }
            }}
        }
    }
}

fun formatDateShort(millis: Long): String {
    val formatter = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
    return formatter.format(Date(millis))
}
