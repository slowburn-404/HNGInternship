package dev.borisochieng.swipeshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.borisochieng.swipeshop.data.Product
import dev.borisochieng.swipeshop.data.ProductRepository
import kotlinx.coroutines.launch

class SharedViewModel(
    private val productsRepository: ProductRepository
) : ViewModel() {
    private val _allProducts = MutableLiveData<List<Product>>()
    val allProducts: LiveData<List<Product>> get() = _allProducts


    private val _cartProducts = MutableLiveData<List<Product>>()
    val cartProducts: LiveData<List<Product>> get() = _cartProducts

    private val _badgeCount = MutableLiveData<Int>()
    val badgeCount: LiveData<Int> get() = _badgeCount

    init {
        getProducts()
    }


    private fun getProducts() =
        viewModelScope.launch {
            try {
                _allProducts.value = productsRepository.getProducts()
                updateCartProducts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    private fun updateCartProducts() {
        val cartItems = _allProducts.value?.filter { product ->
            product.isAddedToCart
        } ?: emptyList()
        _cartProducts.value = cartItems
        _badgeCount.value = cartItems.size
    }


    fun updateProducts(updatedProduct: Product) {
        val updatedProducts = _allProducts.value?.map { product ->
            if (product.name == updatedProduct.name) {
                updatedProduct
            } else {
                product
            }
        }
        _allProducts.value = updatedProducts
        updateCartProducts()
    }
}

@Suppress("UNCHECKED_CAST")
class SharedViewModelFactory(val productsRepository: ProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(productsRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}