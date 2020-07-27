package com.magic.upcoming.games

import com.magic.upcoming.games.base.BaseApplication
import com.magic.upcoming.games.constant.PlatformType
import com.magic.upcoming.games.constant.ReleaseDateType
import com.magic.upcoming.games.constant.SortDirection
import com.magic.upcoming.games.orm.GameFilterOptions
import com.magic.upcoming.games.utils.FilterFormatUtils
import org.litepal.LitePal
import org.litepal.extension.find

class GameApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        LitePal.initialize(this)

        if (LitePal.find<GameFilterOptions>(1) == null) {
            val gameFilterOptions = GameFilterOptions().apply {
                sortDirectionName = SortDirection.Ascending.name
                releaseDateTypeName = ReleaseDateType.RecentAndUpcoming.name
                platformTypeName = PlatformType.CurrentGeneration.name
            }
            gameFilterOptions.save().let {
                        FilterFormatUtils.fetchDateConstraints(ReleaseDateType.RecentAndUpcoming, true)
                    }
            gameFilterOptions.save()
        }
    }

    companion object {
        var instance: BaseApplication? = null
            private set
    }
}