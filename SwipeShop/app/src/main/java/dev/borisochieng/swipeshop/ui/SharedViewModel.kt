package dev.borisochieng.swipeshop.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.borisochieng.swipeshop.data.Product
import dev.borisochieng.swipeshop.data.ProductRepository
import java.util.UUID

class SharedViewModel(
    private val productsRepository: ProductRepository
) {
    private val _productsList =  MutableLiveData<List<Product>>()
    val productsList: LiveData<List<Product>> get() = _productsList

    private val _cartItems = MutableLiveData<List<Product>>()
    val cartItems: LiveData<List<Product>> get() = _cartItems


    fun getProducts() =
        viewModelScope.launch{
            try {
                _productsList.value = productsRepository.getProducts()
            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }

    fun addProductToCart(id: UUID) =
        viewModelScope.launch{
            try {
             //TODO add selected product to _cartItems

            }
            catch (e: Exception) {
                e.printStackTrace()
            }
        }



    fun getCartProducts() =
        viewModelScope.launch {
           try {
               _cartItems.value = _productsList.value!!.filter { product ->
                   product.isAddedToCart
               }

           }
           catch (e: Exception){
              e.printStackTrace()
           }
        }


}