package com.jarroyo.sharedcodeclient.domain.usecase

import com.jarroyo.sharedcodeclient.data.repository.AnimalRepository
import com.jarroyo.sharedcodeclient.domain.base.BaseUseCase
import com.jarroyo.sharedcodeclient.domain.base.Response
import com.jarroyo.sharedcodeclient.domain.model.Breed

class GetAnimalListUsecase(val repository: AnimalRepository) : BaseUseCase<Nothing, List<Breed>?>() {

    override suspend fun run(): Response<List<Breed>?> {
        return repository.getAnimalList()
    }
}