package com.example.mvi_demo.api

import com.example.mvi_demo.data.CarParkAvailableResponse
import com.example.mvi_demo.data.CarParkDetailResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface CarParkService {
    @Headers("Content-Type: application/json")
    @GET("TCMSV_alldesc.json")
    suspend fun getAllCarParkDetail(): HttpResponse<CarParkDetailResponse>

    //https://tcgbusfs.blob.core.windows.net/blobtcmsv/TCMSV_allavailable.json
    @Headers("Content-Type: application/json")
    @GET("TCMSV_allavailable.json")
    suspend fun getAvailableCarParkDetail(): HttpResponse<CarParkAvailableResponse>
}