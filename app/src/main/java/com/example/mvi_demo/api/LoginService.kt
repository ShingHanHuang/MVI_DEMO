package com.example.mvi_demo.api

import com.example.mvi_demo.data.JsonBody
import com.example.mvi_demo.data.UserInfo

import retrofit2.http.*

interface LoginService {
    //https://noodoe-app-development.web.app/api/login
    @Headers(
        "Content-Type: application/json",
        "X-Parse-Application-Id: vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD",
        "X-Parse-Revocable-Session: 1"
    )
    @POST("api/login")
    suspend fun login(@Body body: JsonBody): HttpResponse<UserInfo>

    //Url:https://noodoe-app-development.web.app/api/users/{user object id}
//    @Headers("Content-Type: application/json","X-Parse-Application-Id: vqYuKPOkLQLYHhk4QTGsGKFwATT4mBIGREI2m8eD","X-Parse-REST-API-Key: 321")
//    @PUT("api/users/{obid}")
//    fun upDateTimeZone(@Header("X-Parse-Session-Token") token:String,@Path("obid") key:String ,@Body body: JsonBody): Observable<TimeZoneResponse>

}