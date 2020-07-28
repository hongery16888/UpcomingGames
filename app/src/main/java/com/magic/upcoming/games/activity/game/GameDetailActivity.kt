package com.magic.upcoming.games.activity.game

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Observer
import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseActivity
import com.magic.upcoming.games.databinding.ActivityGameDetailBinding
import com.magic.upcoming.games.viewmodel.game.GameDetailViewModel

internal class GameDetailActivity : BaseActivity<ActivityGameDetailBinding?, GameDetailViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_game_detail

    override fun createViewModel(): GameDetailViewModel {
        return GameDetailViewModel()
    }

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        binding?.viewModel?.guid?.set(intent.getStringExtra("guid"))

        binding?.viewModel?.loadGameDetail()
    }

    override fun setListener() {
        viewModel?.back?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                finish()
            }
        })

        viewModel?.viewGameLink?.observe(this, Observer {
            if (it.getContentIfNotHandled() == true) {
                val url = viewModel?.gameDetail?.value?.detailUrl
                url?.let {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(url)
                    startActivity(intent)
                }
            }
        })

        viewModel?.gameDetail?.observe(this, Observer {
            it?.let { data -> binding?.gameDetial = data }
        })
    }

}