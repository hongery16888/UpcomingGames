package com.magic.upcoming.games.fragment

import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseFragment
import com.magic.upcoming.games.databinding.FragmentFavoriteBinding
import com.magic.upcoming.games.databinding.FragmentTest2Binding
import com.magic.upcoming.games.listener.OnNetworkListener
import com.magic.upcoming.games.viewmodel.EmptyViewModel
import java.util.*

class Test2Fragment: BaseFragment<FragmentTest2Binding, EmptyViewModel>(), OnNetworkListener<ArrayList<String>> {

    override val layoutId: Int
        get() = R.layout.fragment_test2

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