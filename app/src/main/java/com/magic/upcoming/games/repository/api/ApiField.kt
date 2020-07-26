package com.magic.upcoming.games.repository.api

enum class ApiField(val field: String) {
    Json("json"),
    Id("id"),
    Guid("guid"),
    Name("name"),
    Image("image"),
    Images("images"),
    Platforms("platforms"),
    OriginalReleaseDate("original_release_date"),
    ExpectedReleaseDay("expected_release_day"),
    ExpectedReleaseMonth("expected_release_month"),
    ExpectedReleaseYear("expected_release_year"),
    ExpectedReleaseQuarter("expected_release_quarter"),
    OriginalGameRating("original_game_rating"),
    Developers("developers"),
    Publishers("publishers"),
    Genres("genres"),
    Deck("deck"),
    DetailUrl("site_detail_url"),
    DateLastUpdated("date_last_updated")
}