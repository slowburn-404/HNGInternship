package dev.borisochieng.timbushop

import android.app.Application
import dev.borisochieng.timbushop.data.repository.TimbuAPIRepository

class TimbuShopApplication : Application() {
    val timbuAPIRepository: TimbuAPIRepository by lazy {
        TimbuAPIRepository()
    }
}