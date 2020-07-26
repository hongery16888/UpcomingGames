package com.magic.upcoming.games.network.response

import com.magic.upcoming.games.repository.api.ApiField

open class ResponseApi {
    companion object {
        val gameDetailFieldList: String
            get() = "${ApiField.Id.field},${ApiField.Guid.field},${ApiField.Name.field},${ApiField.Image.field}"
    }
}