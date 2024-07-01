package dev.borisochieng.swipeshop

import android.app.Application
import dev.borisochieng.swipeshop.data.ProductRepository

class SwipeShopApplication: Application() {
    val productsRepository by lazy { ProductRepository() }
}