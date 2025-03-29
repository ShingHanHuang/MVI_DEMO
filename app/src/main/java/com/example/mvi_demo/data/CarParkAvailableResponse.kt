package com.example.mvi_demo.data


data class CarParkAvailableResponse(
    val UPDATETIME: String,
    val park: List<AvailablePark>
)

data class AvailablePark(
    val ChargeStation: ChargeStation,
    val availablebus: Int,
    val availablecar: Int,
    val availablemotor: Int,
    val id: String
)

data class ChargeStation(
    val scoketStatusList: List<ScoketStatus>?
)

data class ScoketStatus(
    val spot_abrv: String,
    val spot_status: String
)
