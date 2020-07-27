package com.magic.upcoming.games.orm

import org.litepal.crud.LitePalSupport

internal class GameFilterOptions : LitePalSupport() {
    var sortDirectionName: String? = null
    var releaseDateTypeName: String? = null
    var releastStartData: String? = ""
    var releastEndData: String? = ""
    var releastStartDataMills: Long? = 0
    var releastEndDataMills: Long? = 0
    var platformTypeName: String? = null
    var platformIndices: ArrayList<Int>? = ArrayList()
}