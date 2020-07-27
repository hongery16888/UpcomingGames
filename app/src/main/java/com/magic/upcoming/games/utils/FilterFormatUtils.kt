package com.magic.upcoming.games.utils

import com.magic.upcoming.games.constant.PlatformType
import com.magic.upcoming.games.constant.ReleaseDateType
import com.magic.upcoming.games.constant.allKnownPlatforms
import com.magic.upcoming.games.constant.currentGenerationPlatformRange
import com.magic.upcoming.games.orm.GameFilterOptions
import com.magic.upcoming.games.orm.OrmGameApi
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

object FilterFormatUtils{

    fun fetchDateConstraints(dataType: ReleaseDateType) {
        var dateStartMillis: Long?
        var dateEndMillis: Long?

        val calendar: Calendar = Calendar.getInstance()
        // Make it so hour, minute, second, and millisecond don't affect the timeInMillis returned.
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)

        val currentTimeMillis = calendar.timeInMillis

        when (dataType) {
            ReleaseDateType.RecentAndUpcoming -> {
                // dateStartMillis is set to one week before current day.
                calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7)
                dateStartMillis = calendar.timeInMillis

                // dateEndMillis is set to a far-off date so that every future game will be listed.
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 100)
                dateEndMillis = calendar.timeInMillis
            }
            ReleaseDateType.Any -> {
                dateStartMillis = null
                dateEndMillis = null
            }
            ReleaseDateType.PastMonth -> {
                // dateStartMillis is set to one month before current day.
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
                dateStartMillis = calendar.timeInMillis

                // dateEndMillis is set to current time.
                dateEndMillis = currentTimeMillis
            }
            ReleaseDateType.PastYear -> {
                // dateStartMillis is set to one year before current day.
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1)
                dateStartMillis = calendar.timeInMillis

                // dateEndMillis is set to current time.
                dateEndMillis = currentTimeMillis
            }
            ReleaseDateType.CustomDate -> {
                val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.US)

                val startDateString = OrmGameApi.gameFilterOptions?.releastStartData
                calendar.time = formatter.parse(startDateString!!)!!
                dateStartMillis = calendar.timeInMillis

                val endDateString = OrmGameApi.gameFilterOptions?.releastEndData
                calendar.time = formatter.parse(endDateString!!)!!

                // To get the last millisecond of the day, we add a day and subtract a millisecond.
                calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1)
                calendar.set(Calendar.MILLISECOND, calendar.get(Calendar.MILLISECOND) - 1)

                dateEndMillis = calendar.timeInMillis

                // If the end date is before the start date, just flip them.
                if (dateEndMillis < dateStartMillis) {
                    val temp = dateStartMillis
                    dateStartMillis = dateEndMillis
                    dateEndMillis = temp
                }
            }
        }

        OrmGameApi.gameFilterOptions?.releastStartDataMills = dateStartMillis
        OrmGameApi.gameFilterOptions?.releastEndDataMills = dateEndMillis
    }

    fun fetchPlatformIndices(platformType: PlatformType): Set<Int> {
        val platformIndices = mutableSetOf<Int>()

        return when (platformType) {
            PlatformType.CurrentGeneration -> {
                platformIndices.apply {
                    addAll(currentGenerationPlatformRange)
                }
            }
            PlatformType.All -> platformIndices.apply { addAll(allKnownPlatforms.indices) }
            PlatformType.PickFromList -> platformIndices.apply { addAll(OrmGameApi.gameFilterOptions?.platformIndices!!) }
        }
    }
}