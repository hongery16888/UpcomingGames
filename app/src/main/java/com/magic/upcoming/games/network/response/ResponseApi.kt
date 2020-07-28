package com.magic.upcoming.games.network.response

import com.magic.upcoming.games.constant.SortDirection
import com.magic.upcoming.games.orm.OrmGameApi
import com.magic.upcoming.games.repository.api.ApiField

open class ResponseApi {
    companion object {
//        val gameDetailFieldList: String
//            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Name.field},${ApiField.Image.field}"

        val gameDetailFieldList: String
            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Name.field}," +
                    "${ApiField.Image.field},${ApiField.Images.field},${ApiField.Platforms.field}," +
                    "${ApiField.OriginalReleaseDate.field},${ApiField.ExpectedReleaseDay.field}," +
                    "${ApiField.ExpectedReleaseMonth.field},${ApiField.ExpectedReleaseYear.field}," +
                    "${ApiField.ExpectedReleaseQuarter.field},${ApiField.OriginalGameRating.field}," +
                    "${ApiField.Developers.field},${ApiField.Publishers.field}," +
                    "${ApiField.Genres.field},${ApiField.Deck.field},${ApiField.DetailUrl.field}"

        val gameListSort: String
            get() = "${ApiField.OriginalReleaseDate.field}:" + enumValueOf<SortDirection>(OrmGameApi.gameFilterOptions?.sortDirectionName!!).direction

        val gameListFilter: String
            get() = "${ApiField.OriginalReleaseDate.field}:${OrmGameApi.gameFilterOptions?.releaseStartDataNet}|${OrmGameApi.gameFilterOptions?.releaseEndDataNet}"

        val gameListFieldList: String
            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Name.field},${ApiField.Image.field},${ApiField.Platforms.field}," +
                    "${ApiField.OriginalReleaseDate.field},${ApiField.ExpectedReleaseDay.field},${ApiField.ExpectedReleaseMonth.field}," +
                    "${ApiField.ExpectedReleaseYear.field},${ApiField.ExpectedReleaseQuarter.field}"

        val videoListSort: String
            get() =  "${ApiField.OriginalReleaseDate.field}:${SortDirection.Descending.direction}"

        val videoListFieldList: String
            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Name.field},${ApiField.Image.field}," +
                    "${ApiField.VideoLengthSeconds.field},${ApiField.VideoPublishDate.field},${ApiField.DetailUrl.field}," +
                    "${ApiField.VideoUser.field},${ApiField.VideoYoutubeId.field},${ApiField.VideoType.field}," +
                    "${ApiField.Deck.field},${ApiField.VideoHDUrl.field},${ApiField.VideoHighUrl.field},${ApiField.VideoLowUrl.field}"
    }
}