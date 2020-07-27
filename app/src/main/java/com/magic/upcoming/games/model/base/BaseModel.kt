package com.magic.upcoming.games.model.base

open class BaseModel<T> {
    var limit = 0
    var resultsNum = 0
    var totalResults = 0
    var offset = 0
    var result:T? = null
}