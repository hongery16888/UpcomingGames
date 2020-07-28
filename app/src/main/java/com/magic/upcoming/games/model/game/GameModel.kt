package com.magic.upcoming.games.model.game

import com.google.gson.annotations.SerializedName
import com.magic.upcoming.games.model.BaseModel

open class GameModel  : BaseModel(){

    @SerializedName("id")
    var gameId: String? = null
    @SerializedName("guid")
    var guid: String? = null
    @SerializedName("name")
    var gameName: String? = null
    @SerializedName("image")
    var mainImage: GameImage? = null
    @SerializedName("platforms")
    var platforms: ArrayList<GamePlatform>? = null
    @SerializedName("original_release_date")
    var originalReleaseDate: String? = null
    @SerializedName("expected_release_year")
    var expectedReleaseYear: Int? = null
    @SerializedName("expected_release_quarter")
    var expectedReleaseQuarter: Int? = null
    @SerializedName("expected_release_month")
    var expectedReleaseMonth: Int? = null
    @SerializedName("expected_release_day")
    var expectedReleaseDay: Int? = null

}