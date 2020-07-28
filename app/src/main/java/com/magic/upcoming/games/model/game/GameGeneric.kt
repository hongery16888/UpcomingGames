package com.magic.upcoming.games.model.game

import com.google.gson.annotations.SerializedName
import com.magic.upcoming.games.model.BaseModel

open class GameGeneric : BaseModel() {

    @SerializedName("api_detail_url")
    var apiDetailUrl: String? = null

    @SerializedName("site_detail_url")
    var sideDetailUrl: String? = null

    @SerializedName("id")
    var genericId = 0

    @SerializedName("name")
    var genericName: String? = null

}