package com.mk.api.di.assessment.parsers

import com.mk.api.DataParser
import com.mk.api.ParsingException
import com.mk.api.di.assessment.response.AllAnimalsModel
import com.mk.api.di.assessment.response.AllAnimalsResponse
import com.mk.api.di.assessment.response.AnimalAttributesModel
import com.mk.api.di.assessment.response.AnimalBreedModel
import com.mk.api.di.assessment.response.AnimalColorModel
import com.mk.api.di.assessment.response.AnimalEnvironmentModel
import com.mk.api.di.assessment.response.AnimalsModel
import com.mk.api.di.assessment.response.AnimalsPaginationModel
import javax.inject.Inject

class GetAllAnimalsParser @Inject constructor() : DataParser<AllAnimalsResponse, AllAnimalsModel> {
    override fun parse(input: AllAnimalsResponse?): AllAnimalsModel =
        input?.let { animalsResponse ->

            val pagination = animalsResponse.pagination?.let {
                AnimalsPaginationModel(
                    countPerPage = it.countPerPage ?:
                        throw ParsingException("Error parsing Pagination countPerPage field"),
                    totalCount = it.totalCount ?:
                        throw ParsingException("Error parsing Pagination totalCount field"),
                    currentPage = it.currentPage ?:
                        throw ParsingException("Error parsing Pagination currentPage field"),
                    totalPages = it.totalPages ?:
                        throw ParsingException("Error parsing Pagination totalPages field"),
                )
            } ?: throw ParsingException("Error parsing Animals Pagination Payload")

            val animals = animalsResponse.animals.mapNotNull {
                try {
                    it?.let {
                        val animalBreed = it.breeds?.let { breed ->
                            AnimalBreedModel(
                                primary = breed.primary,
                                secondary = breed.secondary,
                                mixed = breed.mixed,
                                unknown = breed.unknown
                            )
                        } ?: throw ParsingException("Error parsing Animals breeds Payload")

                        val animalColors = it.colors?.let { color ->
                            AnimalColorModel(
                                primary = color.primary,
                                secondary = color.secondary,
                                tertiary = color.tertiary
                            )
                        } ?: throw ParsingException("Error parsing Animals colors Payload")

                        val attributesModel = it.attributes?.let { attributes ->
                            AnimalAttributesModel(
                                spayedNeutered = attributes.spayedNeutered ?: false,
                                houseTrained = attributes.houseTrained ?: false,
                                declawed = attributes.declawed ?: false,
                                specialNeeds = attributes.specialNeeds ?: false,
                                shotsUpToDate = attributes.shotsUpToDate ?: false,
                            )
                        } ?: throw ParsingException("Error parsing Animals attributes Payload")

                        val environmentModel = it.environment?.let { environment ->
                            AnimalEnvironmentModel(
                                children = environment.children ?: false,
                                dogs = environment.dogs ?: false,
                                cats = environment.cats ?: false,
                            )
                        } ?: throw ParsingException("Error parsing Animals environment Payload")

                        AnimalsModel(
                            id = it.id ?: throw ParsingException("Error parsing Animals id field"),
                            orgId = it.orgId
                                ?: throw ParsingException("Error parsing Animals orgId field"),
                            url = it.url
                                ?: throw ParsingException("Error parsing Animals url field"),
                            type = it.type
                                ?: throw ParsingException("Error parsing Animals type field"),
                            species = it.species
                                ?: throw ParsingException("Error parsing Animals species field"),
                            age = it.age
                                ?: throw ParsingException("Error parsing Animals age field"),
                            gender = it.gender
                                ?: throw ParsingException("Error parsing Animals gender field"),
                            size = it.size
                                ?: throw ParsingException("Error parsing Animals size field"),
                            coat = it.coat,
                            name = it.name
                                ?: throw ParsingException("Error parsing Animals name field"),
                            description = it.description,
                            status = it.status
                                ?: throw ParsingException("Error parsing Animals status field"),
                            breeds = animalBreed,
                            colors = animalColors,
                            attributes = attributesModel,
                            environment = environmentModel,
                        )
                    }
                } catch (e: ParsingException) {
                    return@mapNotNull null
                }
            }

            AllAnimalsModel(
                animals = animals,
                pagination = pagination
            )
        } ?: throw ParsingException("Error parsing Animals Payload")
}
