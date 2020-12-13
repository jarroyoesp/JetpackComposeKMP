package com.jarroyo.sharedcodeclient.domain.model

data class Breed(
    var name:String,
    var image: String? = null
)
//fun Breed.toEntity() = BreedEntity(name, image)

/*
fun List<Breed>.toEntity() =
    this.map {
        it.toEntity()
    }*/
