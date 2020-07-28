package com.magic.upcoming.games.model.game

import com.google.gson.annotations.SerializedName

class GameRating {

    @SerializedName("api_detail_url")
    var apiDetailUrl: String? = null

    @SerializedName("id")
    var ratingId = 0

    @SerializedName("name")
    var ratingName: String? = null

}