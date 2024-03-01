package com.mk.assessment

import android.app.Application
import com.mk.infrastructure.logging.AssessmentDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
open class AssessmentApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(AssessmentDebugTree())
        }
    }
}
