package com.swancodes.travelapp.presentation.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swancodes.travelapp.R
import com.swancodes.travelapp.presentation.ui.component.AppButton
import com.swancodes.travelapp.presentation.ui.component.AppImage
import com.swancodes.travelapp.presentation.ui.component.AppSpacer
import com.swancodes.travelapp.presentation.ui.component.SelectionButton
import com.swancodes.travelapp.ui.theme.SatoshiFontFamily

@Composable
fun TripPlannerSection(
    selectedCity: String,
    startDate: String,
    endDate: String,
    onCityClick: () -> Unit,
    onStartDateClick: () -> Unit,
    onEndDateClick: () -> Unit,
    onCreateTrip: () -> Unit
) {
    Box(modifier = Modifier.height(549.dp)) {
        AppImage(R.drawable.body_1)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = stringResource(R.string.plan_dream),
                style = MaterialTheme.typography.headlineSmall,
                fontFamily = SatoshiFontFamily,
            )

            AppSpacer(height = 8.dp)

            Text(
                text = stringResource(R.string.plan_dream_sub),
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 22.sp,
            )

            AppSpacer(height = 122.dp)
            TripPlannerCard(
                selectedCity = selectedCity,
                startDate = startDate,
                endDate = endDate,
                onCityClick = onCityClick,
                onStartDateClick = onStartDateClick,
                onEndDateClick = onEndDateClick,
                onCreateTrip = onCreateTrip
            )
        }
    }
}

@Composable
fun TripPlannerCard(
    selectedCity: String,
    startDate: String,
    endDate: String,
    onCityClick: () -> Unit = {},
    onStartDateClick: () -> Unit = {},
    onEndDateClick: () -> Unit = {},
    onCreateTrip: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SelectionButton(
                icon = R.drawable.ic_location_outline,
                label = R.string.where_to,
                hint = R.string.select_city,
                value = selectedCity,
                onClick = onCityClick
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SelectionButton(
                    icon = R.drawable.ic_calendar,
                    label = R.string.start_date,
                    hint = R.string.enter_date,
                    value = startDate,
                    onClick = onStartDateClick,
                    modifier = Modifier.weight(1f)
                )

                SelectionButton(
                    icon = R.drawable.ic_calendar,
                    label = R.string.end_date,
                    hint = R.string.enter_date,
                    value = endDate,
                    onClick = onEndDateClick,
                    modifier = Modifier.weight(1f)
                )
            }

            AppButton(
                text = stringResource(id = R.string.create_trip),
                textStyle = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),
                enabled = selectedCity.isNotEmpty() && startDate.isNotEmpty() && endDate.isNotEmpty(),
                onClick = onCreateTrip,
            )
        }
    }
}