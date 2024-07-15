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
            id = product.id,
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
            } ?: emptyList(),
            availableQuantity = product.availableQuantity?.toInt() ?: 0,
            quantity = 0
        )
    }

fun String.capitalizeWords(): String =
    split(" ")
        .joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }


fun formatCurrency(value: Any, currencyCode: String): Double {
    val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    numberFormat.currency = Currency.getInstance(currencyCode)
    numberFormat.maximumFractionDigits = 0 // Remove decimal places

    val formattedString = numberFormat.format(value)

    val cleanedString = formattedString
        .replace(Regex("[^\\d.]"), "")
        .toDoubleOrNull() ?: 0.0

    return cleanedString
}
