package com.crypto.moto.domain

class GeServicesUseCase(
    private val servicesClient: ServicesClient
) {
    suspend fun execute(): List<ServiceItem> {
        return servicesClient.getServices().mapNotNull { it }
    }
}