package com.magic.upcoming.games.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.magic.upcoming.games.R
import com.magic.upcoming.games.activity.game.ScreenshotActivity
import com.magic.upcoming.games.base.BaseBindingAdapter
import com.magic.upcoming.games.constant.DateFormat
import com.magic.upcoming.games.databinding.GameListItemBinding
import com.magic.upcoming.games.databinding.ScreenshotItemBinding
import com.magic.upcoming.games.databinding.SearchListItemBinding
import com.magic.upcoming.games.databinding.VideoListItemBinding
import com.magic.upcoming.games.model.game.GameImage
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.model.search.SearchResult
import com.magic.upcoming.games.model.video.VideoModel
import com.magic.upcoming.games.utilities.fetchReleaseDateInMillis


class ScreenshotListAdapter(private val mContext: Context) : BaseBindingAdapter<GameImage, ScreenshotItemBinding>(mContext) {

    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.screenshot_item
    }

    override fun onBindItem(binding: ScreenshotItemBinding?, item: GameImage, position: Int) {
        binding?.screenshotUrl = item.thumbUrl
        binding?.screenshotImageView?.setOnClickListener {
            ScreenshotActivity.start(mContext as Activity, it, position)
        }
        ViewCompat.setTransitionName(binding?.screenshotImageView!!, "${ScreenshotActivity.EXTRA_SHARED_TRANS_NAME}$position")
    }

}