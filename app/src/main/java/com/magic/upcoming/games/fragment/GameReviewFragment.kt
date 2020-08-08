package com.magic.upcoming.games.fragment

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.magic.upcoming.games.R
import com.magic.upcoming.games.adapter.ReviewListAdapter
import com.magic.upcoming.games.base.BaseFragment
import com.magic.upcoming.games.databinding.FragmentGameReviewBinding
import com.magic.upcoming.games.viewmodel.review.GameReviewViewModel

class GameReviewFragment: BaseFragment<FragmentGameReviewBinding, GameReviewViewModel>() {

    lateinit var adapter: ReviewListAdapter
    private var isRefresh: Boolean = true

    override val layoutId: Int
        get() = R.layout.fragment_game_review

    override fun createFragmentViewModel(): GameReviewViewModel {
        return GameReviewViewModel()
    }

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.review = viewModel

        binding?.videoRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
        adapter = ReviewListAdapter(requireContext())
        binding?.videoRecyclerView?.adapter = adapter

        binding?.review?.reviewList(0)
    }

    override fun setListener() {
        viewModel?.loadingStatus?.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                binding?.refreshLayout?.finishRefresh()
                binding?.refreshLayout?.finishLoadMore()
            }
        })

        viewModel?.reviewList?.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { model ->
                model.result?.let { date ->
                    if (isRefresh) adapter.items.clear()
                    adapter.items.addAll(date)
                }
            }
        })

        binding?.refreshLayout?.setOnRefreshListener {
            isRefresh = true
            viewModel?.reviewList(0)
        }
        binding?.refreshLayout?.setOnLoadMoreListener {
            if (adapter.items.size == 0)
                viewModel?.loadingStatus()
            isRefresh = false
            viewModel?.reviewList(adapter.items.size + 1)
        }
    }

}