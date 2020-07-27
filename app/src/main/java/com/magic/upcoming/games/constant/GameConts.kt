package com.magic.upcoming.games.constant

object GameConts {
    const val BASE_PATH = "https://fy.iciba.com/"
    const val GIANT_BOMB_BASE_PATH = "https://www.giantbomb.com/api/"
    const val GIANT_BOMB_API_KEY = "c0d838fadae058f620be7fdaffbbbd8c7ad29d12"
}

enum class ReleaseDateType {
    RecentAndUpcoming,
    PastMonth,
    PastYear,
    Any,
    CustomDate
}

enum class SortDirection(val direction: String) {
    Ascending("asc"),
    Descending("desc")
}

enum class PlatformType {
    CurrentGeneration,
    All,
    PickFromList
}

