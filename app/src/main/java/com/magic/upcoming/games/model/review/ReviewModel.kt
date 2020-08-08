package com.magic.upcoming.games.model.review

import com.google.gson.annotations.SerializedName
import com.magic.upcoming.games.model.game.GameItemModel

class ReviewModel {
    @SerializedName("api_detail_url")
    var apiDetailUrl: String? = null

    @SerializedName("deck")
    var deck: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("game")
    var game: GameItemModel? = null

    @SerializedName("guid")
    var guid: String? = null

    @SerializedName("id")
    var reviewId = 0

    var reviewImage: String? = ""

    @SerializedName("publish_date")
    var publishDate: String? = null

    @SerializedName("reviewer")
    var reviewer: String? = null

    @SerializedName("score")
    var score: Float = 0f

    @SerializedName("site_detail_url")
    var siteDetailUrl: String? = null

    @SerializedName("platforms")
    var platforms: String? = null

}