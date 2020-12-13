package com.jarroyo.sharedcodeclient.data.repository

import com.jarroyo.sharedcodeclient.data.source.NetworkDataSource
import com.jarroyo.sharedcodeclient.domain.base.Response
import com.jarroyo.sharedcodeclient.domain.model.BreedApi
import com.jarroyo.sharedcodeclient.domain.model.Breed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AnimalRepository(private val mNetworkDataSource: NetworkDataSource) {

    companion object {
        const val NUM_ITEMS_FIRST_LOAD = 6
    }

    /***********************************************************************************************
     * GET ANIMAL LIST
     **********************************************************************************************/
    suspend fun getAnimalList(): Response<List<Breed>?> {
        android.util.Log.d("Animalrepository", "getAnimalList")
        val response = mNetworkDataSource.getAnimalList()
        if (response is Response.Success) {
            return  Response.Success(response.data)
        }
        return Response.Error(java.lang.IllegalAccessError(""))
    }

    /***********************************************************************************************
     * GET BREED LIST FLOW
     **********************************************************************************************/
    suspend fun getAnimalListFlow() = flow<Response<List<Breed>?>> {
        android.util.Log.d("Animalrepository", "getBreedsListFlow")
        val response = mNetworkDataSource.getAnimalList()
        //emit(response)
        if (response is Response.Success) {
            emitAll(getAnimalImage(response.data))
        } else {
            emit(response)
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun getAnimalImage(list: List<Breed>?) = flow<Response<List<Breed>?>> {
        list?.let {
            for ((index, breed) in it.withIndex()) {
                val response = mNetworkDataSource.getAnimalImage(breed.name)
                if (response is Response.Success) {
                    android.util.Log.d("Animalrepository", "getBreedImage ${response.data}")
                    it[index].image = response.data ?: ""
                    if (index > NUM_ITEMS_FIRST_LOAD) {
                        emit(Response.Success(it))
                    }
                } else {
                    emit(Response.Success(it))
                }
            }
        }
    }.flowOn(Dispatchers.IO)

}

