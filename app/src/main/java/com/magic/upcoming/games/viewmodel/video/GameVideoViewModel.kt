package com.magic.upcoming.games.viewmodel.video

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.model.base.BaseModel
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.model.video.VideoModel
import com.magic.upcoming.games.repository.RepositoryFactory
import com.magic.upcoming.games.utilities.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameVideoViewModel : BaseViewModel() {

    var initData: ObservableField<Boolean> = ObservableField(true)
    var emptyStatus: ObservableField<Boolean> = ObservableField(false)

    private val _VideoList = MutableLiveData<Event<BaseModel<ArrayList<VideoModel>>>>()
    val videoList: LiveData<Event<BaseModel<ArrayList<VideoModel>>>>
        get() = _VideoList

    @SuppressLint("CheckResult")
    fun videoList(offset: Int = 0, limit:Int = 10){

        RepositoryFactory.getGameApiRepo().videoList(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _VideoList.value = Event(it)
                    emptyStatus.set(it.result.isNullOrEmpty())
                    loadingStatus()
                }, {
                    toast(it.message)
                    loadingStatus()
                })
    }

    private val _LoadingStatus = MutableLiveData<Event<Boolean>>()
    val loadingStatus: LiveData<Event<Boolean>>
        get() = _LoadingStatus

    fun loadingStatus(){
        initData.set(false)
        _LoadingStatus.value = Event(true)
    }

}