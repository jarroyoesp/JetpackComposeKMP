package com.jarroyo.sharedcodeclient.data.source

import com.jarroyo.sharedcodeclient.domain.base.Response
import com.jarroyo.sharedcodeclient.domain.model.Breed

abstract class NetworkDataSource {
    abstract suspend fun getAnimalList(): Response<List<Breed>?>
    abstract suspend fun getAnimalImage(name: String): Response<String?>
}