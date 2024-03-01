package com.mk.assessment.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mk.api.di.assessment.pagingSources.AllAnimalsPagingSource
import com.mk.api.di.assessment.repository.PetFinderRepository
import com.mk.api.di.assessment.response.AnimalsModel
import com.mk.infrastructure.AppSessionManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

//sealed class HomeScreenUiState {
//    data object Loading : HomeScreenUiState()
//    data class Content(
//        val animals: AllAnimalsModel,
//    ) : HomeScreenUiState()
//    data object Error : HomeScreenUiState()
//}

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val sessionManager: AppSessionManager,
    private val petFinderRepo: PetFinderRepository,
) : ViewModel() {
//    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState.Loading)
//    val uiState: StateFlow<HomeScreenUiState> = _uiState

    val animals: Flow<PagingData<AnimalsModel>> = Pager(
        pagingSourceFactory = { AllAnimalsPagingSource(petFinderRepo) },
        config = PagingConfig(pageSize = 50, prefetchDistance = 5),
    ).flow.cachedIn(viewModelScope)

    fun isUserAuthenticated() = sessionManager.isUserAuthenticated()

//    fun getAllAnimals() {
//        viewModelScope.launch {
//            when (val response = petFinderRepo.getAllAnimals()) {
//                is ResponseState.Success -> {
//                    _uiState.emit(HomeScreenUiState.Content(response.item))
//                }
//
//                ResponseState.Loading -> {
//                    _uiState.emit(HomeScreenUiState.Loading)
//                }
//
//                is ResponseState.Error -> {
//                    Unit
//                } // Todo yet to be defined
//            }
//        }
//    }
}
