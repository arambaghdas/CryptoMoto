package com.crypto.moto.di

import com.apollographql.apollo3.ApolloClient
import com.crypto.moto.data.ApolloServicesClient
import com.crypto.moto.domain.GeServicesUseCase
import com.crypto.moto.domain.ServicesClient
import com.crypto.moto.util.NetworkConnectionHelper
import com.crypto.moto.viewmodel.ServicesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<ServicesClient> {
        ApolloServicesClient(
            apolloClient = ApolloClient.Builder()
                .serverUrl("https://motostrangers.com/backend")
                .build()
        )
    }

    single {
        GeServicesUseCase(servicesClient = get())
    }

    single {
        NetworkConnectionHelper(androidApplication())
    }

    viewModel {
        ServicesViewModel(
            networkConnectionHelper = get(),
            getCountriesUseCase = get()
        )
    }
}