package com.magic.upcoming.games.utilities

import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.magic.upcoming.games.R
import com.magic.upcoming.games.constant.PlatformType
import com.magic.upcoming.games.constant.ReleaseDateType
import com.magic.upcoming.games.constant.SortDirection
import com.magic.upcoming.games.orm.OrmGameApi

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
    println("------------------>ReleaseDateType : $dateType")
    val newCheckedId = when (dateType) {
        ReleaseDateType.RecentAndUpcoming -> R.id.new_and_upcoming_releases_radioButton
        ReleaseDateType.PastMonth -> R.id.past_month_radioButton
        ReleaseDateType.PastYear -> R.id.this_year_radioButton
        ReleaseDateType.Any -> R.id.any_release_date_radioButton
        ReleaseDateType.CustomDate -> R.id.custom_date_range_radioButton
    }

    OrmGameApi.gameFilterOptions?.releaseDateTypeName = dateType.name

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
    println("------------------>PlatformType : $platformType")
    val newCheckedId = when (platformType) {
        PlatformType.CurrentGeneration -> R.id.current_generation_radioButton
        PlatformType.All -> R.id.all_platforms_radioButton
        PlatformType.PickFromList -> R.id.custom_platforms_radioButton
    }

    OrmGameApi.gameFilterOptions?.platformTypeName = platformType.name

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