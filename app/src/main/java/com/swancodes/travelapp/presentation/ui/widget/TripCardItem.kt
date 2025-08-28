package com.swancodes.travelapp.presentation.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.swancodes.travelapp.R
import com.swancodes.travelapp.data.model.Trip
import com.swancodes.travelapp.presentation.ui.component.AppButton
import com.swancodes.travelapp.presentation.ui.component.AppSpacer
import com.swancodes.travelapp.presentation.ui.component.NetworkImage
import com.swancodes.travelapp.ui.theme.Neutral400
import com.swancodes.travelapp.ui.theme.TextBlack
import com.swancodes.travelapp.ui.theme.TextSecondary
import com.swancodes.travelapp.ui.theme.TravelAppTheme

@Composable
fun TripCard(
    trip: Trip,
    onViewClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth().padding(16.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = Neutral400
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Box(modifier = Modifier.height(230.dp)) {
                NetworkImage(
                    url = trip.imageUrl ?: "",
                    modifier = Modifier.fillMaxSize(),
                )
                LocationLabel(
                    modifier = Modifier.align(Alignment.TopEnd),
                    location = trip.location
                )
            }
            AppSpacer(height = 14.dp)

            Column {
                Text(
                    text = trip.title,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                AppSpacer(height = 8.dp)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = trip.startDate,
                        style = MaterialTheme.typography.bodyMedium.copy(color = TextBlack)
                    )
                    Text(
                        text = "${trip.days} Days",
                        style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
                    )
                }
            }
            AppSpacer(height = 14.dp)

            AppButton(
                text = stringResource(id = R.string.view),
                onClick = onViewClick
            )

        }
    }
}

@Composable
fun LocationLabel(location: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(horizontal = 18.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(4.dp))
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Gray.copy(alpha = 0.5f))
                .blur(16.dp)
        )

        Text(
            text = location,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}