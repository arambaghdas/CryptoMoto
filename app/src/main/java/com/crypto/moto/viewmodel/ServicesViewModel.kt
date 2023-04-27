package com.crypto.moto.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.moto.domain.GeServicesUseCase
import com.crypto.moto.domain.ServiceItem
import com.crypto.moto.util.NetworkConnectionHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ServicesViewModel(
    private val networkConnectionHelper: NetworkConnectionHelper,
    private val getCountriesUseCase: GeServicesUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ServicesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(
                isLoading = true
            ) }
            _state.update { it.copy(
                services = getServices(),
                isLoading = false,
                isConnected = networkConnectionHelper.isNetworkConnected()
            ) }
        }
    }

    private suspend fun getServices(): List<ServiceItem> {
        if (!networkConnectionHelper.isNetworkConnected())
            return emptyList()

        return getCountriesUseCase.execute()
    }

    data class ServicesState(
        val services: List<ServiceItem> = emptyList(),
        val isLoading: Boolean = false,
        val isConnected: Boolean = true,
    )
}