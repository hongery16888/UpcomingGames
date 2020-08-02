package com.magic.upcoming.games.base

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.webkit.WebView
import com.facebook.ads.AudienceNetworkAds
import com.google.android.gms.ads.MobileAds
import com.magic.upcoming.games.model.game.GameImage

open class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val processName = getProcessName(this)
            if (getAppName(applicationContext) != processName) {//判断不等于默认进程名称
                WebView.setDataDirectorySuffix(processName!!)
            }
        }

        if (AudienceNetworkAds.isInAdsProcess(this)) {
            return
        }

        AudienceNetworkAds.initialize(this)
        MobileAds.initialize(this);
    }

    private fun getProcessName(context: Context?): String? {
        if (context == null) return null
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (processInfo in manager.runningAppProcesses) {
            if (processInfo.pid == android.os.Process.myPid()) {
                return processInfo.processName
            }
        }
        return null
    }

    companion object {

        var instance: BaseApplication? = null
            private set
    }

    private fun getAppName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            return context.resources.getString(labelRes) + " " + getVersionName(context)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    private fun getVersionName(context: Context): String? {
        try {
            val packageManager = context.packageManager
            val packageInfo = packageManager.getPackageInfo(
                    context.packageName, 0)
            return packageInfo.versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

}
