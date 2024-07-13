package dev.borisochieng.timbushop.util

import dev.borisochieng.timbushop.domain.models.DomainProduct

data class UIState(
    val isLoading: Boolean = false,
    val products: List<DomainProduct> = emptyList(),
    val errorMessage: String = ""
)
