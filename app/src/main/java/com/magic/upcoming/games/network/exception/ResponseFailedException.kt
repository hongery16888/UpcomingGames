package com.magic.upcoming.games.network.exception

/**
 * Created by ThinkPad on 2017/11/2.
 */
class ResponseFailedException : Throwable {

    var errorMessage: String? = null

    constructor()

    constructor(mes: String?) {
        errorMessage = mes
    }

    override val message: String?
        get() = if (errorMessage.isNullOrEmpty()) super.message
                    else errorMessage

}