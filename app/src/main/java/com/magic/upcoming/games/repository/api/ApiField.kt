package com.magic.upcoming.games.repository.api

enum class ApiField(val field: String) {
    Json("json"),
    Id("id"),
    Guid("guid"),
    Name("name"),
    Image("image"),
    Images("images"),
    Platforms("platforms"),
    EmbedPlayer("embed_player"),
    OriginalReleaseDate("original_release_date"),
    ExpectedReleaseDay("expected_release_day"),
    ExpectedReleaseMonth("expected_release_month"),
    ExpectedReleaseYear("expected_release_year"),
    ExpectedReleaseQuarter("expected_release_quarter"),
    OriginalGameRating("original_game_rating"),
    Developers("developers"),
    Publishers("publishers"),
    Genres("genres"),
    Game("game"),
    Deck("deck"),
    Score("score"),
    Reviewer("reviewer"),
    Description("description"),
    DetailUrl("site_detail_url"),
    DateLastUpdated("date_last_updated"),
    VideoLengthSeconds("length_seconds"),
    PublishDate("publish_date"),
    VideoUser("user"),
    VideoYoutubeId("youtube_id"),
    VideoType("video_type"),
    VideoHDUrl("hd_url"),
    VideoHighUrl("high_url"),
    VideoLowUrl("low_url"),
    Abbreviation("abbreviation"),
    Aliases("aliases"),
    DateAdded("date_added"),
    DateFounded("date_founded"),
    LocationAddress("location_address"),
    LocationCity("location_city"),
    LocationCountry("location_country"),
    LocationState("location_state"),
    Phone("phone"),
    SiteDetailUrl("site_detail_url"),
    Website("website")
}