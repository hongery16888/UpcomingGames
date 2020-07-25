package com.magic.upcoming.games.base

import android.app.Application
import androidx.databinding.BaseObservable
import androidx.lifecycle.AndroidViewModel

/**
 * Created by yuzhe on 2017/4/8.
 */
abstract class BaseViewModel(application: Application  = BaseApplication.instance!!) : AndroidViewModel(application) {

//    abstract fun <T> getData(onNetworkListener: OnNetworkListener<T>? = null) : T

}