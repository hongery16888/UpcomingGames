package com.magic.upcoming.games.viewmodel.filter

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.magic.upcoming.games.base.BaseViewModel
import com.magic.upcoming.games.constant.PlatformType
import com.magic.upcoming.games.constant.ReleaseDateType
import com.magic.upcoming.games.constant.SortDirection
import com.magic.upcoming.games.orm.OrmGameApi
import com.magic.upcoming.games.utilities.Event

class GameFilterViewModel : BaseViewModel() {

    private val _back = MutableLiveData<Event<Boolean>>()
    val back: LiveData<Event<Boolean>>
        get() = _back

    fun back(){
        _back.value = Event(true)
    }

    private val _Apply = MutableLiveData<Event<Boolean>>()
    val apply: LiveData<Event<Boolean>>
        get() = _Apply

    fun apply(){
        _Apply.value = Event(true)
    }

    var sortDirection:ObservableField<SortDirection> = ObservableField(enumValueOf<SortDirection>(OrmGameApi.gameFilterOptions?.sortDirectionName!!))
    var dateType : ObservableField<ReleaseDateType> = ObservableField(enumValueOf<ReleaseDateType>(OrmGameApi.gameFilterOptions?.releaseDateTypeName!!))
    var platformType: ObservableField<PlatformType> = ObservableField(enumValueOf<PlatformType>(OrmGameApi.gameFilterOptions?.platformTypeName!!))
    var startData: ObservableField<String> = ObservableField(OrmGameApi.gameFilterOptions?.releastStartData!!)
    var endData: ObservableField<String> = ObservableField(OrmGameApi.gameFilterOptions?.releastEndData!!)

    private val _updateDataType = MutableLiveData<Event<Boolean>>()
    val updateDataType: LiveData<Event<Boolean>>
        get() = _updateDataType

    fun onUpdateDataType(
            startDateError: String?,
            startDateText: String?,
            endDateError: String?,
            endDateText: String?
    ) {
        if (dateType.get() == ReleaseDateType.CustomDate) {
            if (startDateError == null && !startDateText.isNullOrBlank()
                    && endDateError == null && !endDateText.isNullOrBlank()
            ) {
                // Custom date range with valid date inputs.
                _updateDataType.value = Event(true)
            } else {
                // Custom date range with invalid date inputs.
                _updateDataType.value = Event(false)
            }
        } else {
            // If not using custom date range, inputs are always valid.
            _updateDataType.value = Event(true)
        }
    }
}