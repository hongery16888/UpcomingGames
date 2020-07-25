package com.magic.upcoming.games

import com.magic.upcoming.games.base.BaseApplication
import org.litepal.LitePal

class GameApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        LitePal.initialize(this);
    }

    companion object {
        var instance: BaseApplication? = null
            private set
    }
}