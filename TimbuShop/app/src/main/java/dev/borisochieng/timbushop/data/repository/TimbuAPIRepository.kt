package dev.borisochieng.timbushop.data.repository

import dev.borisochieng.timbushop.data.Resource
import dev.borisochieng.timbushop.data.models.ProductResponse
import dev.borisochieng.timbushop.data.retrofit.RetrofitClient.timbuAPIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TimbuAPIRepository{
    suspend fun getProducts(
        apiKey: String,
        organizationID: String,
        appID: String
    ): Resource<ProductResponse> =
        withContext(Dispatchers.IO) {
                try{
                    val response = timbuAPIService.getProducts(apiKey, organizationID, appID)

                    if(response.isSuccessful) {
                        Resource.Success(response.body())
                    } else {
                        Resource.Error(response.message())
                    }
                }
                catch(e:Exception){
                    e.printStackTrace()
                    Resource.Error(e.message ?: "An error occurred")
                }
        }
}