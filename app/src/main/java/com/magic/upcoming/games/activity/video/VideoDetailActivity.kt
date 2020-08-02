package com.magic.upcoming.games.activity.video

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.webkit.WebChromeClient.CustomViewCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magic.upcoming.games.GameApplication
import com.magic.upcoming.games.R
import com.magic.upcoming.games.adapter.ScreenshotListAdapter
import com.magic.upcoming.games.base.BaseActivity
import com.magic.upcoming.games.databinding.ActivityVideoDetailBinding
import com.magic.upcoming.games.model.video.VideoModel
import com.magic.upcoming.games.utils.WebUtils
import com.magic.upcoming.games.viewmodel.video.VideoDetailViewModel
import java.util.*


class VideoDetailActivity : BaseActivity<ActivityVideoDetailBinding, VideoDetailViewModel>() {

    private lateinit var videoModel: VideoModel
    private var mCustomView: View? = null
    private var mCustomViewCallback: CustomViewCallback? = null
    private val heads = HashMap<String, String>()
    lateinit var adapter: ScreenshotListAdapter

    override val layoutId: Int
        get() = R.layout.activity_video_detail

    override fun createViewModel(): VideoDetailViewModel {
        return VideoDetailViewModel()
    }

    override fun initView() {
        videoModel = GameApplication.instance?.videoModel!!
        binding?.video = videoModel
        initWebView()
        binding?.webviewVideoPlayer?.loadUrl(videoModel.embedPlayer!!)

        binding?.screenshotRecyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        adapter = ScreenshotListAdapter(this)
        binding?.screenshotRecyclerView?.adapter = adapter
    }

    override fun setListener() {

    }

    private fun initWebView() {

        heads["X-requested-with"] = ""

        val settings: WebSettings = binding?.webviewVideoPlayer?.settings!!
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = true;
        settings.pluginState = WebSettings.PluginState.ON
        //settings.setPluginsEnabled(true);
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
        //javascriptInterface = new JavascriptInterface();
        //mWebView.addJavascriptInterface(javascriptInterface, "java2js_laole918");
        binding?.webviewVideoPlayer?.webChromeClient = object : WebChromeClient(){
            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)
                println("------------------>ShowCustomView : $view")
                if (mCustomView != null) {
                    callback?.onCustomViewHidden()
                    return
                }

                mCustomView = view
                binding?.mFrameLayout?.addView(mCustomView)
                mCustomViewCallback = callback
                binding?.webviewVideoPlayer?.visibility = View.GONE
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }

            override fun onHideCustomView() {
                binding?.webviewVideoPlayer?.visibility = View.VISIBLE
                println("------------------>HideCustomView : $mCustomView")
                if (mCustomView == null) {
                    return
                }
                mCustomView?.visibility = View.GONE
                binding?.mFrameLayout?.removeView(mCustomView)
                mCustomViewCallback?.onCustomViewHidden()
                mCustomView = null;
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                super.onHideCustomView();
            }

            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                println("-----------> mes ： " + consoleMessage.message())
                return super.onConsoleMessage(consoleMessage)
            }
        }
        binding?.webviewVideoPlayer?.webViewClient = object : WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url!!)
                return true
            }
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            binding?.scrollView?.visibility = View.GONE

        }
        else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN)
            binding?.scrollView?.visibility = View.VISIBLE
        }
    }

    override fun onPause() {
        super.onPause()
        binding?.webviewVideoPlayer?.onPause()
    }

    override fun onResume() {
        super.onResume()
        binding?.webviewVideoPlayer?.onResume()
    }

    override fun onBackPressed() {
        if (binding?.webviewVideoPlayer?.canGoBack()!!) {
            binding?.webviewVideoPlayer?.goBack()
            return
        }
        super.onBackPressed()
    }

    override fun onDestroy() {
        binding?.webviewVideoPlayer?.destroy()
        super.onDestroy()
    }

}