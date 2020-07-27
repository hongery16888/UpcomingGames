package com.magic.upcoming.games.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.repository.RepositoryFactory
import com.magic.upcoming.games.utilities.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameMainViewModel : BaseViewModel() {

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

    private val _GameList = MutableLiveData<Event<Boolean>>()
    val gameList: LiveData<Event<Boolean>>
        get() = _GameList

    @SuppressLint("CheckResult")
    fun gameList(){

        RepositoryFactory.getGameRepo().gameDetail("3030-5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("------------------>Id : ${it.gameId}")
                    println("------------------>onSuccess : ${it.gameGuid}   :  Name : ${it.gameName}")
                }, {
                    println("------------------>Throwable : ${it.message}")
                })
    }
}