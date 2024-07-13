package dev.borisochieng.timbushop.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("unique_id") val uniqueId: String,
    @SerializedName("url_slug") val urlSlug: String,
    @SerializedName("is_available") val isAvailable: Boolean,
    @SerializedName("is_service") val isService: Boolean,
    @SerializedName("previous_url_slugs") val previousUrlSlugs: Any?,
    @SerializedName("unavailable") val unavailable: Boolean,
    @SerializedName("unavailable_start") val unavailableStart: Any?,
    @SerializedName("unavailable_end") val unavailableEnd: Any?,
    @SerializedName("id") val id: String,
    @SerializedName("parent_product_id") val parentProductId: Any?,
    @SerializedName("parent") val parent: Any?,
    @SerializedName("organization_id") val organizationId: String,
    @SerializedName("stock_id") val stockId: Any?,
    @SerializedName("product_image") val productImage: List<Any>,
    @SerializedName("categories") val categories: List<Category>?,
    @SerializedName("date_created") val dateCreated: String,
    @SerializedName("last_updated") val lastUpdated: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("photos") val photos: List<Photo>?,
    @SerializedName("prices") val prices: Any?,
    @SerializedName("stocks") val stocks: Any?,
    @SerializedName("is_deleted") val isDeleted: Boolean,
    @SerializedName("available_quantity") val availableQuantity: Float?,
    @SerializedName("selling_price") val sellingPrice: Any?,
    @SerializedName("discounted_price") val discountedPrice: Any?,
    @SerializedName("buying_price") val buyingPrice: Any?,
    @SerializedName("extra_infos") val extraInfos: Any?,
    @SerializedName("featured_reviews") val featuredReviews: Any?,
    @SerializedName("unavailability") val unavailability: List<Any>,
    @SerializedName("current_price") val currentPrice:List<CurrentPrice>
) : Serializable
