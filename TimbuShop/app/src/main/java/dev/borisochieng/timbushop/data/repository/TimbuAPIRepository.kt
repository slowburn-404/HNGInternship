package dev.borisochieng.timbushop.data.repository

import dev.borisochieng.timbushop.data.NetworkResponse
import dev.borisochieng.timbushop.data.models.ProductResponse
import dev.borisochieng.timbushop.data.retrofit.RetrofitClient.timbuAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TimbuAPIRepository{
    suspend fun getProducts(
        apiKey: String,
        organizationID: String,
        appID: String
    ): NetworkResponse<ProductResponse> =
        withContext(Dispatchers.IO) {
                try{
                    val response = timbuAPIService.getProducts(apiKey, organizationID, appID)

                    if(response.isSuccessful) {
                        NetworkResponse.Success(response.body())
                    } else {
                        NetworkResponse.Error(response.message())
                    }
                }
                catch(e:Exception){
                    e.printStackTrace()
                    NetworkResponse.Error(e.message ?: "An error occurred")
                }
        }
}