package com.magic.upcoming.games.activity.video

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.webkit.WebChromeClient.CustomViewCallback
import com.magic.upcoming.games.GameApplication
import com.magic.upcoming.games.R
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

    private var playFlag = false
    private var position = 0
    private val handler = Handler()

    override val layoutId: Int
        get() = R.layout.activity_video_detail

    override fun createViewModel(): VideoDetailViewModel {
        return VideoDetailViewModel()
    }

    override fun initView() {
        videoModel = GameApplication.instance?.videoModel!!
        initWebView()
//        Handler().postDelayed({
//            binding?.webviewVideoPlayer?.loadUrl(videoModel.embedPlayer!!)
//        }, 2000)
        binding?.webviewVideoPlayer?.loadUrl(videoModel.embedPlayer!!)
    }

    override fun setListener() {

    }

    private fun initWebView() {

        heads["X-requested-with"] = ""

        val settings: WebSettings = binding?.webviewVideoPlayer?.settings!!
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
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

    //    override fun initView() {
//
//        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//
//        videoModel = GameApplication.instance?.videoModel!!
//        binding?.videoPlayer?.coverImageView?.setImageResource(R.drawable.ic_cover)
//        binding?.videoPlayer?.layoutParams = LinearLayout.LayoutParams(-1, resources.displayMetrics.widthPixels * 9 / 16)
//        //进入全屏的模式 0横屏 1竖屏 2传感器自动横竖屏 3根据视频比例自动确定横竖屏      -1什么都不做
//        binding?.videoPlayer?.enterFullMode = 3
//        //是否非全屏下也可以手势调节进度
//        binding?.videoPlayer?.isWindowGesture = true
//
//        play(AndroidMedia::class.java)
//    }
//
//    override fun setListener() {
//        binding?.videoPlayer?.setPlayListener(object : PlayListener {
//            var mode = 0
//            override fun onMode(mode: Int) {
//                this.mode = mode
//            }
//
//            override fun onEvent(what: Int, vararg extra: Int?) {
//                if (what == DemoQSVideoView.EVENT_CONTROL_VIEW && mode == IVideoPlayer.MODE_WINDOW_NORMAL) {
//                    if (extra[0] == 0) //状态栏隐藏/显示
//                        Util.CLEAR_FULL(this@VideoDetailActivity) else Util.SET_FULL(this@VideoDetailActivity)
//                }
//                //系统浮窗点击退出退出activity
//                if (what == DemoQSVideoView.EVENT_CLICK_VIEW
//                        && extra[0] == R.id.help_float_close) if (binding?.videoPlayer?.isSystemFloatMode!!) finish()
//            }
//
//            override fun onStatus(status: Int) {
//                if (status == IVideoPlayer.STATE_AUTO_COMPLETE) binding?.videoPlayer?.quitWindowFullscreen()
//            }
//        })
//    }
//
//    private fun play(decodeMedia: Class<out BaseMedia?>) {
//        binding?.videoPlayer?.release()
//        binding?.videoPlayer?.setDecodeMedia(decodeMedia)
//
//        val qsVideos = ArrayList<QSVideo>()
//        if (!videoModel.lowUrl.isNullOrEmpty())
//            qsVideos.add(QSVideo.Build(videoModel.lowUrl + "?api_key=" + GameConts.GIANT_BOMB_API_KEY).title(videoModel.videoName).definition("Low").resolution("Low").build())
//        if (!videoModel.highUrl.isNullOrEmpty())
//            qsVideos.add(QSVideo.Build(videoModel.highUrl + "?api_key=" + GameConts.GIANT_BOMB_API_KEY).title(videoModel.videoName).definition("High").resolution("High").build())
//        if (!videoModel.hdUrl.isNullOrEmpty())
//            qsVideos.add(QSVideo.Build(videoModel.hdUrl + "?api_key=" + GameConts.GIANT_BOMB_API_KEY).title(videoModel.videoName).definition("HD").resolution("HD").build())
//
//        if (qsVideos.size > 0)
//            binding?.videoPlayer?.setUp(qsVideos)
//
//        binding?.videoPlayer?.openCache()
//
//        binding?.videoPlayer?.play()
//    }
//
//    private val runnable = Runnable {
//        if (binding?.videoPlayer?.currentState != IVideoPlayer.STATE_AUTO_COMPLETE) position = binding?.videoPlayer?.position!!
//        binding?.videoPlayer?.release()
//    }
//
//    override fun onBackPressed() {
//        if (binding?.videoPlayer?.onBackPressed()!!) {
//            return
//        }
//        super.onBackPressed()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (playFlag) binding?.videoPlayer?.play()
//        handler.removeCallbacks(runnable)
//        if (position > 0) {
//            binding?.videoPlayer?.seekTo(position)
//            position = 0
//        }
//    }
//
//    override fun onPause() {
//        super.onPause()
//        if (binding?.videoPlayer?.isSystemFloatMode!!) return
//        //暂停
//        playFlag = binding?.videoPlayer?.isPlaying!!
//        binding?.videoPlayer?.pause()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        if (binding?.videoPlayer?.isSystemFloatMode!!) return
//        //进入后台不马上销毁,延时15秒
//        handler.postDelayed(runnable, 1000 * 15.toLong())
//    }
//
//    override fun onDestroy() {
//        super.onDestroy() //销毁
//        if (binding?.videoPlayer?.isSystemFloatMode!!) binding?.videoPlayer?.quitWindowFloat()
//        binding?.videoPlayer?.release()
//        handler.removeCallbacks(runnable)
//    }
//

}