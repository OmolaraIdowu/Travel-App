package com.swancodes.travelapp.presentation.ui.component

import android.net.Network
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.swancodes.travelapp.R
import com.swancodes.travelapp.ui.theme.Error100
import com.swancodes.travelapp.ui.theme.Error900
import com.swancodes.travelapp.ui.theme.Neutral400

@Composable
fun AppImage(
    @DrawableRes imageResId: Int,
    contentDescription: String? = null,
    modifier: Modifier = Modifier.fillMaxSize(),
    contentScale: ContentScale = ContentScale.Crop
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    contentDescription: String = "Trip image"
) {
    AsyncImage(
        model = url.takeIf { it.isNotEmpty() },
        contentDescription = contentDescription,
        placeholder = ColorPainter(Color.LightGray),
        error = painterResource(R.drawable.ic_broken_image),
        modifier = modifier,
        contentScale = contentScale
    )
}

@Composable
fun LoadingIndicator() =
    Box(Modifier
        .fillMaxWidth()
        .padding(32.dp), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }

@Composable
fun ErrorMessage(message: String?, onRetry: () -> Unit) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
    colors = CardDefaults.cardColors(containerColor = Error100)
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(message ?: "An error occurred", color = Error900)
        AppButton("Retry", onClick = onRetry, modifier = Modifier.padding(top = 14.dp))
    }
}

@Composable
fun EmptyContent() =
    Box(Modifier
        .fillMaxWidth()
        .padding(32.dp), contentAlignment = Alignment.Center) {
        Text("No trips found", color = Neutral400)
    }