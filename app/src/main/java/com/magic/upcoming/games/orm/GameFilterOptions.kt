package com.magic.upcoming.games.orm

import org.litepal.crud.LitePalSupport

internal class GameFilterOptions : LitePalSupport() {
    var sortDirectionName: String? = "Ascending"
    var releaseDateTypeName: String? = "RecentAndUpcoming"
    var releastStartData: String? = "2020-05-01"
    var releastEndData: String? = "2020-08-01"
    var platformTypeName: String? = "CurrentGeneration"
}