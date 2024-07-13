package dev.borisochieng.timbushop

import android.app.Application
import dev.borisochieng.timbushop.data.repository.TimbuAPIRepositoryImpl
import dev.borisochieng.timbushop.data.retrofit.RetrofitClient
import dev.borisochieng.timbushop.data.service.TimbuAPIService

class TimbuShopApplication : Application() {
    val timbuAPIRepositoryImpl: TimbuAPIRepositoryImpl by lazy {
        TimbuAPIRepositoryImpl(RetrofitClient.instance.create(TimbuAPIService::class.java))
    }
}