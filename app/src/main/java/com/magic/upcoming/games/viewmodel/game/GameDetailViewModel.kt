package com.magic.upcoming.games.viewmodel.game

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.constant.NetworkState
import com.magic.upcoming.games.model.game.GameDetailModel
import com.magic.upcoming.games.repository.RepositoryFactory
import com.magic.upcoming.games.utilities.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameDetailViewModel : BaseViewModel() {

    var networkState: ObservableField<NetworkState> = ObservableField(NetworkState.Loading)
    var guid: ObservableField<String> = ObservableField()

    private val _back = MutableLiveData<Event<Boolean>>()
    val back: LiveData<Event<Boolean>>
        get() = _back

    fun back() {
        _back.value = Event(true)
    }

    private val _gameDetail = MutableLiveData<GameDetailModel>()
    val gameDetail: LiveData<GameDetailModel>
        get() = _gameDetail

    fun loadGameDetail() {
        networkState.set(NetworkState.Loading)
        viewModelScope.launch {
            RepositoryFactory.getGameApiRepo().gameDetail(guid.get()!!)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        _gameDetail.value = it.result
                        networkState.set(NetworkState.Success)
                    }, {
                        toast(it.message)
                        networkState.set(NetworkState.Failure)
                    })
        }
    }

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean>
        get() = _isFavorite

    fun updateFavorite() {
        // Invert value of _isFavorite
        val isFavoriteNewValue = !_isFavorite.value!!

        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val rowsUpdated = gameRepository.updateFavorite(isFavoriteNewValue, guid)
//                if (rowsUpdated == 1) {
//                    _isFavorite.postValue(isFavoriteNewValue)
//                } else {
                toast("Failed to update a favorite.")
//            }
        }
    }

    private val _viewGameLink = MutableLiveData<Event<Boolean>>()
    val viewGameLink: LiveData<Event<Boolean>>
        get() = _viewGameLink

    fun onViewGameLink() {
        _viewGameLink.value = Event(true)
    }
}