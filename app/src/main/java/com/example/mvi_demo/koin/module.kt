package com.example.mvi_demo.koin


import com.example.mvi_demo.adapter.CarParkInfoAdapter
import com.example.mvi_demo.api.CarParkService
import com.example.mvi_demo.api.LoginService
import com.example.mvi_demo.repository.CarParkInfoRepository
import com.example.mvi_demo.repository.LoginRepository
import com.example.mvi_demo.viewmodel.CarParkInfoViewModel
import com.example.mvi_demo.viewmodel.LoginViewModel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { CarParkInfoViewModel(get()) }
}

val repositoryModule = module {
    single { LoginRepository(get()) }
    single { CarParkInfoRepository(get()) }
}

val networkModule = module {
    single<LoginService> {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://noodoe-app-development.web.app")
            .build()
            .create(LoginService::class.java)
    }
    single<CarParkService> {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://tcgbusfs.blob.core.windows.net/blobtcmsv/")
            .build()
            .create(CarParkService::class.java)
    }

}
val adapterModule = module {
    factory { (context: android.content.Context) -> CarParkInfoAdapter(context) }
}