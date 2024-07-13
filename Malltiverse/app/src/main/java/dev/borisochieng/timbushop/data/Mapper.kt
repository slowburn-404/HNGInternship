package dev.borisochieng.timbushop.data

import dev.borisochieng.timbushop.data.models.ProductResponse
import dev.borisochieng.timbushop.domain.models.DomainCategory
import dev.borisochieng.timbushop.domain.models.DomainProduct
import dev.borisochieng.timbushop.util.Constants.BASE_IMAGE_URL
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun ProductResponse.toDomainProduct(): List<DomainProduct> =
    items.map { product ->
        DomainProduct(
            name = product.name,
            description = product.description ?: "No description available",
            price = formatCurrency(
                product.currentPrice.firstOrNull()?.ngn?.first() ?: 0.0,
                "NGN"
            ),
            imageURL = "$BASE_IMAGE_URL${product.photos?.firstOrNull()?.url}",
            category = product.categories?.map { category ->
                DomainCategory(
                    name = category.name.capitalizeWords(),
                )
            } ?: emptyList()
        )
    }

fun String.capitalizeWords(): String =
    split(" ")
        .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }


fun formatCurrency(value: Any, currencyCode: String): String {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    numberFormat.currency = Currency.getInstance(currencyCode)
    numberFormat.maximumFractionDigits = 0 // Remove decimal places
    return numberFormat.format(value)
}
