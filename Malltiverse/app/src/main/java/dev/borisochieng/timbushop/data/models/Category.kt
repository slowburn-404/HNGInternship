package dev.borisochieng.timbushop.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("organization_id") val organizationId: String,
    @SerializedName("name") val name: String,
    @SerializedName("position") val position: Int? = null,
    @SerializedName("category_type") val categoryType: String,
    @SerializedName("description") val description: String? = null,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("id") val id: String,
    @SerializedName("parent_id") val parentId: String? = null,
    @SerializedName("url_slug") val urlSlug: String? = null,
    @SerializedName("is_deleted") val isDeleted: Boolean,
    @SerializedName("date_created") val dateCreated: String,
    @SerializedName("subcategories") val subcategories: List<Category> = emptyList(),
    @SerializedName("parents") val parents: List<Category> = emptyList()
) : Serializable
