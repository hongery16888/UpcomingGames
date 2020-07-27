package com.magic.upcoming.games.orm

import org.litepal.LitePal
import org.litepal.extension.find

internal object OrmGameApi {
    var gameFilterOptions: GameFilterOptions? = null
        get() {
            if (field == null) field = LitePal.find<GameFilterOptions>(1)
            return field
        }
}