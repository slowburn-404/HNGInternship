package dev.borisochieng.timbushop.domain.models

import androidx.compose.runtime.Immutable

@Immutable
data class DomainProduct(
    val id: String,
    val name: String,
    val description: String,
    val imageURL: String,
    val price: Double,
    val category: List<DomainCategory>,
    val isAddedToCart: Boolean,
    val availableQuantity: Int,
    var quantity: Int
)
