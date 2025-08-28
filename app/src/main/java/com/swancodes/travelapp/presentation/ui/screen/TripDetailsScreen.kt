package com.swancodes.travelapp.presentation.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.swancodes.travelapp.R
import com.swancodes.travelapp.data.model.Trip
import com.swancodes.travelapp.presentation.ui.component.AppSpacer
import com.swancodes.travelapp.presentation.ui.component.NetworkImage
import com.swancodes.travelapp.presentation.viewmodel.TravelViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailScreen(
    navController: NavController,
    viewModel: TravelViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val tripState = viewModel.trip.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.plan_a_trip),
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {

            NetworkImage(
                url = tripState?.imageUrl.orEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            AppSpacer(height = 16.dp)

            TripDetailSection(trip = tripState)
        }
    }
}

@Composable
fun TripDetailSection(trip: Trip?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {

        Text(
            text = trip?.title.orEmpty(),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = trip?.description.orEmpty(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}