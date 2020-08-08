package com.magic.upcoming.games.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.magic.upcoming.games.R
import com.magic.upcoming.games.activity.filter.GameFilterActivity
import com.magic.upcoming.games.activity.search.GameSearchActivity
import com.magic.upcoming.games.adapter.GameListAdapter
import com.magic.upcoming.games.base.BaseFragment
import com.magic.upcoming.games.databinding.FragmentGameMainBinding
import com.magic.upcoming.games.model.base.BaseModel
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.viewmodel.game.GameMainViewModel
import kotlin.collections.ArrayList

class GameMainFragment: BaseFragment<FragmentGameMainBinding, GameMainViewModel>() {

    lateinit var adapter: GameListAdapter
    private var isRefresh: Boolean = true

    override val layoutId: Int
        get() = R.layout.fragment_game_main

    override fun createFragmentViewModel(): GameMainViewModel {
        return GameMainViewModel()
    }

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        binding?.gameRecyclerView?.layoutManager = GridLayoutManager(context, 2)
        adapter = GameListAdapter(requireContext())
        binding?.gameRecyclerView?.adapter = adapter

        viewModel?.gameList(0)
        viewModel?.saveGameInfo()
    }

    override fun setListener() {
        viewModel?.search?.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                startActivity(Intent(context, GameSearchActivity::class.java))
            }
        })

        viewModel?.filter?.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                startActivity(Intent(context, GameFilterActivity::class.java))
            }
        })

        viewModel?.loadingStatus?.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                binding?.refreshLayout?.finishRefresh()
                binding?.refreshLayout?.finishLoadMore()
            }
        })

        viewModel?.gameList?.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { model ->
                model.result?.let { date ->
                    if (isRefresh) adapter.items.clear()
                    adapter.items.addAll(date)
                }
            }
        })

        binding?.refreshLayout?.setOnRefreshListener {
            isRefresh = true
            viewModel?.gameList(0)
        }
        binding?.refreshLayout?.setOnLoadMoreListener {
            if (adapter.items.size == 0)
                viewModel?.loadingStatus()
            isRefresh = false
            viewModel?.gameList(adapter.items.size + 1)
        }
    }

}