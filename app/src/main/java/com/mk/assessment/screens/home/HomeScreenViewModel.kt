package com.mk.assessment.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import callLogs.ContactLoggerManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val contactLoggerManager: ContactLoggerManager,
) : ViewModel() {
    private val _errorMessages: MutableSharedFlow<String?> =
        MutableSharedFlow<String?>(replay = 0, extraBufferCapacity = 1)
    val errorMessages = _errorMessages.asSharedFlow()

    fun showErrorMessage(message: String?) =
        viewModelScope.launch {
            _errorMessages.emit(message)
        }

    fun getCallLogFlow() = contactLoggerManager.savedLaunchCallLogs
}
