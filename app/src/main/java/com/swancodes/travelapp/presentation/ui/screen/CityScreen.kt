package com.swancodes.travelapp.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.swancodes.travelapp.R
import com.swancodes.travelapp.data.model.City
import com.swancodes.travelapp.presentation.ui.component.AppIconButton
import com.swancodes.travelapp.presentation.ui.component.AppSpacer
import com.swancodes.travelapp.presentation.ui.widget.CityItem
import com.swancodes.travelapp.presentation.viewmodel.TravelViewModel
import com.swancodes.travelapp.ui.theme.TextSecondary
import com.swancodes.travelapp.ui.theme.TravelAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityScreen(
  navController: NavController,
    viewModel: TravelViewModel = viewModel()) {
    var searchText by remember { mutableStateOf("") }

    val cities = listOf(
        City("Laghouat", "Algeria", "Laghouat", "DZ", "🇩🇿"),
        City("Lagos", "Nigeria", "Muritala Muhammed", "NG", "🇳🇬"),
        City("Doha", "Qatar", "Doha", "QA", "🇶🇦")
    )

    Scaffold(
        topBar = {
            Box (modifier = Modifier.background(Color.White)){
                TopAppBar(
                    title = { Text(stringResource(R.string.where), style = MaterialTheme.typography.headlineSmall) },
                    navigationIcon = {
                        AppIconButton(
                            onClick = { navController.popBackStack() },
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
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.select_a_city),
                style = MaterialTheme.typography.bodyMedium,
                color = TextSecondary,
                modifier = Modifier.padding(bottom = 16.dp, top = 4.dp)
            )

            // Search Field
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .height(76.dp)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(4.dp)
                    ),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.primary,
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                ),
                placeholder = {
                    Text(
                        text = stringResource(R.string.search_city),
                        style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
                    )
                },
                singleLine = true
            )

            AppSpacer(height = 8.dp)

            // List of Cities
            LazyColumn {
                items(cities) { city ->
                    CityItem(
                        city = city,
                        onClick = {
                            viewModel.setCity(city.name)
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
