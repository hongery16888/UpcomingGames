package com.magic.upcoming.games.activity.filter

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast
import androidx.lifecycle.Observer
import com.magic.upcoming.games.R
import com.magic.upcoming.games.adapter.FilterPlatformAdapter
import com.magic.upcoming.games.base.BaseActivity
import com.magic.upcoming.games.constant.allKnownPlatforms
import com.magic.upcoming.games.databinding.ActivityGameFilterBinding
import com.magic.upcoming.games.orm.OrmGameApi
import com.magic.upcoming.games.utils.DateInputTextWatcher
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

        // Attach DateInputTextWatchers to date editTexts.
        DateInputTextWatcher(binding?.startDateTextInputEditText!!).listen()
        DateInputTextWatcher(binding?.endDateTextInputEditText!!).listen()

        binding?.platformRecyclerView?.adapter = FilterPlatformAdapter(this).apply {
            items.addAll(allKnownPlatforms)
        }

        println("------------------>StartTime : ${OrmGameApi.gameFilterOptions?.releastStartDataMills}")
        println("------------------>EndTime : ${OrmGameApi.gameFilterOptions?.releastEndDataMills}")
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
                viewModel?.onUpdateDataType(
                binding?.startDateTextInputEditText?.error?.toString(),
                binding?.startDateTextInputEditText?.text?.toString(),
                binding?.endDateTextInputEditText?.error?.toString(),
                binding?.endDateTextInputEditText?.text?.toString()
                )
            }
        })

        viewModel?.updateDataType?.observe(this, Observer {
            it.getContentIfNotHandled()?.let { updateFilterOptions ->
                if (updateFilterOptions){
                    OrmGameApi.gameFilterOptions?.releastStartData = binding?.startDateTextInputEditText?.text?.toString()
                    OrmGameApi.gameFilterOptions?.releastEndData = binding?.endDateTextInputEditText?.text?.toString()
                    OrmGameApi.gameFilterOptions?.save()
                    toast("Save Filter Success")
                    finish()
                }else{
                    displayInvalidDateToast()
                }
            }
        })
    }

    private fun displayInvalidDateToast() {
        Toast.makeText(
                this,
                "Before proceeding, you must enter valid dates in the \"Release date\" section.",
                Toast.LENGTH_LONG
        ).show()

        val vibrator =
                getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= 26) {
            vibrator?.vibrate(
                    VibrationEffect.createOneShot(
                            200,
                            VibrationEffect.DEFAULT_AMPLITUDE
                    )
            )
        } else {
            vibrator?.vibrate(200)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        OrmGameApi.gameFilterOptions = null
        finish()
    }
}