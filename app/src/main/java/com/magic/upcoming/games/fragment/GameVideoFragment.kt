package com.magic.upcoming.games.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.magic.upcoming.games.R
import com.magic.upcoming.games.adapter.VideoListAdapter
import com.magic.upcoming.games.base.BaseFragment
import com.magic.upcoming.games.databinding.FragmentGameVideoBinding
import com.magic.upcoming.games.viewmodel.video.GameVideoViewModel

class GameVideoFragment: BaseFragment<FragmentGameVideoBinding, GameVideoViewModel>() {

    lateinit var adapter: VideoListAdapter
    private var isRefresh: Boolean = true

    override val layoutId: Int
        get() = R.layout.fragment_game_video

    override fun createFragmentViewModel(): GameVideoViewModel {
        return GameVideoViewModel()
    }

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        binding?.videoRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        adapter = VideoListAdapter(requireContext())
        binding?.videoRecyclerView?.adapter = adapter

        binding?.viewModel?.videoList(0)
    }

    override fun setListener() {
        viewModel?.loadingStatus?.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                binding?.refreshLayout?.finishRefresh()
                binding?.refreshLayout?.finishLoadMore()
            }
        })

        viewModel?.videoList?.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { model ->
                model.result?.let { date ->
                    if (isRefresh) adapter.items.clear()
                    adapter.items.addAll(date)
                }
            }
        })

        binding?.refreshLayout?.setOnRefreshListener {
            isRefresh = true
            viewModel?.videoList(0)
        }
        binding?.refreshLayout?.setOnLoadMoreListener {
            if (adapter.items.size == 0)
                viewModel?.loadingStatus()
            isRefresh = false
            viewModel?.videoList(adapter.items.size + 1)
        }
    }

}