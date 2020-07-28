package com.magic.upcoming.games.model.video

import com.google.gson.annotations.SerializedName
import com.magic.upcoming.games.model.game.GameImage

class VideoModel {

    @SerializedName("id")
    var videoId = 0

    @SerializedName("guid")
    var guid: String? = null

    @SerializedName("name")
    var videoName: String? = null

    @SerializedName("image")
    var image: GameImage? = null

    @SerializedName("length_seconds")
    var videoLength:String? = null

    @SerializedName("publish_date")
    var publishDate:String? = null

    @SerializedName("site_detail_url")
    var siteDetailUrl:String? = null

    @SerializedName("user")
    var user:String? = null

    @SerializedName("youtube_id")
    var youtubeId:String? = null

    @SerializedName("video_type")
    var videoType:String? = null

    @SerializedName("deck")
    var deck:String? = null

    @SerializedName("hd_url")
    var hdUrl:String? = null

    @SerializedName("high_url")
    var highUrl:String? = null

    @SerializedName("low_url")
    var lowUrl:String? = null
}