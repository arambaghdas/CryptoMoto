package com.crypto.moto.domain

import java.io.Serializable

data class Author(
    val id: String,
    val firstName: String,
    val lastName: String?,
    val avatar: Avatar,
): Serializable {
    companion object {
        private const val serialVersionUID: Long = 1L
    }
}

data class Avatar(
    val id: String,
    val downloadUrl: String,
): Serializable {
    companion object {
        private const val serialVersionUID: Long = 2L
    }
}

data class Photo(
    val id: String,
    val downloadUrl: String,
): Serializable {
    companion object {
        private const val serialVersionUID: Long = 3L
    }
}

data class Country(
    val id: String,
    val nicename: String,
): Serializable {
    companion object {
        private const val serialVersionUID: Long = 4L
    }
}

data class ServiceItem(
    val id: String,
    val type: String,
    val author: Author,
    val title: String,
    val listing: Int,
    val photo: Photo,
    val description: String,
    val country: Country,
    val city: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val phone: String?,
    val publicEmail: String?,
    val website: String?,
    val workingHours: String?,
    val rating: Double?,
    val createdAt: Any,
    val updatedAt: Any,
): Serializable {
    companion object {
        private const val serialVersionUID: Long = 5L
    }
}
