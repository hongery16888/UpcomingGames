package com.magic.upcoming.games.model.game

import com.magic.upcoming.games.model.BaseModel

open class GameDetailModel : BaseModel() {
    var id: Long = 0
    var guid: String? = null
    var name: String? = null
}