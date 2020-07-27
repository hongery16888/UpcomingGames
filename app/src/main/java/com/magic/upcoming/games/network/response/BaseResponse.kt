package com.magic.upcoming.games.network.response

import com.google.gson.annotations.SerializedName

class BaseResponse<T> {
    @SerializedName("status_code")
    var status_code = 0
    var results: T? = null
        private set
    var limit = 0
    var number_of_page_results = 0
    var number_of_total_results = 0
    var error: String? = null
    var offset = 0
    var version: String? = null
    fun setResults(results: T) {
        println("------------------>Result : " + results.toString())
        this.results = results
    }

}