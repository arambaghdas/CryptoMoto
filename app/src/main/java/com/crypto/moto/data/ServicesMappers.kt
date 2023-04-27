package com.crypto.moto.data

import com.crypto.moto.domain.*
import com.moto.MobjectsQuery

fun MobjectsQuery.Mobject.toService(): ServiceItem {
    return ServiceItem(
        id = id,
        type = getTypeString(type),
        author = Author(
            id = author.id,
            firstName = author.first_name,
            lastName = author.last_name,
            avatar = Avatar(
                id = author.avatar.id,
                downloadUrl = author.avatar.downloadUrl
            )
        ),
        title = title,
        listing = listing,
        photo = Photo(
            id = photo.id,
            downloadUrl = photo.downloadUrl
        ),
        description = description,
        country = Country(
            id = country.id,
            nicename = country.nicename
        ),
        city = city,
        address = address,
        lat = lat,
        lng = lng,
        phone = phone,
        publicEmail = publicEmail,
        website = website,
        workingHours = workingHours,
        rating = rating,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun getTypeString(int: Int): String {
    return when (int) {
        1 -> "Repair shop"
        2 -> "Moto mechanic"
        else -> "Repair shop"
    }
}