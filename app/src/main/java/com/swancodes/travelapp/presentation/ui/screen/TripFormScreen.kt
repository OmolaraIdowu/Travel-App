package com.swancodes.travelapp.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.swancodes.travelapp.R
import com.swancodes.travelapp.presentation.ui.component.AppButton
import com.swancodes.travelapp.presentation.ui.component.AppIconButton
import com.swancodes.travelapp.presentation.ui.component.AppImage
import com.swancodes.travelapp.presentation.ui.component.AppSpacer
import com.swancodes.travelapp.presentation.viewmodel.TravelViewModel
import com.swancodes.travelapp.ui.theme.Primary600

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripFormBottomSheet(
    viewModel: TravelViewModel,
    onNextClick: () -> Unit = {},
    onBackClick: () -> Unit
) {
    val tripName by viewModel.tripName.collectAsState()
    val travelStyle by viewModel.travelStyle.collectAsState()
    val tripDescription by viewModel.tripDescription.collectAsState()
    val travelStyles = listOf("Solo", "Couple", "Family", "Group")
    var showDropdown by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.ic_trip),
                contentDescription = null,
                modifier = Modifier.size(44.dp)
            )
            AppIconButton(
                iconRes = R.drawable.ic_arrow_close,
                onClick = onBackClick,
                contentDescriptionRes = R.string.back,
            )
        }

        AppSpacer(height = 8.dp)

        Text(
            text = stringResource(id = R.string.create_a_trip),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = stringResource(id = R.string.create_a_trip_sub),
            style = MaterialTheme.typography.bodyMedium
        )
        AppSpacer(height = 12.dp)

        // Trip Name
        OutlinedTextField(
            value = tripName,
            onValueChange = { viewModel.setTripName(it) },
            label = { Text(stringResource(id = R.string.trip_name)) },
            placeholder = { Text(text = stringResource(R.string.enter_trip_name)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary600,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Primary600
            ),
            singleLine = true
        )

        Column {
            Text(
                text = stringResource(R.string.travel_style),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Box {
                OutlinedTextField(
                    value = travelStyle,
                    onValueChange = {},
                    placeholder = {
                        Text(
                            stringResource(R.string.select_travel_style),
                            color = Color.Gray
                        )
                    },
                    readOnly = true,
                    trailingIcon = {
                        AppImage(
                            imageResId = R.drawable.ic_arrow_down,
                            contentDescription = null,
                            modifier = Modifier
                                .size(20.dp)
                                .rotate(if (showDropdown) 180f else 0f),
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showDropdown = !showDropdown },
                    shape = RoundedCornerShape(4.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledBorderColor = if (showDropdown) Primary600 else Color.LightGray,
                        disabledTextColor = Color.Black
                    ),
                    enabled = false,
                    singleLine = true
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { showDropdown = !showDropdown })
            }

            if (showDropdown) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                ) {
                    Column {
                        travelStyles.forEach { style ->
                            val isSelected = travelStyle == style
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(38.dp)
                                    .clickable {
                                        viewModel.setTravelStyle(style)
                                        showDropdown = false
                                    }
                                    .background(if (isSelected) Primary600 else Color.Transparent)
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                            ) {
                                Text(
                                    text = style,
                                    color = if (isSelected) Color.White else Color.Black,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                if (isSelected) {
                                    AppImage(
                                        imageResId = R.drawable.ic_check,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Trip Description
        OutlinedTextField(
            value = tripDescription,
            onValueChange = { viewModel.setTripDescription(it) },
            label = { Text(text = stringResource(R.string.trip_description)) },
            placeholder = { Text(text = stringResource(R.string.trip_description_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary600,
                unfocusedBorderColor = Color.LightGray,
                cursorColor = Primary600
            ),
            maxLines = 5
        )

        AppSpacer(height = 16.dp)

        AppButton(
            text = stringResource(R.string.next),
            onClick =  onNextClick,
        )
    }
}
