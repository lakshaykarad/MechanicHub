package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.systemmonitor.data.MechanicListResponse
import com.example.systemmonitor.data.SampleData
import com.example.systemmonitor.data.SampleData.mechanicDetails
import com.example.systemmonitor.ui.viewmodel.MechanicViewModel
import com.example.ui.components.FilterChipItem
import com.example.ui.components.MechanicCard
import com.example.ui.components.MechanicHubBottomNav
import com.example.ui.components.MechanicHubTopAppBar
import com.example.ui.theme.Background
import com.example.ui.theme.OnSurface
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.OutlineVariant
import com.example.ui.theme.PrimaryNavy
import com.example.ui.theme.SurfaceContainerLowest

val serviceCategories = listOf("All Services", "Oil Change", "Brake Repair", "Tyre Change", "Car Wash", "AC Repair")

@Composable
fun HomeScreen(
    onMechanicClick: (MechanicListResponse) -> Unit = {},
    onNavigateToBookings: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    viewModel: MechanicViewModel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    var activeCategory by remember { mutableStateOf("All Services") }
    val mechanicsListState by viewModel.mechanicListState.collectAsStateWithLifecycle()

     val rawList: List<MechanicListResponse> = when (mechanicsListState) {
        is com.example.systemmonitor.common.Resource.Success -> mechanicsListState.data ?: emptyList()
        else -> {
            SampleData.mechanicList
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadMechanics()
    }

    val filteredMechanics: List<MechanicListResponse> = remember(searchText, activeCategory, rawList) {
        rawList.filter { mechanicShop ->
            /*
           Fetch the shop details to get the available service.
           User Search somthing and match it with location and shop name
           Filter active category  or default value All Services
          */
            val shopDetails = mechanicDetails[mechanicShop.id]
            val serviceTags = shopDetails?.available_services ?: emptyList()
            val matchesSearch = searchText.isBlank() ||
                    mechanicShop.name.contains(searchText, ignoreCase = true) ||
                    mechanicShop.location_name.contains(searchText, ignoreCase = true)

            val matchesCategory = activeCategory == "All Services" ||
                    serviceTags.contains(activeCategory)

            matchesSearch && matchesCategory
        }
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        topBar = {
            MechanicHubTopAppBar(
                title = "MechanicHub",
                showBackButton = false,
                onMenuClick = {},
                onProfileClick = onNavigateToProfile
            )
        },
        bottomBar = {
            MechanicHubBottomNav(
                selectedTab = "home",
                onTabSelected = { tab ->
                    when (tab) {
                        "bookings" -> onNavigateToBookings()
                        "profile" -> onNavigateToProfile()
                    }
                }
            )
        },
        containerColor = Background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("home_screen_content"),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Search Bar
            item {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = {
                        Text(
                            text = "Search mechanics, services...",
                            style = MaterialTheme.typography.bodyMedium,
                            color = OnSurfaceVariant
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = OnSurfaceVariant,
                            modifier = Modifier.size(22.dp)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 2.dp, shape = CircleShape, spotColor = Color(0x101A2B48))
                        .testTag("home_search_input"),
                    shape = CircleShape,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = SurfaceContainerLowest,
                        unfocusedContainerColor = SurfaceContainerLowest,
                        disabledContainerColor = SurfaceContainerLowest,
                        focusedBorderColor = PrimaryNavy,
                        unfocusedBorderColor = OutlineVariant,
                        focusedTextColor = OnSurface,
                        unfocusedTextColor = OnSurface
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    serviceCategories.forEach { category ->
                        FilterChipItem(
                            text = category,
                            isSelected = activeCategory == category,
                            onClick = { activeCategory = category }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }

            // Mechanics List
            items(filteredMechanics) { mechanicShop ->
                val shopDetails = mechanicDetails[mechanicShop.id]
                val serviceTags = shopDetails?.available_services ?: emptyList()

                MechanicCard(
                    name = mechanicShop.name,
                    location = mechanicShop.location_name,
                    rating = mechanicShop.rating,
                    isOpen = mechanicShop.is_open,
                    tags = serviceTags,
                    onClick = { onMechanicClick(mechanicShop) }
                )
            }
        }
    }
}