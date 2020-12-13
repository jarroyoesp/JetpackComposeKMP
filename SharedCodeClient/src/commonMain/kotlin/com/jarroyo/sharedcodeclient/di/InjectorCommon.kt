package com.jarroyo.sharedcodeclient.di

import com.jarroyo.sharedcodeclient.data.repository.AnimalRepository
import com.jarroyo.sharedcodeclient.data.source.AnimalApi
import com.jarroyo.sharedcodeclient.data.source.NetworkDataSource
import com.jarroyo.sharedcodeclient.data.source.NetworkDataSourceImpl
import com.jarroyo.sharedcodeclient.domain.usecase.GetAnimalListUsecase
import com.jarroyo.sharedcodeclient.domain.usecase.GetAnimalListUsecaseFlow

object InjectorCommon {

    private val animalApi: AnimalApi by lazy {
        AnimalApi()
    }

    private val networkDataSource: NetworkDataSource by lazy {
        NetworkDataSourceImpl(animalApi)
    }

    private val animalRepository: AnimalRepository by lazy {
        AnimalRepository(networkDataSource)
    }

    fun provideGetAnimalListUsecase(): GetAnimalListUsecase {
        return GetAnimalListUsecase(animalRepository)
    }

    fun provideGetAnimalListUsecaseFlow(): GetAnimalListUsecaseFlow {
        return GetAnimalListUsecaseFlow(animalRepository)
    }
}