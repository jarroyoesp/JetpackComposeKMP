package com.jarroyo.sharedcodeclient.data.source

import com.jarroyo.sharedcodeclient.domain.base.Response
import com.jarroyo.sharedcodeclient.domain.model.Breed
import com.jarroyo.sharedcodeclient.domain.model.toModel

class NetworkDataSourceImpl(private val api: AnimalApi): NetworkDataSource() {
    override suspend fun getAnimalList(): Response<List<Breed>?> {
        return api.getAnimalList()
    }

    override suspend fun getAnimalImage(name: String): Response<String?> {
        return api.getAnimalImage(name)
    }
}