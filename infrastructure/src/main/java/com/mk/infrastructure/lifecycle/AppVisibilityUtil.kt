package com.mk.infrastructure.lifecycle

import android.app.ActivityManager

object AppVisibilityUtil {
    private var isAppInForeground = setAppVisibilityState()

    fun isAppInBackground() = !isAppInForeground

    fun setAppVisibilityState(): Boolean {
        val appProcessInfo = ActivityManager.RunningAppProcessInfo()
        ActivityManager.getMyMemoryState(appProcessInfo)
        isAppInForeground = (
            appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND ||
                appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE
            )
        return isAppInForeground
    }
}
