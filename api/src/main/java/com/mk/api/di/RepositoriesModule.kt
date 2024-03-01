package com.mk.api.di

import com.mk.api.di.assessment.repository.PetFinderEndpointsRepository
import com.mk.api.di.assessment.repository.PetFinderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Singleton
    @Binds
    abstract fun providePetFinderRepo(
        petFinderEndpointsRepository: PetFinderEndpointsRepository
    ): PetFinderRepository
}
