package com.magic.upcoming.games.network.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("status_code")
    var statusCode = 0
    var results: T? = null
        private set
    var limit = 0
    @SerializedName("number_of_page_results")
    var numberOfPageResults = 0
    @SerializedName("number_of_total_results")
    var numberOfTotalResults = 0
    var error: String? = null
    var offset = 0
    var version: String? = null
    fun setResults(results: T) {
        this.results = results
    }

}