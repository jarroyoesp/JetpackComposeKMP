package com.jarroyo.sharedcodeclient.data.source

import com.jarroyo.sharedcodeclient.domain.base.Response
import com.jarroyo.sharedcodeclient.domain.model.BreedApi
import com.jarroyo.sharedcodeclient.domain.model.Breed
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import com.jarroyo.sharedcodeclient.domain.model.toModel

class AnimalApi {

    private val httpClient = HttpClient()

    val endpoint = "https://dog.ceo/api/"
    companion object{
        const val URL_PATH_BREEDS_LIST = "breeds/list"
        const val URL_PATH_BREED_IMAGE = "breed/{name}/images/random"
    }
    /**
     * GET ANIMAL LIST
     */
    suspend fun getAnimalList(): Response<List<Breed>?> {
        try {
            val url = endpoint + URL_PATH_BREEDS_LIST
            val json = httpClient.get<String>(url) {
            }
            val breedApi = Json.decodeFromString<BreedApi>(json)
            return Response.Success(breedApi.toModel())
        } catch (ex: Exception) {
            return Response.Error(ex)
        }
    }
}