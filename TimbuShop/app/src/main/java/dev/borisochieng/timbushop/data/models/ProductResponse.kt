package dev.borisochieng.timbushop.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("size") val size: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("debug") val debug: Any?,
    @SerializedName("previous_page") val previousPage: Any?,
    @SerializedName("next_page") val nextPage: String?,
    @SerializedName("items") val items: List<Product>
) : Serializable