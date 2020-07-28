package com.magic.upcoming.games.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseBindingAdapter
import com.magic.upcoming.games.constant.DateFormat
import com.magic.upcoming.games.databinding.GameListItemBinding
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.utilities.fetchReleaseDateInMillis


class GameListAdapter(mContext: Context) : BaseBindingAdapter<GameModel, GameListItemBinding>(mContext) {

    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.game_list_item
    }

    override fun onBindItem(binding: GameListItemBinding?, item: GameModel, position: Int) {
        binding?.game = item
        val releaseDateArray = fetchReleaseDateInMillis(
                item.originalReleaseDate,
                item.expectedReleaseYear,
                item.expectedReleaseQuarter,
                item.expectedReleaseMonth,
                item.expectedReleaseDay
        )

        binding?.releaseDateInMillis = releaseDateArray[0] as Long?
        binding?.dateFormat = (releaseDateArray[1] as DateFormat).formatCode

        val flexboxLayout = binding?.platformFlexboxLayout

        //Since ViewHolders are reused, we need to remove the previously added Views first.
        flexboxLayout?.removeAllViews()

        item.platforms?.forEach {
            val textView = LayoutInflater.from(binding?.root?.context).inflate(
                    R.layout.platform_chip_layout,
                    flexboxLayout, false
            ) as TextView

            textView.text = it.abbreviation
            flexboxLayout?.addView(textView)
        }

    }

}