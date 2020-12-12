package com.jarroyo.sharedcodeclient.domain.usecase

import com.jarroyo.sharedcodeclient.data.repository.AnimalRepository
import com.jarroyo.sharedcodeclient.domain.base.BaseUseCase
import com.jarroyo.sharedcodeclient.domain.base.Response

class GetAnimalListUsecase(val repository: AnimalRepository) : BaseUseCase<Nothing, Int>() {

    override suspend fun run(): Response<Int> {
        return repository.getAnimalList()
    }
}