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
                    "${ApiField.Developers.field},${ApiField.Publishers.field},${ApiField.Description.field}," +
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
                    "${ApiField.VideoLengthSeconds.field},${ApiField.PublishDate.field},${ApiField.DetailUrl.field}," +
                    "${ApiField.VideoUser.field},${ApiField.VideoYoutubeId.field},${ApiField.VideoType.field},${ApiField.EmbedPlayer.field}," +
                    "${ApiField.Deck.field},${ApiField.VideoHDUrl.field},${ApiField.VideoHighUrl.field},${ApiField.VideoLowUrl.field}"

        val reviewListSort: String
            get() =  "${ApiField.PublishDate.field}:${SortDirection.Descending.direction}"

        val reviewListFieldList: String
            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Deck.field},${ApiField.Game.field},${ApiField.Platforms.field}," +
                    "${ApiField.Description.field},${ApiField.DetailUrl.field},${ApiField.PublishDate.field}," +
                    "${ApiField.Score.field},${ApiField.Reviewer.field}"

        val companyListSort: String
            get() =  "${ApiField.PublishDate.field}:${SortDirection.Descending.direction}"

        val companyListFieldList: String
            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Deck.field},${ApiField.Game.field},${ApiField.Name.field}," +
                    "${ApiField.Description.field},${ApiField.DateLastUpdated.field},${ApiField.Abbreviation.field},${ApiField.Aliases.field}," +
                    "${ApiField.DateAdded.field},${ApiField.DateFounded.field},${ApiField.LocationAddress.field}," +
                    "${ApiField.LocationCity.field},${ApiField.LocationCountry.field},${ApiField.LocationState.field}," +
                    "${ApiField.Phone.field},${ApiField.SiteDetailUrl.field},${ApiField.Website.field},${ApiField.Image.field}"
    }
}