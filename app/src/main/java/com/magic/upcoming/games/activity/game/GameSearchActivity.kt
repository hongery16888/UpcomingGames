package com.magic.upcoming.games.activity.game

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseActivity
import com.magic.upcoming.games.databinding.ActivityGameSearchBinding
import com.magic.upcoming.games.viewmodel.GameSearchViewModel

internal class GameSearchActivity : BaseActivity<ActivityGameSearchBinding?, GameSearchViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_game_search

    override fun createViewModel(): GameSearchViewModel {
        return GameSearchViewModel()
    }

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        viewModel?.searchGameList("")

        viewModel?.onShowKeyboard()
    }

    override fun setListener() {
        viewModel?.back?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                finish()
            }
        })

        binding?.searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel?.searchGameList(s.toString())
            }
        })

        viewModel?.showKeyboard?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                binding?.root?.postDelayed({
                    showSoftKeyboard(binding?.searchEditText!!)
                }, 50)
            }
        })

        viewModel?.clearSearchText?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                binding?.searchEditText?.setText("")
            }
        })
    }

}