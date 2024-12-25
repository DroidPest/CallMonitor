package com.mk.infrastructure.phoneSession

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

interface PhoneCallSessionManager {
    val phoneCallState: StateFlow<PhoneCallSession>
    fun setPhoneCallSession(contactName: String, phoneNumber: String, outgoing: Boolean)
    fun setPhoneCallEnded()
}

/**
 * Implementation of the [PhoneCallSessionManager] interface.
 * Manages the state of an ongoing phone call session.
 *
 * This class uses a [MutableStateFlow] to hold the current [PhoneCallSession] and exposes
 * it as a read-only [StateFlow] for external observers.
 *
 * @constructor Creates a new instance of [PhoneCallSessionManagerImpl].
 */
class PhoneCallSessionManagerImpl @Inject constructor() : PhoneCallSessionManager {
    private val _phoneCallState: MutableStateFlow<PhoneCallSession> =
        MutableStateFlow(PhoneCallSession())
    override val phoneCallState: StateFlow<PhoneCallSession> = _phoneCallState.asStateFlow()

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
        _phoneCallState.update { it.copy(ongoing = false) }
    }
}
