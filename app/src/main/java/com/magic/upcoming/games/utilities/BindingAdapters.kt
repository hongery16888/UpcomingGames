package com.magic.upcoming.games.utilities

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.magic.upcoming.games.R
import com.magic.upcoming.games.activity.game.GameDetailActivity
import com.magic.upcoming.games.constant.*
import com.magic.upcoming.games.model.game.GameGeneric
import com.magic.upcoming.games.model.game.GamePlatform
import com.magic.upcoming.games.model.game.GameRating
import com.magic.upcoming.games.orm.OrmGameApi
import com.magic.upcoming.games.utils.FilterFormatUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@BindingAdapter("sortDirection")
fun RadioGroup.bindSortDirection(sortDirection: SortDirection) {
    println("------------------>sortDirection : $sortDirection")
    val newCheckedId = when (sortDirection) {
        SortDirection.Ascending -> R.id.sort_ascending_radioButton
        SortDirection.Descending -> R.id.sort_descending_radioButton
    }

    OrmGameApi.gameFilterOptions?.sortDirectionName = sortDirection.name

    // Prevent infinite loops
    if (checkedRadioButtonId != newCheckedId) {
        check(newCheckedId)
    }
}

@InverseBindingAdapter(attribute = "sortDirection")
fun RadioGroup.setSortDirection(): SortDirection {
    println("------------------>checkedRadioButtonId : $checkedRadioButtonId")
    return when (checkedRadioButtonId) {
        R.id.sort_ascending_radioButton -> SortDirection.Ascending
        else -> SortDirection.Descending
    }
}

@BindingAdapter("sortDirectionAttrChanged")
fun RadioGroup.setSortDirectionListeners(listener: InverseBindingListener) {
    setOnCheckedChangeListener { _, _ ->
        listener.onChange()
    }
}


@BindingAdapter("releaseDateType")
fun RadioGroup.bindReleaseDateType(dateType: ReleaseDateType) {
    val newCheckedId = when (dateType) {
        ReleaseDateType.RecentAndUpcoming -> R.id.new_and_upcoming_releases_radioButton
        ReleaseDateType.PastMonth -> R.id.past_month_radioButton
        ReleaseDateType.PastYear -> R.id.this_year_radioButton
        ReleaseDateType.Any -> R.id.any_release_date_radioButton
        ReleaseDateType.CustomDate -> R.id.custom_date_range_radioButton
    }

    OrmGameApi.gameFilterOptions?.releaseDateTypeName = dateType.name
    FilterFormatUtils.fetchDateConstraints(dateType)

    // Prevent infinite loops
    if (checkedRadioButtonId != newCheckedId) {
        check(newCheckedId)
    }
}

@InverseBindingAdapter(attribute = "releaseDateType")
fun RadioGroup.setReleaseDateType(): ReleaseDateType {
    return when (checkedRadioButtonId) {
        R.id.new_and_upcoming_releases_radioButton -> ReleaseDateType.RecentAndUpcoming
        R.id.past_month_radioButton -> ReleaseDateType.PastMonth
        R.id.this_year_radioButton -> ReleaseDateType.PastYear
        R.id.any_release_date_radioButton -> ReleaseDateType.Any
        else -> ReleaseDateType.CustomDate
    }
}

// This notifies the data binding system that the attribute value has changed.
@BindingAdapter("releaseDateTypeAttrChanged")
fun RadioGroup.setReleaseDateTypeListeners(listener: InverseBindingListener) {
    setOnCheckedChangeListener { _, _ ->
        listener.onChange()
    }
}

@BindingAdapter("platformType")
fun RadioGroup.bindPlatformType(platformType: PlatformType) {
    val newCheckedId = when (platformType) {
        PlatformType.CurrentGeneration -> R.id.current_generation_radioButton
        PlatformType.All -> R.id.all_platforms_radioButton
        PlatformType.PickFromList -> R.id.custom_platforms_radioButton
    }

    OrmGameApi.gameFilterOptions?.platformTypeName = platformType.name
    FilterFormatUtils.fetchPlatformIndices(platformType)

    // Prevent infinite loops
    if (checkedRadioButtonId != newCheckedId) {
        check(newCheckedId)
    }
}

