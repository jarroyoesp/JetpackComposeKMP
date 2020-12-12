package com.jarroyo.sharedcodeclient.data.repository

import com.jarroyo.sharedcodeclient.data.source.NetworkDataSource
import com.jarroyo.sharedcodeclient.domain.base.Response

class AnimalRepository(private val mNetworkDataSource: NetworkDataSource) {

    /***********************************************************************************************
     * LOGIN
     **********************************************************************************************/
    suspend fun getAnimalList(): Response<Int> {
        return mNetworkDataSource.getAnimalList()
    }
}

