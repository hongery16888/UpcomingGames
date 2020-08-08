package com.magic.upcoming.games.model.game

import com.google.gson.annotations.SerializedName

class GameItemModel{
    @SerializedName("api_detail_url")
    var apiDetailUrl: String? = null
    @SerializedName("id")
    var gameId: String? = null
    @SerializedName("name")
    var gameName: String? = null
    @SerializedName("site_detail_url")
    var siteDetailUrl: String? = null
}