package com.mk.assessment.screens.home

import androidx.lifecycle.ViewModel
import callLogs.ContactLoggerManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val contactLoggerManager: ContactLoggerManager,
) : ViewModel() {
    fun getCallLogFlow() = contactLoggerManager.savedCallLogs
}
