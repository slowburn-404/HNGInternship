package dev.borisochieng.timbushop.util

import dev.borisochieng.timbushop.data.models.Product

data class UIState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val errorMessage: String = ""
)
