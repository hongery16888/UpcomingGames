package com.magic.upcoming.games.model.search

import com.google.gson.annotations.SerializedName
import com.magic.upcoming.games.model.game.GameImage

class SearchResult {

    @SerializedName("id")
    var gameId = 0

    @SerializedName("guid")
    var guid: String? = null

    @SerializedName("name")
    var gameName: String? = null

    @SerializedName("image")
    var image: GameImage? = null

    @SerializedName("resource_type")
    var resourceType:String? = null

}