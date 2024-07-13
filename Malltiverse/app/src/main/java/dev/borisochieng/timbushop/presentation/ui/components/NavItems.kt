package dev.borisochieng.timbushop.presentation.ui.components

sealed class NavItems(
    val route: String,
    val title: String
) {
    data object Home : NavItems(
        route = "home",
        title = "Product List"
    )

    data object Cart : NavItems(
        route = "cart",
        title = "My Cart"
    )

    data object Checkout : NavItems(
        route = "checkout",
        title = "Checkout"
    )

    data object Payment : NavItems(
        route = "payment",
        title = "Payment"
    )

    data object PaymentSuccess : NavItems(
        route = "payment_success",
        title = "Payment Success"
    )


}