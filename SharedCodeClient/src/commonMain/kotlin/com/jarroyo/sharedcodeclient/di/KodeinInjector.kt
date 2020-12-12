package com.jarroyo.sharedcodeclient.di

import com.jarroyo.sharedcodeclient.data.repository.AnimalRepository
import com.jarroyo.sharedcodeclient.data.source.AnimalApi
import com.jarroyo.sharedcodeclient.data.source.NetworkDataSource
import com.jarroyo.sharedcodeclient.data.source.NetworkDataSourceImpl
import com.jarroyo.sharedcodeclient.domain.usecase.GetAnimalListUsecase
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.provider
import org.kodein.di.erased.singleton
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
val KodeinInjector = Kodein {
    /**
     * USECASES
     */
    bind<GetAnimalListUsecase>() with singleton { GetAnimalListUsecase(instance()) }

    /**
     * REPOSITORIES
     */
    bind<AnimalRepository>() with provider { AnimalRepository(instance()) }

    /**
     * NETWORK DATA SOURCE
     */
    bind<NetworkDataSource>() with provider { NetworkDataSourceImpl(instance()) }

    /**
     * API
     */
    bind<AnimalApi>() with provider { AnimalApi() }
}