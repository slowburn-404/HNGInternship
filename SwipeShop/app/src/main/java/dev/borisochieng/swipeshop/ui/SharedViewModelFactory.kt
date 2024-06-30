package dev.borisochieng.swipeshop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.borisochieng.swipeshop.data.ProductRepository

class SharedViewModelFactory(
    private val productsRepository: ProductRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SharedViewModel::class.java)) {
            return SharedViewModel(productsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")

    }
}