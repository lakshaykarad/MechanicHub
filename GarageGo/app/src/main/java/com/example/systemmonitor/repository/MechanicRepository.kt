package com.example.systemmonitor.repository

import android.util.Log
import com.example.systemmonitor.common.Resource
import com.example.systemmonitor.data.BookingItem
import com.example.systemmonitor.data.MechanicApi
import com.example.systemmonitor.data.MechanicDetailResponse
import com.example.systemmonitor.data.MechanicListResponse
import com.example.systemmonitor.data.SampleData
import com.example.systemmonitor.data.SampleData.bookingsList
import com.example.systemmonitor.data.ServiceRequestCreate
import com.example.systemmonitor.data.ServiceRequestResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MechanicRepository @Inject constructor(
    private val api : MechanicApi
) {
    private val bookingHistory = bookingsList.toMutableList()


    suspend fun getMechanicList() : Resource<List<MechanicListResponse>>{
        return try {
            val data = api.getMechanics()
            Resource.Success(data)
        }catch (e: Exception){
            Log.d("api","Something went worng: ${e.message}")
            Resource.Success(SampleData.mechanicList)
        }
    }

    suspend fun getMechanicDetail(id : Int) : Resource<MechanicDetailResponse>{
        return try {
            val data = api.getMechanicDetail(id)
            Resource.Success(data)
        }catch (e  : Exception){
            Log.d("api","Something went worng: ${e.message}")
            val data : MechanicDetailResponse = SampleData.mechanicDetails.getValue(id)
            Resource.Success<MechanicDetailResponse>(data)
        }
    }

    suspend fun serviceRequest(request: ServiceRequestCreate) : Resource<ServiceRequestResponse>{
        return try {
            val data = api.createServiceRequest(request)
            Resource.Success(data)
        }catch (e : Exception){
            Log.d("api","Something went worng: ${e.message}")
            // -1 will not effect our data
            val data = ServiceRequestResponse("Service request submitted", request_id = -1)
            Resource.Success(data)
        }
    }

    suspend fun submitServiceRequest(request: ServiceRequestCreate): Resource<ServiceRequestResponse> {
        return try {
            val response = api.createServiceRequest(request)
            bookingHistory.add(
                BookingItem(
                    mechanicName = "Mechanic #${request.mechanic_id}",
                    date = "Today",
                    plateNumber = request.vehicle_number,
                    vehicleModel = "Unknown"
                )
            )
            Resource.Success(response)
        } catch (e: Exception) {
            bookingHistory.addAll(SampleData.bookingsList)
            Resource.Success(ServiceRequestResponse("Submitted offline", -1))
        }
    }

    fun getBookingHistory(): List<BookingItem> = bookingHistory

}