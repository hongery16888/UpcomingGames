package com.magic.upcoming.games.viewmodel.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.model.search.SearchResult
import com.magic.upcoming.games.repository.RepositoryFactory
import com.magic.upcoming.games.utilities.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class GameSearchViewModel : BaseViewModel() {

    private val _back = MutableLiveData<Event<Boolean>>()
    val back: LiveData<Event<Boolean>>
        get() = _back

    fun back() {
        _back.value = Event(true)
    }

    private val _clearSearchText = MutableLiveData<Event<Boolean>>()
    val clearSearchText: LiveData<Event<Boolean>>
        get() = _clearSearchText

    private val _showKeyboard = MutableLiveData<Event<Boolean>>()
    val showKeyboard: LiveData<Event<Boolean>>
        get() = _showKeyboard

    fun onClearSearchText() {
        _clearSearchText.value = Event(true)
    }

    fun onShowKeyboard() {
        _showKeyboard.value = Event(true)
    }

    val searchResults = MutableLiveData<ArrayList<SearchResult>>()

    fun searchGameList(searchQuery: String) {
        viewModelScope.launch {
//            searchResults.value = gameRepository.searchGameList(searchQuery)
            RepositoryFactory.getGameApiRepo().searchList(searchQuery)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        searchResults.value = it.result
                    }, {
                        toast(it.message)
                    })
        }
    }
}