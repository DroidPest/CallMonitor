package com.mk.api.di.assessment.service

import com.mk.api.di.assessment.response.AllAnimalsResponse
import com.mk.networking.NetRequestStatus
import retrofit2.http.GET
import retrofit2.http.Query

interface PetFinderService {
    @GET("animals")
    suspend fun getAllAnimals(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 50,
    ): NetRequestStatus<AllAnimalsResponse>
}
