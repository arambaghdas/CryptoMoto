package com.crypto.moto.domain

interface ServicesClient {
    suspend fun getServices(): List<ServiceItem?>
}