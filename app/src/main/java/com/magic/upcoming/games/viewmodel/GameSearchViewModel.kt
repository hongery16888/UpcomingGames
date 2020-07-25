package com.magic.upcoming.games.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.utilities.Event
import kotlinx.coroutines.launch

class GameSearchViewModel : BaseViewModel() {

    private val _back = MutableLiveData<Event<Boolean>>()
    val back: LiveData<Event<Boolean>>
        get() = _back

    private val _clearSearchText = MutableLiveData<Event<Boolean>>()
    val clearSearchText: LiveData<Event<Boolean>>
        get() = _clearSearchText

    private val _showKeyboard = MutableLiveData<Event<Boolean>>()
    val showKeyboard: LiveData<Event<Boolean>>
        get() = _showKeyboard

    fun back(){
        _back.value = Event(true)
    }

    fun onClearSearchText() {
        _clearSearchText.value = Event(true)
    }

    fun onShowKeyboard() {
        _showKeyboard.value = Event(true)
    }

    fun searchGameList(searchQuery: String) {
        viewModelScope.launch {
//            searchResults.value = gameRepository.searchGameList(searchQuery)
        }
    }
}