@InverseBindingAdapter(attribute = "platformType")
fun RadioGroup.setPlatformType(): PlatformType {
    return when (checkedRadioButtonId) {
        R.id.current_generation_radioButton -> PlatformType.CurrentGeneration
        R.id.all_platforms_radioButton -> PlatformType.All
        else -> PlatformType.PickFromList
    }
}

// This notifies the data binding system that the attribute value has changed.
@BindingAdapter("platformTypeAttrChanged")
fun RadioGroup.setPlatformTypeListeners(listener: InverseBindingListener) {
    setOnCheckedChangeListener { _, _ ->
        listener.onChange()
    }
}

@BindingAdapter("imageUrl")
fun ImageView.bindImage(imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(context)
                .load(if (imgUrl.contains(NO_IMG_FILE_NAME)) R.drawable.broken_image else imgUri)
                .fitCenter()
                .apply(
                        RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.broken_image)
                )
                .into(this)
    }
}

@BindingAdapter("imageTypeIcon")
fun ImageView.bindImageTypeIcon(type: String?) {
    val imageUrl = when (type) {
        IconType.Video.type -> R.drawable.ic_video
        else -> R.drawable.ic_game
    }

    Glide.with(context)
            .load(imageUrl)
            .fitCenter()
            .apply(
                    RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.broken_image)
            )
            .into(this)
}

@BindingAdapter("avatar")
fun ImageView.bindAvatarImage(img: String?) {
    val random = Random()
    Glide.with(context)
            .load(allDefaultAvatar[random.nextInt(20)])
            .fitCenter()
            .apply(
                    RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.broken_image)
            )
            .into(this)
}

@BindingAdapter("releaseDateInMillis", "dateFormat", "inGameDetailFragment")
fun TextView.formatReleaseDateString(
        releaseDateInMillis: Long?,
        dateFormat: Int,
        inGameDetailFragment: Boolean
) {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = releaseDateInMillis ?: -1

    // Depending on dateFormat, we format release date string differently.
    when (dateFormat) {
        DateFormat.Exact.formatCode -> {
            val desiredPatternFormatter = SimpleDateFormat("MMMM d, yyyy", Locale.US)
            text = desiredPatternFormatter.format(calendar.time)
        }
        DateFormat.Month.formatCode -> {
            val desiredPatternFormatter = SimpleDateFormat("MMMM yyyy", Locale.US)
            text = desiredPatternFormatter.format(calendar.time)
        }
        DateFormat.Quarter.formatCode -> {
            val quarter = (calendar.get(Calendar.MONTH) / 3) + 1
            val desiredPatternFormatter = SimpleDateFormat("yyyy", Locale.US)
            text = context.getString(
                    R.string.quarter_release_date,
                    quarter,
                    desiredPatternFormatter.format(calendar.time)
            )
        }
        DateFormat.Year.formatCode -> {
            val desiredPatternFormatter = SimpleDateFormat("yyyy", Locale.US)
            text = desiredPatternFormatter.format(calendar.time)
        }
        DateFormat.None.formatCode -> {
            text = if (inGameDetailFragment) {
                context.getString(R.string.not_available)
            } else {
                context.getString(R.string.no_release_date)
            }
        }
    }
}

@BindingAdapter("publishDate")
fun TextView.formatPublishDateString(publishDate: String?){
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = releaseDateInMillis ?: -1
}

