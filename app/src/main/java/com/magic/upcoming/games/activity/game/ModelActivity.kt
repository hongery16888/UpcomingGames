package com.magic.upcoming.games.activity.game

import androidx.lifecycle.Observer
import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseActivity
import com.magic.upcoming.games.databinding.ActivityGameDetailBinding
import com.magic.upcoming.games.viewmodel.game.GameDetailViewModel

internal class ModelActivity : BaseActivity<ActivityGameDetailBinding?, GameDetailViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_game_search

    override fun createViewModel(): GameDetailViewModel {
        return GameDetailViewModel()
    }

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

    }

    override fun setListener() {
        viewModel?.back?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                finish()
            }
        })

    }

}