package com.magic.upcoming.games.fragment

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.magic.upcoming.games.R
import com.magic.upcoming.games.activity.game.GameSearchActivity
import com.magic.upcoming.games.base.BaseFragment
import com.magic.upcoming.games.databinding.FragmentGameMainBinding
import com.magic.upcoming.games.listener.OnNetworkListener
import com.magic.upcoming.games.viewmodel.GameMainViewModel
import java.util.*

class GameMainFragment: BaseFragment<FragmentGameMainBinding, GameMainViewModel>(), OnNetworkListener<ArrayList<String>> {

    override val layoutId: Int
        get() = R.layout.fragment_game_main

    override fun createFragmentViewModel(): GameMainViewModel {
        return GameMainViewModel()
    }

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel
        binding?.viewModel?.gameList()
    }

    override fun setListener() {
        viewModel?.search?.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let {
                startActivity(Intent(context, GameSearchActivity::class.java))
            }
        })
    }

    override fun onSuccess(data: ArrayList<String>) {
    }

    override fun onFailure(errorMsg: String?) {
    }

    override fun onResume() {
        super.onResume()
    }
}