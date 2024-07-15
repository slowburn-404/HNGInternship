package dev.borisochieng.timbushop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.borisochieng.timbushop.data.NetworkResponse
import dev.borisochieng.timbushop.domain.TimbuAPIRepository
import dev.borisochieng.timbushop.domain.models.DomainProduct
import dev.borisochieng.timbushop.util.Constants.API_KEY
import dev.borisochieng.timbushop.util.Constants.APP_ID
import dev.borisochieng.timbushop.util.Constants.ORGANIZATION_ID
import dev.borisochieng.timbushop.util.UIEvents
import dev.borisochieng.timbushop.util.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val timbuAPIRepository: TimbuAPIRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(UIState())
    val uiState: StateFlow<UIState> get() = _uiState.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UIEvents>()
    val eventFlow: SharedFlow<UIEvents> get() = _eventFlow.asSharedFlow()

    private val _categories = MutableStateFlow<Map<String, List<DomainProduct>>>(emptyMap())
    val categories: StateFlow<Map<String, List<DomainProduct>>> get() = _categories.asStateFlow()

    private val _cartItems = MutableStateFlow<List<DomainProduct>>(emptyList())
    val cartItems: StateFlow<List<DomainProduct>> get() = _cartItems.asStateFlow()

    init {
        getProducts(
            apiKey = API_KEY,
            organizationID = ORGANIZATION_ID,
            appId = APP_ID
        )
    }


    fun getProducts(
        apiKey: String,
        organizationID: String,
        appId: String
    ) =
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    isLoading = true,
                    errorMessage = ""
                )
            }

            val productsResponse = timbuAPIRepository.getProducts(
                organizationID = organizationID,
                appID = appId,
                apiKey = apiKey
            )

            when (productsResponse) {
                is NetworkResponse.Success -> {
                    val allProducts = productsResponse.payLoad ?: emptyList()

                    val cartItems = getCartItems(allProducts)

                    withContext(Dispatchers.Default) {
                        val groupedProductsByCategory = allProducts.flatMap { product ->
                            product.category.map { category ->
                                category.name to product
                            }
                        }.groupBy({ it.first }, { it.second })

                        withContext(Dispatchers.Main) {
                            _categories.update { groupedProductsByCategory }
                            _cartItems.update { cartItems }
                            _uiState.update {
                                it.copy(isLoading = false, products = allProducts)
                            }
                        }
                    }

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            products = allProducts
                        )
                    }
                }

                is NetworkResponse.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = productsResponse.message
                        )
                    }

                    _eventFlow.emit(
                        UIEvents.SnackBarEvent(
                            message = productsResponse.message
                        )
                    )
                }
            }


        }

    fun toggleCart(product: DomainProduct) = viewModelScope.launch {
        val allProducts = _uiState.value.products.map { p ->
            if (p.id == product.id) {
                p.copy(isAddedToCart = !p.isAddedToCart, quantity = if (p.isAddedToCart) 0 else 1)
            } else {
                p
            }
        }

        _uiState.value = _uiState.value.copy(
            products = allProducts)

        _uiState.update {
            it.copy(products = allProducts)
        }
        _cartItems.update {
            allProducts.filter { it.isAddedToCart }
        }
    }

    fun updateQuantity(product: DomainProduct, newQuantity: Int) =
        viewModelScope.launch {
            val updatedCartItems = _cartItems.value.map {
                if (it.id == product.id) {
                    it.copy(quantity = newQuantity)
                } else {
                    it
                }
            }

            _cartItems.update { updatedCartItems }
        }

}


private fun getCartItems(cartItems: List<DomainProduct>): List<DomainProduct> =
    cartItems.filter { product ->
        product.isAddedToCart
    }


class MainActivityViewModelFactory(private val timbuAPIRepository: TimbuAPIRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(timbuAPIRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}