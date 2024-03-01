package com.mk.assessment.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.mk.api.di.assessment.response.AnimalsModel
import com.mk.component.TextComponent
import com.mk.component.UiLibrary

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
//    val uiState by viewModel.uiState.collectAsState()

//    LaunchedEffect(key1 = viewModel) {
//        viewModel.getAllAnimals()
//    }

    UiLibrary.TextComponent.h3(text = "User authentication status is = " + viewModel.isUserAuthenticated())
    ShowAnimalList(viewModel = viewModel)
//    when (uiState) {
//        is HomeScreenUiState.Content -> {
//            ShowAnimalList((uiState as HomeScreenUiState.Content).animals)
//        }
//        is HomeScreenUiState.Error -> Unit
//        is HomeScreenUiState.Loading -> {
//            UiLibrary.LoadingComponent.LoadingScreen()
//        }
//    }
}

@Composable
fun ShowAnimalList(viewModel: HomeScreenViewModel) {
    val animals: LazyPagingItems<AnimalsModel> = viewModel.animals.collectAsLazyPagingItems()

    // Unstable API, no way to have unique identifier, same result may show more than once
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(
            count = animals.itemCount
        ) { index: Int ->
            val article: AnimalsModel = animals[index] ?: return@items
            UiLibrary.TextComponent.h1(text = article.name)
        }
    }
}
