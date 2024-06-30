package dev.borisochieng.swipeshop.data

interface ProductRepository {
    fun getProducts (): List<Product>
}