package com.magic.upcoming.games.activity.game

import android.content.Intent
import android.net.Uri
import android.text.Spanned
import android.text.method.LinkMovementMethod
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magic.upcoming.games.GameApplication
import com.magic.upcoming.games.R
import com.magic.upcoming.games.adapter.ScreenshotListAdapter
import com.magic.upcoming.games.base.BaseActivity
import com.magic.upcoming.games.databinding.ActivityGameDetailBinding
import com.magic.upcoming.games.viewmodel.game.GameDetailViewModel
import com.pixplicity.htmlcompat.HtmlCompat


internal class GameDetailActivity : BaseActivity<ActivityGameDetailBinding?, GameDetailViewModel>() {

    lateinit var adapter: ScreenshotListAdapter

    override val layoutId: Int
        get() = R.layout.activity_game_detail

    override fun createViewModel(): GameDetailViewModel {
        return GameDetailViewModel()
    }

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        binding?.viewModel?.guid?.set(intent.getStringExtra("guid"))

        binding?.screenshotRecyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        adapter = ScreenshotListAdapter(this)
        binding?.screenshotRecyclerView?.adapter = adapter

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
            it?.let { data ->
                binding?.gameDetail = data
                GameApplication.instance?.gameImages = it.images

                data.description?.let { text ->
                    val fromHtml: Spanned = HtmlCompat.fromHtml(this, text, 0)
                    binding?.richtext?.movementMethod = LinkMovementMethod.getInstance()
                    binding?.richtext?.text = fromHtml
                }

                adapter.items.addAll(it.images!!)
            }
        })


    }

}