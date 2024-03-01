package com.mk.api.di.assessment.repository

import com.mk.api.ResponseState
import com.mk.api.di.assessment.parsers.GetAllAnimalsParser
import com.mk.api.di.assessment.response.AllAnimalsModel
import com.mk.api.di.assessment.service.PetFinderService
import com.mk.api.validate
import com.mk.infrastructure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface PetFinderRepository {
    suspend fun getAllAnimals(page: Int, limit: Int): ResponseState<AllAnimalsModel>
}

class PetFinderEndpointsRepository @Inject constructor(
    private val service: PetFinderService,
    private val userDataParser: GetAllAnimalsParser,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : PetFinderRepository {
    override suspend fun getAllAnimals(page: Int, limit: Int) =
        withContext(ioDispatcher) {
            service.getAllAnimals(page, limit).validate { userDataParser.parse(it) }
        }
}
