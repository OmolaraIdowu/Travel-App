package com.swancodes.travelapp.presentation.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.swancodes.travelapp.R
import com.swancodes.travelapp.presentation.ui.component.AppImage
import com.swancodes.travelapp.presentation.ui.component.AppSpacer
import com.swancodes.travelapp.ui.theme.Neutral300
import com.swancodes.travelapp.ui.theme.TextBlack

@Composable
fun TripsSection(
    selectedCategory: String?,
    onCategoryChange: (String?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val dropdownTrips = listOf("All Trips", "Planned Trips", "Invited Trips")

    Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 24.dp)) {
        Text(
            text = stringResource(id = R.string.your_trips),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = TextBlack
        )
        Text(
            text = stringResource(id = R.string.your_trips_sub),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 20.sp,
            letterSpacing = (-0.5).sp,
            color = Color.Gray
        )
        AppSpacer(height = 16.dp)

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(4.dp),
            colors = CardDefaults.cardColors(containerColor = Neutral300)
        ) {
            OutlinedButton(
                onClick = { expanded = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                border = BorderStroke(
                    width = 0.dp,
                    color = Color(0xFFE0E0E0)
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = selectedCategory ?: "Select Trip Category")
                    AppImage(
                        imageResId = if (expanded) R.drawable.ic_arrow_close
                        else R.drawable.ic_arrow_down,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth().background(Color.White)
            ) {
                dropdownTrips.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            onCategoryChange(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}