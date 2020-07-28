package com.magic.upcoming.games.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseBindingAdapter
import com.magic.upcoming.games.constant.DateFormat
import com.magic.upcoming.games.databinding.GameListItemBinding
import com.magic.upcoming.games.databinding.SearchListItemBinding
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.model.search.SearchResult
import com.magic.upcoming.games.utilities.fetchReleaseDateInMillis


class SearchListAdapter(mContext: Context) : BaseBindingAdapter<SearchResult, SearchListItemBinding>(mContext) {

    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.search_list_item
    }

    override fun onBindItem(binding: SearchListItemBinding?, item: SearchResult, position: Int) {
        binding?.searchResult = item
    }

}