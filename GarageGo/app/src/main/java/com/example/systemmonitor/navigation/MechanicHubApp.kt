package com.example.systemmonitor.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.systemmonitor.data.SampleData.mechanicDetails
import com.example.systemmonitor.ui.screens.ProfileScreen
import com.example.ui.screens.BookingsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MechanicDetailsScreen
import com.example.ui.screens.RequestServiceScreen
import com.example.ui.screens.RequestSubmittedScreen
import com.example.ui.theme.MechanicHubTheme
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun MechanicHubApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {

        // 1. Home Screen
        composable("home") {
            HomeScreen(
                onMechanicClick = { mechanicShop ->
                    // Pass the ID as an argument in the route
                    navController.navigate("details/${mechanicShop.id}")
                },
                onNavigateToBookings = {
                    navController.navigate("bookings") {
                        popUpTo("home") // Prevents stacking multiple copies in backstack
                        launchSingleTop = true
                    }
                },
                onNavigateToProfile = {
                    navController.navigate("profile") {
                        popUpTo("home")
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(
            route = "details/{mechanicId}",
            arguments = listOf(navArgument("mechanicId") { type = NavType.IntType })
        ) { backStackEntry ->
            val mechanicId = backStackEntry.arguments?.getInt("mechanicId") ?: -1
            val shopDetails = mechanicDetails[mechanicId]

            if (shopDetails != null) {
                MechanicDetailsScreen(
                    mechanicId = mechanicId,
                    onBackClick = { navController.popBackStack() }, // Goes back to previous screen
                    onRequestServiceClick = { navController.navigate("request_service/$mechanicId") },
                    onNavigateToHome = {
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    },
                    onNavigateToBookings = {
                        navController.navigate("bookings") { popUpTo("home"); launchSingleTop = true }
                    },
                    onNavigateToProfile = {
                        navController.navigate("profile") { popUpTo("home"); launchSingleTop = true }
                    }
                )
            }
        }

        composable(
            route = "request_service/{mechanicId}",
            arguments = listOf(navArgument("mechanicId") { type = NavType.IntType })
        ) { backStackEntry ->
            val mechanicId = backStackEntry.arguments?.getInt("mechanicId") ?: -1

            RequestServiceScreen(
                mechanicId = mechanicId,
                onBackClick = { navController.popBackStack() },
                onSubmitSuccess = {
                    // FIX: Remove "/$mechanicId" from this line
                    navController.navigate("request_submitted")
                },
                onNavigateToHome = {
                    navController.navigate("home") { popUpTo("home") { inclusive = true } }
                },
                onNavigateToBookings = {
                    navController.navigate("bookings") { popUpTo("home"); launchSingleTop = true }
                },
                onNavigateToProfile = {
                    navController.navigate("profile") { popUpTo("home"); launchSingleTop = true }
                }
            )
        }
        composable(route = "request_submitted") {
            RequestSubmittedScreen(
                onBackToHomeClick = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }

        composable("bookings") {
            BookingsScreen(
                onBackClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onNavigateToHome = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onNavigateToProfile = { navController.navigate("profile") { popUpTo("home"); launchSingleTop = true } },
                onBookAgainClick = { booking ->
                    // Since BookingItem lacks an ID right now, we default to 1 as a placeholder.
                    // Update this when your backend provides the ID in the bookings list.
                    navController.navigate("request_service/1")
                }
            )
        }

        // 6. Profile Screen
        composable("profile") {
            ProfileScreen(
                onBackClick = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onNavigateToHome = { navController.navigate("home") { popUpTo("home") { inclusive = true } } },
                onNavigateToBookings = { navController.navigate("bookings") { popUpTo("home"); launchSingleTop = true } }
            )
        }
    }
}