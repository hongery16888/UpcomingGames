package com.magic.upcoming.games

import com.magic.upcoming.games.base.BaseApplication
import com.magic.upcoming.games.constant.PlatformType
import com.magic.upcoming.games.constant.ReleaseDateType
import com.magic.upcoming.games.constant.SortDirection
import com.magic.upcoming.games.model.game.GameImage
import com.magic.upcoming.games.model.video.VideoModel
import com.magic.upcoming.games.orm.GameFilterOptions
import com.magic.upcoming.games.orm.LitePalDB
import com.magic.upcoming.games.utils.FilterFormatUtils
import org.litepal.LitePal
import org.litepal.extension.find

open class GameApplication : BaseApplication() {

    var gameImages: ArrayList<GameImage>? = null
    var videoModel: VideoModel? = null

    override fun onCreate() {
        super.onCreate()

        instance = this

        LitePalDB.initDB(this)
    }

    companion object {
        var instance: GameApplication? = null
            private set
    }
}