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
    @SerializedName("image")
    var mainImageUrl: GameImage? = null
    @SerializedName("images")
    var images: ArrayList<GameImage>? = null
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
    @SerializedName("original_game_rating")
    var originalGameRating: ArrayList<GameRating>? = null
    @SerializedName("developers")
    var developers: ArrayList<GameGeneric>? = null
    @SerializedName("publishers")
    var publishers: ArrayList<GameGeneric>? = null
    @SerializedName("genres")
    var genres: ArrayList<GameGeneric>? = null
    @SerializedName("deck")
    var deck: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("site_detail_url")
    var detailUrl: String? = null
}