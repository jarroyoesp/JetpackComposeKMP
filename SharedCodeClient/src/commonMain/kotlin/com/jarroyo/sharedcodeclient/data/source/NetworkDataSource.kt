package com.jarroyo.sharedcodeclient.data.source

import com.jarroyo.sharedcodeclient.domain.base.Response

abstract class NetworkDataSource {
    abstract suspend fun getAnimalList(): Response<Int>
}