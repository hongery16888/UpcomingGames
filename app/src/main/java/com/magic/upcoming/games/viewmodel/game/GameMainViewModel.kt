package com.magic.upcoming.games.viewmodel.game

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.model.base.BaseModel
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.repository.RepositoryFactory
import com.magic.upcoming.games.utilities.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameMainViewModel : BaseViewModel() {

    var initData: ObservableField<Boolean> = ObservableField(true)
    var emptyStatus: ObservableField<Boolean> = ObservableField(false)

    private val _Search = MutableLiveData<Event<Boolean>>()
    val search: LiveData<Event<Boolean>>
        get() = _Search

    fun search(){
        _Search.value = Event(true)
    }

    private val _Favorite = MutableLiveData<Event<Boolean>>()
    val favorite: LiveData<Event<Boolean>>
        get() = _Favorite

    fun favorite(){

    }

    private val _Filter = MutableLiveData<Event<Boolean>>()
    val filter: LiveData<Event<Boolean>>
        get() = _Filter

    fun filter(){
        _Filter.value = Event(true)
    }

    private val _GameList = MutableLiveData<Event<BaseModel<ArrayList<GameModel>>>>()
    val gameList: LiveData<Event<BaseModel<ArrayList<GameModel>>>>
        get() = _GameList

    @SuppressLint("CheckResult")
    fun gameList(offset: Int = 20, limit:Int = 10){

//        RepositoryFactory.getGameRepo().gameDetail("3030-5")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    println("------------------>Id : ${it.result?.gameId}")
//                    println("------------------>onSuccess : ${it.result?.gameGuid}   :  Name : ${it.result?.gameName}")
//                }, {
//                    println("------------------>Throwable : ${it.message}")
//                })

        RepositoryFactory.getGameApiRepo().gameList(20, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _GameList.value = Event(it)
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