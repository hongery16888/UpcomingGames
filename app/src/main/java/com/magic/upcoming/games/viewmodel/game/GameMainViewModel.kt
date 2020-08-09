package com.magic.upcoming.games.viewmodel.game

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
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

        val gson = Gson()

        RepositoryFactory.getGameApiRepo().companyList(0, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("------------------>Company : $it")
                    save(gson.toJson(it))
                }, {
                    toast(it.message)
                })

//        Rx2AndroidNetworking.get("https://www.giantbomb.com/api/companies/?api_key=c0d838fadae058f620be7fdaffbbbd8c7ad29d12&format=json&limit=2&sort=date_last_updated:dec")
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


    }

    @SuppressLint("CheckResult")
    private fun save(strData: String){
        Rx2AndroidNetworking.post("https://www.playsoftware.net/boon/save_company.php")
                .addBodyParameter("JsonStr", strData)
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