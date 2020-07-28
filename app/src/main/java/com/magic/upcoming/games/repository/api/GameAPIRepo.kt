package com.magic.upcoming.games.repository.api

import com.magic.upcoming.games.constant.GameConts
import com.magic.upcoming.games.model.base.BaseModel
import com.magic.upcoming.games.model.game.GameDetailModel
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.model.search.SearchResult
import com.magic.upcoming.games.model.translate.TranslateModel
import com.magic.upcoming.games.network.response.ResponseApi
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Created by ThinkPad on 2017/11/2.
 */
interface GameAPIRepo {
    val translate: Call<ResponseBody>
    val translateFlow: Flowable<BaseModel<TranslateModel>>
    fun gameDetail(guid: String, fieldList:String = ResponseApi.gameDetailFieldList, apiKey:String = GameConts.GIANT_BOMB_API_KEY, format:String = "json" ): Flowable<BaseModel<GameDetailModel>>
    fun gameList(offset: Int, limit:Int, sort: String = ResponseApi.gameListSort, filter:String = ResponseApi.gameListFilter, fieldList:String = ResponseApi.gameListFieldList, apiKey:String = GameConts.GIANT_BOMB_API_KEY, format:String = "json" ): Flowable<BaseModel<ArrayList<GameModel>>>
    fun searchList(keyword: String, limit:Int = 50, page: Int = 0, fieldList:String = ResponseApi.gameListFieldList, resource: String = "game,video", apiKey:String = GameConts.GIANT_BOMB_API_KEY, format:String = "json" ): Flowable<BaseModel<ArrayList<SearchResult>>>
}