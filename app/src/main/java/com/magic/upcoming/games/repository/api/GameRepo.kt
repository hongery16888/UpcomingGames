package com.magic.upcoming.games.repository.api

import com.magic.upcoming.games.constant.GameConts
import com.magic.upcoming.games.model.base.BaseModel
import com.magic.upcoming.games.model.game.GameDetailModel
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.model.translate.TranslateModel
import com.magic.upcoming.games.network.response.BaseResponse
import com.magic.upcoming.games.network.response.ResponseApi
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Created by ThinkPad on 2017/11/2.
 */
interface GameRepo {
    val translate: Call<ResponseBody>
    val translateFlow: Flowable<BaseModel<TranslateModel>>
    fun gameDetail(guid: String, fieldList:String = ResponseApi.gameDetailFieldList, apiKey:String = GameConts.GIANT_BOMB_API_KEY, format:String = "json" ): Flowable<BaseModel<GameDetailModel>>
    fun gameList(offset: Int = 0, limit:Int = 5, sort: String = ResponseApi.gameListSort, filter:String = ResponseApi.gameListFilter, fieldList:String = ResponseApi.gameListFieldList, apiKey:String = GameConts.GIANT_BOMB_API_KEY, format:String = "json" ): Flowable<BaseModel<ArrayList<GameModel>>>
}