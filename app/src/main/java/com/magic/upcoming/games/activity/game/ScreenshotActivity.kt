package com.magic.upcoming.games.activity.game

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import androidx.core.app.ActivityOptionsCompat
import androidx.core.app.SharedElementCallback
import androidx.core.view.ViewCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.github.chrisbanes.photoview.PhotoView
import com.magic.upcoming.games.GameApplication
import com.magic.upcoming.games.R
import com.magic.upcoming.games.model.game.GameImage
import com.magic.upcoming.games.view.ScreenshotViewPager
import pers.zy.image_trans_lib.ImageDetailFrameLayout

open class ScreenshotActivity : AppCompatActivity() {

    companion object {
        const val ARG_IMG_RES_IDS = "arg_img_res_ids"
        const val ARG_POSITION = "arg_position"
        const val EXTRA_SHARED_TRANS_NAME = "extra_shared_trans_name"

        fun start(context: Activity, view: View, position: Int) {
            context.startActivity(
                    Intent(context, ScreenshotActivity::class.java).apply {
                        putExtra(ARG_POSITION, position)
                    }, ActivityOptionsCompat.makeSceneTransitionAnimation(
                    context, view,
                    ViewCompat.getTransitionName(view) ?: return
            ).toBundle()
            )
        }
    }

    private lateinit var adapter: DetailImageAdapter
    protected var imageListArray: ArrayList<GameImage>? = null
    private var position = 0
    private var vpImageDetail: ScreenshotViewPager? = null
    private var flImageDetail: ImageDetailFrameLayout? = null
    private var bgMask: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportPostponeEnterTransition()
        setContentView(R.layout.activity_screenshot)
        imageListArray = GameApplication.instance?.gameImages
        position = intent.getIntExtra(ARG_POSITION, 0)
        vpImageDetail = findViewById(R.id.vp_image_detail)
        flImageDetail = findViewById(R.id.fl_image_detail)
        bgMask = findViewById(R.id.bg_mask)
        initViewPager()

        findViewById<View>(R.id.back).setOnClickListener {
            checkFinish()
        }
    }

    private fun initViewPager() {
        adapter = DetailImageAdapter()
        vpImageDetail?.adapter = adapter
        vpImageDetail?.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                this@ScreenshotActivity.position = position
            }
        })
        vpImageDetail?.currentItem = position
        flImageDetail?.setOnMoveExitListener(object : ImageDetailFrameLayout.OnMoveExitListener {
            override fun onMove(fraction: Float) {
                bgMask?.alpha = fraction
            }

            override fun restore() {
                bgMask?.alpha = 1f
            }

            override fun onExit() {
                checkFinish()
            }
        })
        flImageDetail?.maxMoveExitLength = 400
        flImageDetail?.minScale = 0.5f
    }

    override fun onBackPressed() {
        checkFinish()
    }

    private fun checkFinish() {
        setEnterSharedElementCallback(object : SharedElementCallback() {
            override fun onMapSharedElements(
                    names: MutableList<String>?,
                    sharedElements: MutableMap<String, View>?
            ) {
                val mCurrentView = adapter.mCurrentView ?: return
                val transitionName = ViewCompat.getTransitionName(mCurrentView) ?: return
                sharedElements?.let {
                    it.clear()
                    it[transitionName] = mCurrentView
                }
                names?.let {
                    it.clear()
                    it.add(transitionName)
                }
            }
        })

        setResult(Activity.RESULT_OK, Intent().apply {
            putExtra("position", position)
        })
        supportFinishAfterTransition()
    }

    inner class DetailImageAdapter : PagerAdapter() {
        var mCurrentView: View? = null

        override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

        override fun getCount(): Int = this@ScreenshotActivity.imageListArray!!.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val imageView = PhotoView(this@ScreenshotActivity).apply {
                ViewCompat.setTransitionName(this, "$EXTRA_SHARED_TRANS_NAME$position")
                if (position == this@ScreenshotActivity.position) {
                    viewTreeObserver.addOnGlobalLayoutListener(object :
                            ViewTreeObserver.OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            viewTreeObserver.removeOnGlobalLayoutListener(this)
                            this@ScreenshotActivity.supportStartPostponedEnterTransition()
                        }
                    })
                }
                setOnClickListener {
                    checkFinish()
                }
            }
            Glide.with(this@ScreenshotActivity)

                    .load(imageListArray?.get(position)?.thumbUrl).diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(object : CustomTarget<Drawable>() {
                        override fun onLoadCleared(placeholder: Drawable?) {}

                        override fun onResourceReady(
                                resource: Drawable,
                                transition: Transition<in Drawable>?
                        ) {
                            imageView.setImageDrawable(resource)
                        }
                    })
            container.addView(imageView)
            return imageView
        }

        override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
            mCurrentView = `object` as View
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View?)
        }
    }
}