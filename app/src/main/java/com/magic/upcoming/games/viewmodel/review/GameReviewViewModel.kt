package com.magic.upcoming.games.viewmodel.review

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.model.base.BaseModel
import com.magic.upcoming.games.model.review.ReviewModel
import com.magic.upcoming.games.repository.RepositoryFactory
import com.magic.upcoming.games.utilities.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GameReviewViewModel : BaseViewModel() {

    var initData: ObservableField<Boolean> = ObservableField(true)
    var emptyStatus: ObservableField<Boolean> = ObservableField(false)

    private val _ReviewList = MutableLiveData<Event<BaseModel<ArrayList<ReviewModel>>>>()
    val reviewList: LiveData<Event<BaseModel<ArrayList<ReviewModel>>>>
        get() = _ReviewList

    @SuppressLint("CheckResult")
    fun reviewList(offset: Int = 0, limit:Int = 50){

        RepositoryFactory.getGameApiRepo().reviewList(offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _ReviewList.value = Event(it)
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