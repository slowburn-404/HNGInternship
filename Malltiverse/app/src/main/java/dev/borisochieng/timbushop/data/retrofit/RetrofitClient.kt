package dev.borisochieng.timbushop.data.retrofit

import dev.borisochieng.timbushop.data.service.TimbuAPIService
import dev.borisochieng.timbushop.util.Constants.TIMBU_API_BASE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    val instance: Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request: Request =
                    chain.request()
                        .newBuilder()
                        .build()
                chain.proceed(request)
            }.build()

        Retrofit.Builder()
            .baseUrl(TIMBU_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }
}