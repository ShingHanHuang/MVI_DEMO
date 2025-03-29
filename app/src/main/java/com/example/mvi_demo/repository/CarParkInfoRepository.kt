package com.example.mvi_demo.repository

import android.util.Log
import com.example.mvi_demo.api.ApiResult
import com.example.mvi_demo.api.CarParkService
import com.example.mvi_demo.data.CarParkAvailableResponse
import com.example.mvi_demo.data.CarParkDetailResponse
import com.example.mvi_demo.data.CarParkInfo


class CarParkInfoRepository(
    private val carParkService: CarParkService
) : BaseRepository() {
    suspend fun getAllCarParkDetail(): ApiResult<CarParkDetailResponse> {
        return Call { carParkService.getAllCarParkDetail() }
    }

    suspend fun getAvailableCarParkDetail(): ApiResult<CarParkAvailableResponse> {
        return Call { carParkService.getAvailableCarParkDetail() }
    }

    fun mappingCarParkData(
        detailResponse: CarParkDetailResponse,
        availableResponse: CarParkAvailableResponse
    ): ArrayList<CarParkInfo> {
        Log.e("peter", "mCarParkDetailResponse: ${detailResponse.park.size}")
        Log.e("peter", "mCarParkAvailableResponse: ${availableResponse.park.size}")
        val carParkDetailMap = detailResponse.park.associateBy { it.id }
        val carParkInfoList = arrayListOf<CarParkInfo>()
        availableResponse.park.forEach { availableItem ->
            carParkDetailMap[availableItem.id]?.let { detailItem ->
                carParkInfoList.add(CarParkInfo().apply {
                    id = detailItem.id
                    address = detailItem.address
                    name = detailItem.name
                    totalcar = detailItem.totalcar
                    availablecar = availableItem.availablecar
                    ChargeStation = availableItem.ChargeStation
                    calculateChargeStationStatus()
                })
            }
        }
        return carParkInfoList
    }
}