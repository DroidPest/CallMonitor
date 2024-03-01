package com.mk.api.di.assessment.pagingSources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mk.api.ResponseState
import com.mk.api.di.assessment.repository.PetFinderRepository
import com.mk.api.di.assessment.response.AnimalsModel

class AllAnimalsPagingSource(
    private val recipesRepository: PetFinderRepository
) : PagingSource<Int, AnimalsModel>() {

    override fun getRefreshKey(state: PagingState<Int, AnimalsModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimalsModel> =
        try {
            val page = params.key ?: 1
            val data = recipesRepository.getAllAnimals(page = page, limit = 50)
            if (data is ResponseState.Success) {
                data.item.pagination
                if (params.placeholdersEnabled) {
                    LoadResult.Page(
                        data = data.item.animals,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if ( data.item.animals.isEmpty()) null else page + 1,
                    )
                } else {
                    LoadResult.Page(
                        data = data.item.animals,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if ( data.item.animals.isEmpty()) null else page + 1
                    )
                }
            } else {
                LoadResult.Error(Exception("Something went wrong loading data"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}