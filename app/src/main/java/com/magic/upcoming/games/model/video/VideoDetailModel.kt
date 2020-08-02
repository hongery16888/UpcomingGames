package com.magic.upcoming.games.model.video

import com.google.gson.annotations.SerializedName
import com.magic.upcoming.games.model.game.GameImage

class VideoDetailModel {
    @SerializedName("api_detail_url")
    var apiDetailUrl: String? = null

    @SerializedName("deck")
    var deck: String? = null

    @SerializedName("embed_player")
    var embedPlayer: String? = null

    @SerializedName("guid")
    var guid: String? = null

    @SerializedName("id")
    var videoId = 0

    @SerializedName("length_seconds")
    var lengthSeconds = 0

    @SerializedName("name")
    var videoName: String? = null

    @SerializedName("premium")
    var isPremium = false

    @SerializedName("publish_date")
    var publishDate: String? = null

    @SerializedName("site_detail_url")
    var siteDetailUrl: String? = null

    @SerializedName("image")
    var videoImage: GameImage? = null

    @SerializedName("user")
    var user: String? = null

    @SerializedName("video_type")
    var videoType: String? = null

    @SerializedName("saved_time")
    var savedTime: Long = 0

    @SerializedName("youtube_id")
    var youtubeId: String? = null

    @SerializedName("low_url")
    var lowUrl: String? = null

    @SerializedName("high_url")
    var highUrl: String? = null

    @SerializedName("url")
    var url: String? = null

}