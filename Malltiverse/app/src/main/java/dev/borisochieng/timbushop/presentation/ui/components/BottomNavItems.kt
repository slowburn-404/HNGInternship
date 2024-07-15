package dev.borisochieng.timbushop.presentation.ui.components

sealed class BottomNavItems(
    val route: String,
    val title: String
) {
    data object Home : BottomNavItems(
        route = "home",
        title = "Product List"
    )

    data object Cart : BottomNavItems(
        route = "cart",
        title = "My Cart"
    )

    data object Checkout : BottomNavItems(
        route = "checkout",
        title = "Checkout"
    )

//    data object Payment : BottomNavItems(
//        route = "payment",
//        title = "Payment"
//    )
//
//    data object PaymentSuccess : BottomNavItems(
//        route = "payment_success",
//        title = "Payment Success"
//    )


}