package com.magic.upcoming.games.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.AndroidNetworking
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.utilities.Event

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

    fun gameList(){

    }
}