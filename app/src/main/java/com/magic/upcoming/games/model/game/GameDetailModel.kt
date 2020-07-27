package com.magic.upcoming.games.model.game

import com.google.gson.annotations.SerializedName
import com.magic.upcoming.games.model.BaseModel

open class GameDetailModel : BaseModel() {

    @SerializedName("id")
    var gameId: Long = 0
    @SerializedName("guid")
    var gameGuid: String? = null
    @SerializedName("name")
    var gameName: String? = null
}