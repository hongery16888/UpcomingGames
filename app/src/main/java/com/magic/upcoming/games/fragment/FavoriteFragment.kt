package com.magic.upcoming.games.fragment

import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseFragment
import com.magic.upcoming.games.databinding.FragmentFavoriteBinding
import com.magic.upcoming.games.listener.OnNetworkListener
import com.magic.upcoming.games.viewmodel.EmptyViewModel
import java.util.*

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding, EmptyViewModel>(), OnNetworkListener<ArrayList<String>> {

    override val layoutId: Int
        get() = R.layout.fragment_favorite

    override fun createFragmentViewModel(): EmptyViewModel {
        return EmptyViewModel()
    }

    override fun initView() {
    }

    override fun setListener() {

    }

    override fun onSuccess(data: ArrayList<String>) {
    }

    override fun onFailure(errorMsg: String?) {
    }

    override fun onResume() {
        super.onResume()
    }
}