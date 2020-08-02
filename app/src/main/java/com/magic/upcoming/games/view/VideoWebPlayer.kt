package com.magic.upcoming.games.view

import android.content.Context
import android.content.pm.ActivityInfo
import android.util.AttributeSet
import android.view.View
import android.webkit.*
import com.magic.upcoming.games.GameApplication
import com.magic.upcoming.games.base.BaseApplication
import com.magic.upcoming.games.utils.WebUtils
import java.util.ArrayList
import java.util.HashMap

class VideoWebPlayer @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    private val heads = HashMap<String, String>()

    init {
        initWebView()
        loadUrl(GameApplication.instance?.videoModel?.embedPlayer!!)
    }

    private fun initWebView() {
        heads["X-requested-with"] = ""
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = true;
        settings.pluginState = WebSettings.PluginState.ON
        settings.allowFileAccess = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        var ua: String = settings.userAgentString
        val uaList: ArrayList<String> = WebUtils.getAllSatisfyStr(ua, "\\sBuild\\/[^\\s]+;[\\s]+wv")
        if (uaList.size > 0) {
            ua = ua.replace(uaList[0], "")
        }

        val uaLists: ArrayList<String> = WebUtils.getAllSatisfyStr(ua, "\\sVersion\\/[^\\s]+")
        if (uaLists.size > 0) {
            ua = ua.replace(uaLists[0], "")
        }
        settings.userAgentString = ua
    }

}