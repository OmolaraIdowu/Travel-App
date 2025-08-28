package com.swancodes.travelapp.presentation.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.swancodes.travelapp.R
import com.swancodes.travelapp.data.model.City
import com.swancodes.travelapp.presentation.ui.component.AppImage
import com.swancodes.travelapp.ui.theme.TextBlack
import com.swancodes.travelapp.ui.theme.TextSecondary

@Composable
fun CityItem(
    city: City,
    onClick: (City) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(city) }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppImage(R.drawable.ic_location_filled,  contentDescription = "Location", modifier = Modifier.size(22.dp))

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${city.name}, ${city.country}",
                style = MaterialTheme.typography.titleSmall.copy(fontSize = 16.sp),
                color = TextBlack
            )

            Text(
                text = city.region,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
            )
        }

        // Flag and Country Code
        Column(
            horizontalAlignment = Alignment.End
        ) {
            AsyncImage(
                model = city.flagEmojiUrl,
                contentDescription = "${city.country} flag",
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = city.countryCode,
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
            )
        }
    }
}