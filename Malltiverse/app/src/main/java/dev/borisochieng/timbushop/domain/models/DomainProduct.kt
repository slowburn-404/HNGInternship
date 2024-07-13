package dev.borisochieng.timbushop.domain.models

data class DomainProduct(
    val name: String,
    val description: String,
    val imageURL: String,
    val price: String,
    val category: List<DomainCategory>,
    val isAddedToCart: Boolean = false
)
