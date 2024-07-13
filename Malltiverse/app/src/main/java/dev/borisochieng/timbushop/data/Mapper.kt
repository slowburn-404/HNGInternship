package dev.borisochieng.timbushop.data

import dev.borisochieng.timbushop.data.models.ProductResponse
import dev.borisochieng.timbushop.domain.models.DomainCategory
import dev.borisochieng.timbushop.domain.models.DomainProduct
import dev.borisochieng.timbushop.util.Constants.BASE_IMAGE_URL

fun ProductResponse.toDomainProduct(): List<DomainProduct> =
    items.map { product ->
        DomainProduct(
            name = product.name,
            description = product.description ?: "No description available",
            price = product.currentPrice.firstOrNull()?.kes?.first().toString(),
            imageURL = "$BASE_IMAGE_URL${product.photos?.firstOrNull()?.url}",
            category = product.categories?.map { category ->
                DomainCategory(
                    name = category.name.capitalizeWords(),
                )
            } ?: emptyList()
        )
    }

fun String.capitalizeWords(): String =
    split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }