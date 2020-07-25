package com.magic.upcoming.games.listener

interface OnNetworkListener<D> {
    fun onSuccess(data: D)
    fun onFailure(errorMsg: String?)
}