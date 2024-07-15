package dev.borisochieng.timbushop.presentation.ui.components

sealed class OtherNavItems(
    val route: String,
    val title: String
) {

    data object Payment : OtherNavItems(
        route = "payment",
        title = "Payment"
    )
    data object PaymentSuccess : OtherNavItems(
        route = "payment_success",
        title = ""
    )
}