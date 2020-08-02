package com.magic.upcoming.games.service

import com.magic.upcoming.games.model.game.GameDetailModel
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.model.search.SearchResult
import com.magic.upcoming.games.model.translate.TranslateModel
import com.magic.upcoming.games.model.video.VideoModel
import com.magic.upcoming.games.network.response.BaseResponse
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by ThinkPad on 2017/11/2.
 */
interface GameApiService {
    @get:GET("ajax.php?a=fy&f=auto&t=auto&w=global%20world")
    val translate: Call<ResponseBody>

    @get:GET("ajax.php?a=fy&f=auto&t=auto&w=global%20world")
    val translateFlow: Flowable<BaseResponse<TranslateModel>>

    @GET("game/{guid}")
    fun getGameDetailData(
            @Path("guid") guid: String,
            @Query("field_list") fieldList: String,
            @Query("api_key") apiKey: String,
            @Query("format") format: String
    ): Flowable<BaseResponse<GameDetailModel>>

    @GET("games")
    fun getGameListData(
            @Query("offset") offset: Int,
            @Query("limit") limit: Int,
            @Query("sort") sort: String,
            @Query("filter") filter: String,
            @Query("field_list") fieldList: String,
            @Query("api_key") apiKey: String,
            @Query("format") format: String
    ): Flowable<BaseResponse<ArrayList<GameModel>>>

    @GET("search")
    fun getSearchListData(
            @Query("query") keyword: String,
            @Query("limit") limit: Int,
            @Query("page") page: Int,
            @Query("field_list") fieldList: String,
            @Query("resources") resources: String,
            @Query("api_key") apiKey: String,
            @Query("format") format: String
    ): Flowable<BaseResponse<ArrayList<SearchResult>>>

    @GET("videos")
    fun getVideoListData(
            @Query("offset") offset: Int,
            @Query("limit") limit: Int,
            @Query("sort") sort: String,
            @Query("field_list") fieldList: String,
            @Query("api_key") apiKey: String,
            @Query("format") format: String
    ): Flowable<BaseResponse<ArrayList<VideoModel>>>


}