package dev.borisochieng.swipeshop.data

class ProductRepositoryImpl : ProductRepository {

    override fun getProducts(): List<Product> = mutableListOf(
        Product(name = "Shirt", price = 50, isAddedToCart = false),
        Product(name = "Trouser", price = 50, isAddedToCart = false),
        Product(name = "Jeans", price = 100, isAddedToCart = false),
        Product(name = "Beanie", price = 50, isAddedToCart = false),
        Product(name = "iPhone", price = 1000, isAddedToCart = false),
        Product(name = "Vision Pro", price = 3000, isAddedToCart = false),
        Product(name = "Pixel", price = 1000, isAddedToCart = false),
        Product(name = "Air Fryer", price = 200, isAddedToCart = false),
        Product(name = "Water Bottle", price = 5, isAddedToCart = false),
        Product(name = "Non-stick pan", price = 20, isAddedToCart = false),
        Product(name = "Batteries", price = 2, isAddedToCart = false),
    )
}