package dev.borisochieng.swipeshop

import android.app.Application
import dev.borisochieng.swipeshop.data.ProductRepositoryImpl

class SwipeShopApplication: Application() {
    val productsRepository by lazy { ProductRepositoryImpl() }
}