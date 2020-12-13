package com.jarroyo.sharedcodeclient.data.repository

import com.jarroyo.sharedcodeclient.data.source.NetworkDataSource
import com.jarroyo.sharedcodeclient.domain.base.Response
import com.jarroyo.sharedcodeclient.domain.model.BreedApi
import com.jarroyo.sharedcodeclient.domain.model.Breed

class AnimalRepository(private val mNetworkDataSource: NetworkDataSource) {

    /***********************************************************************************************
     * LOGIN
     **********************************************************************************************/
    suspend fun getAnimalList(): Response<List<Breed>?> {
        val response = mNetworkDataSource.getAnimalList()
        if (response is Response.Success) {
            return  Response.Success(response.data)
        }
        return Response.Error(java.lang.IllegalAccessError(""))
    }
}

