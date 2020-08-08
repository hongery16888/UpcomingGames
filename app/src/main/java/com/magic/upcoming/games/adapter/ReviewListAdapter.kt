package com.magic.upcoming.games.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.TextView
import com.magic.upcoming.games.GameApplication
import com.magic.upcoming.games.R
import com.magic.upcoming.games.activity.review.ReviewDetailActivity
import com.magic.upcoming.games.activity.video.VideoDetailActivity
import com.magic.upcoming.games.base.BaseApplication
import com.magic.upcoming.games.base.BaseBindingAdapter
import com.magic.upcoming.games.constant.DateFormat
import com.magic.upcoming.games.databinding.GameListItemBinding
import com.magic.upcoming.games.databinding.ReviewListItemBinding
import com.magic.upcoming.games.databinding.SearchListItemBinding
import com.magic.upcoming.games.databinding.VideoListItemBinding
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.model.review.ReviewModel
import com.magic.upcoming.games.model.search.SearchResult
import com.magic.upcoming.games.model.video.VideoModel
import com.magic.upcoming.games.utilities.fetchReleaseDateInMillis
import com.magic.upcoming.games.utils.WebUtils

class ReviewListAdapter(private val mContext: Context) : BaseBindingAdapter<ReviewModel, ReviewListItemBinding>(mContext) {

    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.review_list_item
    }

    override fun onBindItem(binding: ReviewListItemBinding?, item: ReviewModel, position: Int) {
        WebUtils.returnImageUrlsFromHtml(item.description!!)?.let {
            item.reviewImage = it[0]
        }
        binding?.ratingStar?.rating = item.score
        binding?.review = item
        binding?.imageCardView?.setOnClickListener {
            mContext.startActivity(Intent(mContext, ReviewDetailActivity::class.java).apply {
                GameApplication.instance?.reviewModel = item
            })
        }
    }

}