package com.magic.upcoming.games.network.response

import com.magic.upcoming.games.constant.SortDirection
import com.magic.upcoming.games.orm.OrmGameApi
import com.magic.upcoming.games.repository.api.ApiField

open class ResponseApi {
    companion object {
        val gameDetailFieldList: String
            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Name.field},${ApiField.Image.field}"

        val gameListSort: String
            get() = "${ApiField.OriginalReleaseDate.field}:" + enumValueOf<SortDirection>(OrmGameApi.gameFilterOptions?.sortDirectionName!!).direction + ","

        val gameListFilter: String
            get() = "${ApiField.OriginalReleaseDate.field}:${OrmGameApi.gameFilterOptions?.releaseStartDataNet}|${OrmGameApi.gameFilterOptions?.releaseEndDataNet}"

        val gameListFieldList: String
            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Name.field}"
//        val gameListFieldList: String
//            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Name.field}," + "${ApiField.Image.field},${ApiField.Platforms.field}," +
//                    "${ApiField.OriginalReleaseDate.field},${ApiField.ExpectedReleaseDay.field},${ApiField.ExpectedReleaseMonth}," +
//                    "${ApiField.ExpectedReleaseYear},${ApiField.ExpectedReleaseQuarter.field}"
    }
}