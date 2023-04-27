package com.crypto.moto.data

import com.apollographql.apollo3.ApolloClient
import com.crypto.moto.domain.ServiceItem
import com.crypto.moto.domain.ServicesClient
import com.moto.MobjectsQuery

class ApolloServicesClient (
    private val apolloClient: ApolloClient
): ServicesClient {
    override suspend fun getServices(): List<ServiceItem?> {
        return apolloClient
            .query(MobjectsQuery())
            .execute()
            .data
            ?.mobjects
            ?.map { it?.toService() }
            ?: emptyList()
    }
}