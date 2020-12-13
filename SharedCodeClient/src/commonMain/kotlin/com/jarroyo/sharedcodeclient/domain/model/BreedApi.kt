package com.jarroyo.sharedcodeclient.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BreedApi(
    var status: String? = null,
    @SerialName("message")
    var nameList: List<String>?
)

fun BreedApi.toModel(): List<Breed>? {
    var list = mutableListOf<Breed>()
    nameList?.let {
        for (name in it)
            list.add(Breed(name))
    }

    return list
}

@Serializable
data class BreedImageApi(
    var status: String? = null,
    @SerialName("message")
    var image: String? = null
)