fun fetchReleaseDateInMillis(
        originalReleaseDate: String?, expectedReleaseYear: Int?, expectedReleaseQuarter: Int?,
        expectedReleaseMonth: Int?, expectedReleaseDay: Int?
): List<Any?> {
    val calendar: Calendar = Calendar.getInstance()
    /**
     * When we call Calendar.getInstance(), this sets values for hours, minutes, seconds, and
     * milliseconds that we don't want. We want to create a Calendar object that contains only
     * the day, month, and year. So setting timeInMillis = 0 makes it so the current time doesn't
     * affect the timeInMillis value we're ultimately returning.
     */
    calendar.timeInMillis = 0

    if (originalReleaseDate != null) {
        val apiPatternFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = apiPatternFormatter.parse(originalReleaseDate)
        calendar.time = date!!
        return listOf(calendar.timeInMillis, DateFormat.Exact)
    }

    var releaseMonth = expectedReleaseMonth?.minus(1)
    var releaseDay = expectedReleaseDay

    when {
        releaseDay != null -> {
            calendar.set(expectedReleaseYear!!, releaseMonth!!, releaseDay)
            return listOf(calendar.timeInMillis, DateFormat.Exact)
        }
        releaseMonth != null -> {
            // Temporarily set day to 1
            calendar.set(expectedReleaseYear!!, releaseMonth, 1)
            // Find maximum day in month
            releaseDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            // Re-set calendar with maximum day
            calendar.set(expectedReleaseYear, releaseMonth, releaseDay)
            return listOf(calendar.timeInMillis, DateFormat.Month)
        }
        expectedReleaseQuarter != null -> {
            releaseMonth = (expectedReleaseQuarter * 3) - 1
            calendar.set(expectedReleaseYear!!, releaseMonth, 1)
            releaseDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            calendar.set(expectedReleaseYear, releaseMonth, releaseDay)
            return listOf(calendar.timeInMillis, DateFormat.Quarter)
        }
        expectedReleaseYear != null -> {
            calendar.set(expectedReleaseYear, 11, 31)
            return listOf(calendar.timeInMillis, DateFormat.Year)
        }
        else -> return listOf(null, DateFormat.None)
    }
}

@BindingAdapter("gameDetailList")
fun TextView.bindGameDetailList(items: ArrayList<String>?) {
    text = if (items != null) {
        val builder = StringBuilder()
        for (item in items) {
            builder.append(item).append("\n")
        }
        builder.trim()
    } else {
        context.getString(R.string.not_available)
    }
}

@BindingAdapter("gameDetailGenresList")
fun TextView.bindGameDetailGenresList(items: ArrayList<GameGeneric>?) {
    text = if (items != null) {
        val builder = StringBuilder()
        for (item in items) {
            builder.append(item.genericName).append("\n")
        }
        builder.trim()
    } else {
        context.getString(R.string.not_available)
    }
}

@BindingAdapter("gameDetailGameRatingList")
fun TextView.bindGameDetailGameRatingList(items: ArrayList<GameRating>?) {
    text = if (items != null) {
        val builder = StringBuilder()
        for (item in items) {
            builder.append(item.ratingName).append("\n")
        }
        builder.trim()
    } else {
        context.getString(R.string.not_available)
    }
}

@BindingAdapter("gameDetailPlatformList")
fun TextView.bindGameDetailPlatformList(items: ArrayList<GamePlatform>?) {
    text = if (items != null) {
        val builder = StringBuilder()
        for (item in items) {
            builder.append(item.platformName).append("\n")
        }
        builder.trim()
    } else {
        context.getString(R.string.not_available)
    }
}

@BindingAdapter("onGameClick")
fun View.gameItemClicked(guid: String?) {
    context.startActivity(Intent(context, GameDetailActivity::class.java).apply { putExtra("guid", guid) })
}

@BindingAdapter("gameDetailProgressBarVisibility")
fun ContentLoadingProgressBar.bindGameDetailProgressBarVisibility(detailNetworkState: NetworkState) {
    when (detailNetworkState) {
        NetworkState.Loading -> show()
        else -> hide()
    }
}
