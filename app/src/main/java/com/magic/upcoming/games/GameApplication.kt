package com.magic.upcoming.games

import com.magic.upcoming.games.base.BaseApplication
import com.magic.upcoming.games.constant.PlatformType
import com.magic.upcoming.games.constant.ReleaseDateType
import com.magic.upcoming.games.constant.SortDirection
import com.magic.upcoming.games.orm.GameFilterOptions
import com.magic.upcoming.games.orm.LitePalDB
import com.magic.upcoming.games.utils.FilterFormatUtils
import org.litepal.LitePal
import org.litepal.extension.find

class GameApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        LitePalDB.initDB(this)
    }

    companion object {
        var instance: BaseApplication? = null
            private set
    }
}