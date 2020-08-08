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
import com.rx2androidnetworking.Rx2AndroidNetworking
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
    fun gameList(offset: Int = 0, limit:Int = 50){

        RepositoryFactory.getGameApiRepo().gameList(offset, limit)
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

    @SuppressLint("CheckResult")
    fun saveGameInfo(){
//        Rx2AndroidNetworking.get("https://www.playsoftware.net/boon/game_index.php")
//                .addQueryParameter("game_id", "44")
//                .addQueryParameter("game_guid", "88-1")
//                .addQueryParameter("game_name", "MagicHua")
//                .build()
//                .stringObservable
//                .subscribeOn(Schedulers.io())
//                .doOnError { println("------------------>Error : $it") }
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    println("------------------>PHP Return : $it")
//                },{
//                    println("------------------>Throwable : $it")
//                })

        Rx2AndroidNetworking.get("https://www.playsoftware.net/boon/save_game.php")
                .addQueryParameter("JsonStr", "{\"error\":\"OK\",\"limit\":2,\"offset\":0,\"number_of_page_results\":2,\"number_of_total_results\":74575,\"status_code\":1,\"results\":[{\"name\":\"Desert Strike: Return to the Gulf\"},{\"name\":\"Breakfree\"}],\"version\":\"1.0\"}")
                .build()
                .stringObservable
                .subscribeOn(Schedulers.io())
                .doOnError { println("------------------>Error : $it") }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("------------------>PHP Return : $it")
                },{
                    println("------------------>Throwable : $it")
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