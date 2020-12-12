package com.jarroyo.sharedcodeclient.data.source

import com.jarroyo.sharedcodeclient.domain.base.Response

class NetworkDataSourceImpl(private val api: AnimalApi): NetworkDataSource() {
    override suspend fun getAnimalList(): Response<Int> {
        return api.getAnimalList()
    }
}