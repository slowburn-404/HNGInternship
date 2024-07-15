package dev.borisochieng.timbushop.domain.models

data class DomainProduct(
    val id: String,
    val name: String,
    val description: String,
    val imageURL: String,
    val price: Double,
    val category: List<DomainCategory>,
    var isAddedToCart: Boolean = false,
    val availableQuantity: Int,
    var quantity: Int
)
