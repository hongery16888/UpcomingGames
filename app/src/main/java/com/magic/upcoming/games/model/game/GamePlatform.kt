package com.magic.upcoming.games.model.game

import com.google.gson.annotations.SerializedName

class GamePlatform {
    @SerializedName("api_detail_url")
    var apiDetailUrl: String? = null

    @SerializedName("id")
    var platformId = 0

    @SerializedName("name")
    var platformName: String? = null

    @SerializedName("site_detail_url")
    var siteDetailUrl: String? = null

    @SerializedName("abbreviation")
    var abbreviation: String? = null

}