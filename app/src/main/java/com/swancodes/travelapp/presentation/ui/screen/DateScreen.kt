package com.swancodes.travelapp.presentation.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.swancodes.travelapp.R
import com.swancodes.travelapp.presentation.ui.component.AppButton
import com.swancodes.travelapp.presentation.ui.component.AppIconButton
import com.swancodes.travelapp.presentation.ui.component.AppSpacer
import com.swancodes.travelapp.presentation.viewmodel.TravelViewModel
import com.swancodes.travelapp.ui.theme.Neutral600
import com.swancodes.travelapp.ui.theme.Primary100
import com.swancodes.travelapp.ui.theme.Primary600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateScreen(
    navController: NavController,
    viewModel: TravelViewModel = viewModel(),
) {
    val state = rememberDateRangePickerState()

    Scaffold(
        topBar = {
            Box(modifier = Modifier.background(Color.White)) {
                TopAppBar(
                    title = { Text(stringResource(R.string.date), style = MaterialTheme.typography.titleMedium) },
                    navigationIcon = {
                       AppIconButton(
                                onClick = {navController.popBackStack()},
                        iconRes = R.drawable.ic_arrow_close,
                        contentDescriptionRes = R.string.back
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black
                    )
                )
                HorizontalDivider(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth(),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.start_date),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Text(
                        text = stringResource(id = R.string.end_date),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }

                AppSpacer(height = 4.dp)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DateCard(
                        text = state.selectedStartDateMillis?.let { formatDateShort(it) } ?: "",
                        modifier = Modifier.weight(1f)
                    )
                    DateCard(
                        text = state.selectedEndDateMillis?.let { formatDateShort(it) } ?: "",
                        modifier = Modifier.weight(1f)
                    )
                }

                AppSpacer(height = 16.dp)

                AppButton(
                    text = stringResource(id = R.string.choose_date),
                    onClick = {
                        viewModel.setDates(
                            state.selectedStartDateMillis?.let { formatDateShort(it) } ?: "",
                            state.selectedEndDateMillis?.let { formatDateShort(it) } ?: ""
                        )
                        navController.popBackStack()
                    },
                    enabled = state.selectedStartDateMillis != null && state.selectedEndDateMillis != null
                )

            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            DateRangePicker(
                state = state,
                showModeToggle = false,
                modifier = Modifier.fillMaxWidth().background(Color.White),
                headline = {
                    Box(modifier = Modifier.height(1.dp)) {}
                },
                title = { Box(modifier = Modifier.height(1.dp)) {}
                },
                colors = DatePickerDefaults.colors(
                    containerColor = Color.White,
                    selectedDayContainerColor = MaterialTheme.colorScheme.primary,
                    selectedDayContentColor = Color.White,
                    dayInSelectionRangeContainerColor = Primary100,
                    dayInSelectionRangeContentColor = Primary600,
                    todayDateBorderColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

//private fun formatMonth(millis: Long): String {
//    val formatter = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
//    return formatter.format(Date(millis))
//}

@Composable
fun DateCard(
    text: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Neutral600
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = text.ifEmpty { "Select date" },
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Image(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = null
            )
        }
    }
}