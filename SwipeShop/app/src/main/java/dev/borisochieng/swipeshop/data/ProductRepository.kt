package dev.borisochieng.swipeshop.data

class ProductRepository {

    fun getProducts(): List<Product> = mutableListOf(
        Product(name = "iPhone"),
        Product(name = "M1 MacBook"),
        Product(name = "Vision Pro"),
        Product(name = "Pixel 8"),
        Product(name = "Galaxy S24"),
        Product(name = "PS5"),
        Product(name = "Meta Quest"),
        Product(name = "Rabbit R1"),
        Product(name = "Legion 5 Pro"),
        Product(name = "Mi 13 "),

        )
}