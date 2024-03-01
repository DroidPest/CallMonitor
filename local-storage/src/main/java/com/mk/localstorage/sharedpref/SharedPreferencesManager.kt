package com.mk.localstorage.sharedpref

import android.content.SharedPreferences
import javax.inject.Inject

interface SharedPreferencesManager {
    fun getString(key: String, defaultValue: String): String
    fun putString(key: String, value: String)
    fun getInt(key: String, defaultValue: Int): Int
    fun putInt(key: String, value: Int)
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun putBoolean(key: String, value: Boolean)
    fun remove(key: String)
    fun contains(key: String): Boolean
}

class AssessmentSharedPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : SharedPreferencesManager {

    private fun getEditor() = sharedPreferences.edit()

    override fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override fun putString(key: String, value: String) {
        getEditor().putString(key, value).apply()
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun putInt(key: String, value: Int) {
        getEditor().putInt(key, value).apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun putBoolean(key: String, value: Boolean) {
        getEditor().putBoolean(key, value).apply()
    }

    override fun remove(key: String) {
        getEditor().remove(key).apply()
    }

    override fun contains(key: String) = sharedPreferences.contains(key)
}
