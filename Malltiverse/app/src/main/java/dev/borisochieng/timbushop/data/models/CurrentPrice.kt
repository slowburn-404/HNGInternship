package dev.borisochieng.timbushop.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CurrentPrice(
    @SerializedName("NGN") val currency: List<Any?>
) : Serializable
