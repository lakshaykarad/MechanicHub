package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.systemmonitor.data.SampleData
import com.example.systemmonitor.data.ServiceRequestCreate // Import your data class
import com.example.systemmonitor.ui.viewmodel.MechanicViewModel // Import your ViewModel
import com.example.ui.components.MechanicHubBottomNav
import com.example.ui.components.MechanicHubTopAppBar
import com.example.ui.theme.Background
import com.example.ui.theme.OnPrimary
import com.example.ui.theme.OnSurface
import com.example.ui.theme.OnSurfaceVariant
import com.example.ui.theme.OutlineVariant
import com.example.ui.theme.PrimaryNavy
import com.example.ui.theme.SurfaceContainerLowest
import com.example.ui.theme.ErrorRed
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RequestServiceScreen(
    mechanicId: Int,
    onBackClick: () -> Unit = {},
    onSubmitSuccess: (ServiceRequestCreate) -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToBookings: () -> Unit = {},
    onNavigateToProfile: () -> Unit = {},
    viewModel: MechanicViewModel = hiltViewModel()
) {
    var customerName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var vehicleNumber by remember { mutableStateOf("") }
    var serviceType by remember { mutableStateOf("") }
    var problemDescription by remember { mutableStateOf("") }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var isSubmitting by remember { mutableStateOf(false) }
    var hasAttemptedSubmit by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    val serviceOptions = SampleData.serviceCategories

    val isFormValid = customerName.isNotBlank() && phoneNumber.isNotBlank() &&
            vehicleNumber.isNotBlank() && serviceType.isNotBlank() &&
            problemDescription.isNotBlank()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Background),
        topBar = {
            MechanicHubTopAppBar(
                title = "Request Service",
                showBackButton = true,
                onBackClick = onBackClick,
                onProfileClick = onNavigateToProfile
            )
        },
        bottomBar = {
            MechanicHubBottomNav(
                selectedTab = "bookings",
                onTabSelected = { tab ->
                    when (tab) {
                        "home" -> onNavigateToHome()
                        "bookings" -> onNavigateToBookings()
                        "profile" -> onNavigateToProfile()
                    }
                }
            )
        },
        containerColor = Background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .testTag("request_service_screen_content"),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp), spotColor = Color(0x141A2B48))
                    .border(1.dp, OutlineVariant.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp),
                color = SurfaceContainerLowest
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Vehicle Details",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Customer Name *",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurfaceVariant
                        )
                        OutlinedTextField(
                            value = customerName,
                            onValueChange = { customerName = it },
                            isError = hasAttemptedSubmit && customerName.isBlank(),
                            placeholder = { Text(text = "John Doe", color = OnSurfaceVariant.copy(alpha = 0.6f)) },
                            modifier = Modifier.fillMaxWidth().testTag("input_customer_name"),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryNavy,
                                unfocusedBorderColor = OutlineVariant,
                                errorBorderColor = ErrorRed
                            )
                        )
                    }

                    // Phone Number
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Phone Number *",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurfaceVariant
                        )
                        OutlinedTextField(
                            value = phoneNumber,
                            onValueChange = { phoneNumber = it },
                            isError = hasAttemptedSubmit && phoneNumber.isBlank(),
                            placeholder = { Text(text = "+1 (555) 000-0000", color = OnSurfaceVariant.copy(alpha = 0.6f)) },
                            modifier = Modifier.fillMaxWidth().testTag("input_phone_number"),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryNavy,
                                unfocusedBorderColor = OutlineVariant,
                                errorBorderColor = ErrorRed
                            )
                        )
                    }

                    // Vehicle License/VIN
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Vehicle License/VIN *",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurfaceVariant
                        )
                        OutlinedTextField(
                            value = vehicleNumber,
                            onValueChange = { vehicleNumber = it.uppercase() },
                            isError = hasAttemptedSubmit && vehicleNumber.isBlank(),
                            placeholder = { Text(text = "ABC-1234", color = OnSurfaceVariant.copy(alpha = 0.6f)) },
                            modifier = Modifier.fillMaxWidth().testTag("input_vehicle_number"),
                            shape = RoundedCornerShape(8.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryNavy,
                                unfocusedBorderColor = OutlineVariant,
                                errorBorderColor = ErrorRed
                            )
                        )
                    }

                    // Service Type Dropdown
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Service Type *",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurfaceVariant
                        )
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(56.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(1.dp, if (hasAttemptedSubmit && serviceType.isEmpty()) ErrorRed else OutlineVariant, RoundedCornerShape(8.dp))
                                    .clickable { isDropdownExpanded = true }
                                    .padding(horizontal = 16.dp)
                                    .testTag("service_type_dropdown_trigger"),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = if (serviceType.isEmpty()) "Select a service..." else serviceType,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = if (serviceType.isEmpty()) OnSurfaceVariant.copy(alpha = 0.6f) else OnSurface
                                )
                                Icon(
                                    imageVector = Icons.Default.ExpandMore,
                                    contentDescription = "Expand",
                                    tint = OnSurfaceVariant
                                )
                            }

                            DropdownMenu(
                                expanded = isDropdownExpanded,
                                onDismissRequest = { isDropdownExpanded = false },
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .background(SurfaceContainerLowest)
                            ) {
                                serviceOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(text = option, style = MaterialTheme.typography.bodyMedium, color = OnSurface) },
                                        onClick = {
                                            serviceType = option
                                            isDropdownExpanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = "Problem Description *",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurfaceVariant
                        )
                        OutlinedTextField(
                            value = problemDescription,
                            onValueChange = { problemDescription = it },
                            isError = hasAttemptedSubmit && problemDescription.isBlank(),
                            placeholder = { Text(text = "Please describe the issue...", color = OnSurfaceVariant.copy(alpha = 0.6f)) },
                            modifier = Modifier.fillMaxWidth().height(120.dp).testTag("input_problem_description"),
                            shape = RoundedCornerShape(8.dp),
                            maxLines = 4,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryNavy,
                                unfocusedBorderColor = OutlineVariant,
                                errorBorderColor = ErrorRed
                            )
                        )
                    }

                    if (hasAttemptedSubmit && !isFormValid) {
                        Text(
                            text = "Please fill out all required fields.",
                            color = ErrorRed,
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    // Submit Request Button API Connection
                    Button(
                        onClick = {
                            hasAttemptedSubmit = true
                            if (isFormValid && !isSubmitting) {
                                isSubmitting = true
                                coroutineScope.launch {

                                    // 1. Package up all the variables into the Request object
                                    val requestData = ServiceRequestCreate(
                                        customer_name = customerName,
                                        phone_number = phoneNumber,
                                        vehicle_number = vehicleNumber,
                                        selected_service = serviceType,
                                        problem_description = problemDescription,
                                        mechanic_id = mechanicId
                                    )

                                    // 2. Send it to the ViewModel (Update "submitServiceRequest" to match your ViewModel's function name)
                                    viewModel.submitServiceRequest(requestData)

                                    // 3. Small delay for UX, then navigate to the Success Screen
                                    delay(400)
                                    isSubmitting = false
                                    onSubmitSuccess(requestData)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .shadow(elevation = 3.dp, shape = RoundedCornerShape(8.dp), spotColor = Color(0x201A2B48))
                            .testTag("submit_request_button"),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryNavy,
                            contentColor = OnPrimary
                        ),
                        enabled = !isSubmitting
                    ) {
                        if (isSubmitting) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp), color = OnPrimary, strokeWidth = 2.dp)
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(text = "Processing...", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold)
                        } else {
                            Text(text = "Submit Request", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}