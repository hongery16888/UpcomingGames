package com.magic.upcoming.games.viewmodel.review

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.utilities.Event

class ReviewDetailViewModel : BaseViewModel() {

    private val _back = MutableLiveData<Event<Boolean>>()
    val back: LiveData<Event<Boolean>>
        get() = _back

    fun back() {
        _back.value = Event(true)
    }

    private val _viewReviewLink = MutableLiveData<Event<Boolean>>()
    val viewReviewLink: LiveData<Event<Boolean>>
        get() = _viewReviewLink

    fun onViewReviewLink() {
        _viewReviewLink.value = Event(true)
    }
}