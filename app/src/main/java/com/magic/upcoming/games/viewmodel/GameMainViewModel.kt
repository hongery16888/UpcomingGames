package com.magic.upcoming.games.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.AndroidNetworking
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.conts.GameConts
import com.magic.upcoming.games.repository.RepositoryFactory
import com.magic.upcoming.games.utilities.Event
import com.rx2androidnetworking.Rx2AndroidNetworking
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
                    println("------------------>Id : ${it.id}")
                    println("------------------>onSuccess : ${it.guid}   :  Name : ${it.name}")
                }, {
                    println("------------------>Throwable : ${it.message}")
                })

//        Rx2AndroidNetworking.post(GameConts.GIANT_BOMB_BASE_PATH + "games")
//                .addQueryParameter("api_key", GameConts.GIANT_BOMB_API_KEY)
//                .addQueryParameter("format", "json")
//                .addQueryParameter("limit", "10")
//                .addQueryParameter("sort", "date_last_updated:asc")
//                .addQueryParameter("filter", "date_last_updated:2020-02-01|2020-08-01")
//                .addQueryParameter("field_list", "id,guid,name,image,platforms")
//                .addQueryParameter("offset", "0")
//                .build()
//                .stringObservable
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnNext { println("------------------>Success : $it") }
//                .doOnError { println("------------------>Failure : ${it.message}") }
//                .subscribe({}, {
//                    println("------------------>Game Throwable : ${it.message}")
//                })
    }
}