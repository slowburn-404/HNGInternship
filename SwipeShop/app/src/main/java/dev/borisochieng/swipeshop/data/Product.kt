package dev.borisochieng.swipeshop.data

import java.util.UUID
import java.util.UUID.randomUUID

data class Product(
    val id: UUID = randomUUID(),
    val name: String,
    val price: Int,
    val quantity: Int = 1,
    val isAddedToCart: Boolean
)
