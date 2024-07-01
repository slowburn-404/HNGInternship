package dev.borisochieng.swipeshop.data

import java.util.UUID
import java.util.UUID.randomUUID

data class Product(
    val name: String,
    val price: Int,
    var quantity: Int = 1,
    var isAddedToCart: Boolean = false
)
