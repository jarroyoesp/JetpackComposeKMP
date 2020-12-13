package com.jarroyo.sharedcodeclient.domain.usecase

import com.jarroyo.sharedcodeclient.data.repository.AnimalRepository
import com.jarroyo.sharedcodeclient.domain.base.BaseUseCaseFlow
import com.jarroyo.sharedcodeclient.domain.base.Response
import com.jarroyo.sharedcodeclient.domain.model.Breed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.InternalCoroutinesApi


open class GetAnimalListUsecaseFlow(val repository: AnimalRepository) : BaseUseCaseFlow<Nothing, List<Breed>?>() {
    @InternalCoroutinesApi
    override suspend fun run(): Flow<Response<List<Breed>?>> {
        return repository.getAnimalListFlow()
    }
}