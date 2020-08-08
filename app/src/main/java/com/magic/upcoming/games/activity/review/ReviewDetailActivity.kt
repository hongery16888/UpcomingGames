package com.magic.upcoming.games.activity.review

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.magic.upcoming.games.GameApplication
import com.magic.upcoming.games.R
import com.magic.upcoming.games.activity.game.ScreenshotActivity
import com.magic.upcoming.games.base.BaseActivity
import com.magic.upcoming.games.databinding.ActivityReviewDetailBinding
import com.magic.upcoming.games.model.game.GameImage
import com.magic.upcoming.games.model.review.ReviewModel
import com.magic.upcoming.games.viewmodel.review.ReviewDetailViewModel
import me.wcy.htmltext.HtmlImageLoader
import me.wcy.htmltext.HtmlText
import me.wcy.htmltext.OnTagClickListener


class ReviewDetailActivity : BaseActivity<ActivityReviewDetailBinding, ReviewDetailViewModel>(){

    private lateinit var reviewModel: ReviewModel

    override val layoutId: Int
        get() = R.layout.activity_review_detail

    override fun createViewModel(): ReviewDetailViewModel {
        return ReviewDetailViewModel()
    }

    override fun initView() {
        reviewModel = GameApplication.instance?.reviewModel!!

        binding?.viewModel = viewModel
        binding?.review = reviewModel

        binding?.ratingStar?.rating = reviewModel.score

        val flexboxLayout = binding?.platformFlexboxLayout

        //Since ViewHolders are reused, we need to remove the previously added Views first.
        flexboxLayout?.removeAllViews()

        reviewModel.platforms?.split(",")?.forEach {
            val textView = LayoutInflater.from(binding?.root?.context).inflate(
                    R.layout.platform_chip_layout,
                    flexboxLayout, false
            ) as TextView

            textView.text = it
            flexboxLayout?.addView(textView)
        }

        initRichText()
    }

    private fun initRichText(){
        binding?.richtext?.movementMethod = LinkMovementMethod.getInstance()
        HtmlText.from(reviewModel.description)
                .setImageLoader(object : HtmlImageLoader{

                    private fun getTextWidth(): Int {
                        val dm = resources.displayMetrics
                        return dm.widthPixels - binding?.richtext?.paddingLeft!! - binding?.richtext?.paddingRight!!
                    }

                    override fun getErrorDrawable(): Drawable {
                        return ContextCompat.getDrawable(this@ReviewDetailActivity, R.drawable.broken_image)!!
                    }

                    override fun getMaxWidth(): Int {
                        return getTextWidth()
                    }

                    override fun loadImage(url: String?, callback: HtmlImageLoader.Callback?) {
                        Glide.with(this@ReviewDetailActivity)
                                .asBitmap()
                                .load(url)
                                .fitCenter()
                                .into(object : SimpleTarget<Bitmap?>() {
                                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                                        callback?.onLoadComplete(resource)
                                    }
                                })
                    }

                    override fun fitWidth(): Boolean {
                        return false;
                    }

                    override fun getDefaultDrawable(): Drawable {
                        return ContextCompat.getDrawable(this@ReviewDetailActivity, R.drawable.loading_animation)!!
                    }
                }).setOnTagClickListener(object : OnTagClickListener{
                    override fun onImageClick(p0: Context?, imgs: MutableList<String>?, position: Int) {
                        var imageList = ArrayList<GameImage>()
                        imgs?.forEach {
                            imageList.add(GameImage().apply { thumbUrl = it })
                        }

                        GameApplication.instance?.gameImages = imageList
                        ScreenshotActivity.start(this@ReviewDetailActivity, binding?.richtext!!, position)
                        ViewCompat.setTransitionName(binding?.richtext!!, "${ScreenshotActivity.EXTRA_SHARED_TRANS_NAME}$position")

                    }

                    override fun onLinkClick(p0: Context?, p1: String?) {

                    }
                })
                .into(binding?.richtext)

    }

    override fun setListener() {
        viewModel?.back?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                finish()
            }
        })

        viewModel?.viewReviewLink?.observe(this, Observer {
            if (it.getContentIfNotHandled() == true) {
                reviewModel.siteDetailUrl?.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }
            }
        })

    }
}