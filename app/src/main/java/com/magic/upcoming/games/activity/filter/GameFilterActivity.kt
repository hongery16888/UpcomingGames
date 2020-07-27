package com.magic.upcoming.games.activity.filter

import androidx.lifecycle.Observer
import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseActivity
import com.magic.upcoming.games.databinding.ActivityGameFilterBinding
import com.magic.upcoming.games.orm.OrmGameApi
import com.magic.upcoming.games.viewmodel.filter.GameFilterViewModel

internal class GameFilterActivity : BaseActivity<ActivityGameFilterBinding, GameFilterViewModel>() {

    override val layoutId: Int
        get() = R.layout.activity_game_filter

    override fun createViewModel(): GameFilterViewModel {
        return GameFilterViewModel()
    }

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
    }
    override fun setListener() {
        viewModel?.back?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                OrmGameApi.gameFilterOptions = null
                finish()
            }
        })

        viewModel?.apply?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                OrmGameApi.gameFilterOptions?.save()
                toast("Save Filter Success")
                finish()
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        OrmGameApi.gameFilterOptions = null
        finish()
    }
}