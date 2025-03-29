package com.example.mvi_demo.data

data class CarParkDetailResponse(
    val UPDATETIME: String,
    val park: List<Park>
)

data class Park(
    val AED_Equipment: String,
    val Accessibility_Elevator: String,
    val CellSignal_Enhancement: String,
    val ChargingStation: String,
    val Child_Pickup_Area: String,
    val EntranceCoord: EntranceCoord,
    val FareInfo: FareInfo,
    val Handicap_First: String,
    val Phone_Charge: String,
    val Pregnancy_First: String,
    val Taxi_OneHR_Free: String,
    val address: String,
    val area: String,
    val id: String,
    val name: String,
    val payex: String,
    val serviceTime: String,
    val summary: String,
    val tel: String,
    val totalbike: Int,
    val totalbus: Int,
    val totalcar: Int,
    val totallargemotor: String,
    val totalmotor: Int,
    val tw97x: String,
    val tw97y: String,
    val type: String,
    val type2: String
)

data class EntranceCoord(
    val EntrancecoordInfo: List<EntrancecoordInfo>
)

data class FareInfo(
    val Holiday: List<Holiday>,
    val WorkingDay: List<WorkingDay>
)

data class EntrancecoordInfo(
    val Address: String,
    val Xcod: String,
    val Ycod: String
)

data class Holiday(
    val Fare: String,
    val Period: String
)

data class WorkingDay(
    val Fare: String,
    val Period: String
)