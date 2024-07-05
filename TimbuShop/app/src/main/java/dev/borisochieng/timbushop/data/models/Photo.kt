package dev.borisochieng.timbushop.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("model_name") val modelName: String,
    @SerializedName("model_id") val modelId: String,
    @SerializedName("organization_id") val organizationId: String,
    @SerializedName("filename") val filename: String,
    @SerializedName("url") val url: String,
    @SerializedName("is_featured") val isFeatured: Boolean,
    @SerializedName("save_as_jpg") val saveAsJpg: Boolean,
    @SerializedName("is_public") val isPublic: Boolean,
    @SerializedName("file_rename") val fileRename: Boolean,
    val position: Int
) : Serializable
