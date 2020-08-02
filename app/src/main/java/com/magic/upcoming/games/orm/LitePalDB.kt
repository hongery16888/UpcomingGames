package com.magic.upcoming.games.orm

import android.content.Context
import com.magic.upcoming.games.constant.PlatformType
import com.magic.upcoming.games.constant.ReleaseDateType
import com.magic.upcoming.games.constant.SortDirection
import com.magic.upcoming.games.utils.FilterFormatUtils
import org.litepal.LitePal
import org.litepal.LitePal.initialize
import org.litepal.extension.find
import java.util.*

object LitePalDB {
    fun initDB(context: Context?) {
        initialize(context!!)

        if (LitePal.find<GameFilterOptions>(1) == null) {
            GameFilterOptions().apply {
                sortDirectionName = SortDirection.Descending.name
                releaseDateTypeName = ReleaseDateType.RecentAndUpcoming.name
                platformTypeName = PlatformType.CurrentGeneration.name

                val calendar: Calendar = Calendar.getInstance()
                // Make it so hour, minute, second, and millisecond don't affect the timeInMillis returned.
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)

                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7)
                releaseStartDataNet = FilterFormatUtils.formatGameListData(calendar.timeInMillis)

                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 100)
                releaseEndDataNet = FilterFormatUtils.formatGameListData(calendar.timeInMillis)
            }.save()
        }
    }
}