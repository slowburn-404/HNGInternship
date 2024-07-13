package dev.borisochieng.timbushop.domain

import dev.borisochieng.timbushop.data.NetworkResponse
import dev.borisochieng.timbushop.domain.models.DomainProduct

interface TimbuAPIRepository {
    suspend fun getProducts(
        apiKey: String,
        organizationID: String,
        appID: String
    ): NetworkResponse<List<DomainProduct>>

}