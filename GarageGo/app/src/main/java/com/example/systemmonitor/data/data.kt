package com.example.systemmonitor.data

import kotlinx.serialization.Serializable

@Serializable
data class MechanicListResponse(
    val id: Int,
    val name: String,
    val rating: Double,
    val location_name: String,
    val is_open: Boolean
)

@Serializable
data class MechanicDetailResponse(
    val id: Int,
    val name: String,
    val rating: Double,
    val latitude: Double,
    val longitude: Double,
    val location_name: String,
    val address: String,
    val available_services: List<String>,
    val working_hours: String,
    val phone_number: String,
    val is_open: Boolean
)

@Serializable
data class ServiceRequestCreate(
    val customer_name: String,
    val phone_number: String,
    val vehicle_number: String,
    val selected_service: String,
    val problem_description: String,
    val mechanic_id: Int
)

@Serializable
data class ServiceRequestResponse(
    val message: String,
    val request_id: Int
)
// this is hardcoded
data class BookingItem(
    val mechanicName: String,
    val date: String,
    val plateNumber: String,
    val vehicleModel: String
)