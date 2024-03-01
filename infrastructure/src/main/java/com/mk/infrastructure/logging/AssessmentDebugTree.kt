package com.mk.infrastructure.logging

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import timber.log.Timber

class AssessmentDebugTree : Timber.DebugTree() {
    private companion object {
        private const val METHOD_OFFSET = 4
    }

    init {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .methodOffset(METHOD_OFFSET)
            .tag("$")
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        Logger.log(priority, tag, message, t)
    }
}
