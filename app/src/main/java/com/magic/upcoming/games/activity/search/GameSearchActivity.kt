package com.magic.upcoming.games.activity.search

import android.annotation.SuppressLint
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.magic.upcoming.games.R
import com.magic.upcoming.games.adapter.SearchListAdapter
import com.magic.upcoming.games.base.BaseActivity
import com.magic.upcoming.games.databinding.ActivityGameSearchBinding
import com.magic.upcoming.games.viewmodel.search.GameSearchViewModel

internal class GameSearchActivity : BaseActivity<ActivityGameSearchBinding?, GameSearchViewModel>() {

    lateinit var keyword: String
    lateinit var adapter: SearchListAdapter

    override val layoutId: Int
        get() = R.layout.activity_game_search

    override fun createViewModel(): GameSearchViewModel {
        return GameSearchViewModel()
    }

    var handler: Handler? = Handler()

    override fun initView() {
        binding?.lifecycleOwner = this
        binding?.viewModel = viewModel

        viewModel?.onShowKeyboard()

        binding?.searchRecyclerView?.layoutManager = LinearLayoutManager(this)
        adapter = SearchListAdapter(this)
        binding?.searchRecyclerView?.adapter = adapter
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun setListener() {
        viewModel?.back?.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                finish()
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

        viewModel?.searchResults?.observe(this, Observer {
            it?.let { data ->
                binding?.refreshLayout?.finishRefresh()
                adapter.items.clear()
                adapter.items.addAll(data)
            }
        })

        binding?.searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                // When searchEditText changes, re-query the database.
                if (s.toString().isEmpty()) {
                    handler?.removeCallbacks(runnable)
                    return
                }

                keyword = s.toString()
                handler?.postDelayed(runnable, 2000)
            }
        })

        binding?.refreshLayout?.setOnRefreshListener {
            viewModel?.searchGameList(keyword)
        }

    }

    var runnable: Runnable = Runnable {
        binding?.refreshLayout?.autoRefresh()
    }
}