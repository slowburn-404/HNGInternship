package dev.borisochieng.timbushop.presentation

import android.util.Log
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

                    withContext(Dispatchers.Default) {
                        val groupedProductsByCategory = allProducts.flatMap { product ->
                            product.category.map { category ->
                                category.name to product
                            }
                        }.groupBy({ it.first }, { it.second })

                        withContext(Dispatchers.Main) {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    products = allProducts,
                                    categoriesWithProducts = groupedProductsByCategory
                                )
                            }
                        }
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
        val updatedCategories = _uiState.value.categoriesWithProducts.mapValues { (_, products) ->
            products.map { p ->
                if (p.id == product.id) {
                    p.copy(
                        isAddedToCart = !p.isAddedToCart,
                        quantity = if (!p.isAddedToCart) 1 else 1
                    )
                } else {
                    p
                }
            }
        }

        _uiState.update {
            it.copy(categoriesWithProducts = updatedCategories)
        }

        val updatedCartItems = getCartItems(updatedCategories)

        _uiState.update {
            it.copy(cartItems = updatedCartItems)
        }

        Log.d("Toggle Cart", "toggleCart: ${product.name} ${product.isAddedToCart}")
    }

    fun updateQuantity(product: DomainProduct, newQuantity: Int) =
        viewModelScope.launch {
            val updatedCartItems =
                _uiState.value.categoriesWithProducts.mapValues { (_, products) ->
                    products.map {
                        if (it.id == product.id) {
                            it.copy(
                                quantity = newQuantity,
                                price = newQuantity * it.price
                            )
                        } else {
                            it
                        }
                    }
                }

            _uiState.update {
                it.copy(categoriesWithProducts = updatedCartItems)
            }

            val updatedCartItemsList = getCartItems(updatedCartItems)
            _uiState.update {
                it.copy(cartItems = updatedCartItemsList)
            }
        }

    fun getTotalCartPrice(): Double =
        _uiState.value.cartItems.sumOf { it.price * it.quantity }

    private fun getCartItems(categories: Map<String, List<DomainProduct>>): List<DomainProduct> =
        categories.values.flatten().filter { it.isAddedToCart }
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