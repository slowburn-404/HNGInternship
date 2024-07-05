package dev.borisochieng.timbushop.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CurrentPrice(
    @SerializedName("KES") val kes: List<Any?>
) : Serializable
