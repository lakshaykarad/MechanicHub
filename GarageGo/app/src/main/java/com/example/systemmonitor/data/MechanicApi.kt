package com.example.systemmonitor.data

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MechanicApi {
    @GET("mechanics")
    suspend fun getMechanics() :  List<MechanicListResponse>
    @GET("mechanics/{id}")
    suspend fun getMechanicDetail(@Path("id") id: Int) : MechanicDetailResponse
    @POST("service-requests")
    suspend fun createServiceRequest(@Body request: ServiceRequestCreate ) : ServiceRequestResponse
}