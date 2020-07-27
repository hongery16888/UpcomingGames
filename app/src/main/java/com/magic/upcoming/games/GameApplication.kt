package com.magic.upcoming.games

import com.magic.upcoming.games.base.BaseApplication
import com.magic.upcoming.games.orm.GameFilterOptions
import org.litepal.LitePal
import org.litepal.extension.find

class GameApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        LitePal.initialize(this)

        if (LitePal.find<GameFilterOptions>(1) == null)
            GameFilterOptions().save()
    }

    companion object {
        var instance: BaseApplication? = null
            private set
    }
}