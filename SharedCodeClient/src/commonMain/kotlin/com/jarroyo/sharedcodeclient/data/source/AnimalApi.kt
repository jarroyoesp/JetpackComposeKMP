package com.jarroyo.sharedcodeclient.data.source

import com.jarroyo.sharedcodeclient.domain.base.Response
import io.ktor.client.*
import io.ktor.client.request.*

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
    suspend fun getAnimalList(): Response<Int> {
        try {
            val url = endpoint + URL_PATH_BREEDS_LIST
            val json = httpClient.get<String>(url) {
            }
            return Response.Success(9875)
        } catch (ex: Exception) {
            return Response.Error(ex)
        }
    }
}