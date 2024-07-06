package dev.borisochieng.timbushop.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.borisochieng.timbushop.data.Resource
import dev.borisochieng.timbushop.data.repository.TimbuAPIRepository
import dev.borisochieng.timbushop.util.Constants.API_KEY
import dev.borisochieng.timbushop.util.Constants.APP_ID
import dev.borisochieng.timbushop.util.Constants.ORGANIZATION_ID
import dev.borisochieng.timbushop.util.UIEvents
import dev.borisochieng.timbushop.util.UIState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

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
                is Resource.Success -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            products = productsResponse.payLoad?.items ?: emptyList(),
                        )

                    }
                }

                is Resource.Error -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = productsResponse.message
                                ?: "Something went wrong kindly try again"
                        )
                    }

                    _eventFlow.emit(
                        UIEvents.SnackBarEvent(
                            message = productsResponse.message
                                ?: "Something went wrong kindly try again"
                        )
                    )
                }
            }


        }
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