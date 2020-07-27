package com.magic.upcoming.games.orm

import org.litepal.crud.LitePalSupport

internal class GameFilterOptions : LitePalSupport() {
    var sortDirectionName: String? = null
    var releaseDateTypeName: String? = null
    var releaseStartData: String? = ""
    var releaseEndData: String? = ""
    var releaseStartDataNet: String? = ""
    var releaseEndDataNet: String? = ""
    var platformTypeName: String? = null
    var platformIndices: ArrayList<Int>? = ArrayList()
}