package com.mk.infrastructure.phoneSession

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface PhoneCallSessionManager {
    val phoneCallState: StateFlow<PhoneCallSession?>
    fun setPhoneCallSession(contactName: String, phoneNumber: String, outgoing: Boolean)
    fun setPhoneCallEnded()
}

class PhoneCallSessionManagerImpl @Inject constructor() : PhoneCallSessionManager {
    private val _phoneCallState: MutableStateFlow<PhoneCallSession?> = MutableStateFlow(null)
    override val phoneCallState: StateFlow<PhoneCallSession?> = _phoneCallState.asStateFlow()

    override fun setPhoneCallSession(contactName: String, phoneNumber: String, outgoing: Boolean) {
        _phoneCallState.update {
            PhoneCallSession(
                ongoing = outgoing,
                phoneNumber = phoneNumber,
                contactName = contactName,
            )
        }
    }

    override fun setPhoneCallEnded() {
        _phoneCallState.update { null }
    }
}
