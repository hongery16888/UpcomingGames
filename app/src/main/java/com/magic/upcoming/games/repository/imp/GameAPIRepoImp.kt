package com.magic.upcoming.games.repository.imp

import com.magic.upcoming.games.model.base.BaseModel
import com.magic.upcoming.games.model.game.GameDetailModel
import com.magic.upcoming.games.model.game.GameModel
import com.magic.upcoming.games.model.search.SearchResult
import com.magic.upcoming.games.model.translate.TranslateModel
import com.magic.upcoming.games.model.video.VideoModel
import com.magic.upcoming.games.network.RetrofitBuilder
import com.magic.upcoming.games.network.rx.BaseResponseFunc1
import com.magic.upcoming.games.repository.api.GameAPIRepo
import com.magic.upcoming.games.service.GameApiService
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Created by ThinkPad on 2017/11/2.
 */
class GameAPIRepoImp : GameAPIRepo {
    private var mRetrofitBuilder: RetrofitBuilder = RetrofitBuilder.getInstance()
    private var mGameApiService: GameApiService? = null
    private fun initService(): GameApiService? {
        if (mGameApiService == null) mGameApiService = mRetrofitBuilder.build().create(GameApiService::class.java)
        return mGameApiService
    }

    override val translate: Call<ResponseBody>
        get() = initService()!!.translate

    override val translateFlow: Flowable<BaseModel<TranslateModel>>
        get() = initService()!!.translateFlow.flatMap(BaseResponseFunc1())

    override fun gameDetail(guid: String, fieldList:String, apiKey:String, format:String): Flowable<BaseModel<GameDetailModel>> {
        return initService()!!.getGameDetailData(guid, fieldList, apiKey, format).flatMap(BaseResponseFunc1())
    }

    override fun gameList(offset: Int, limit:Int, sort: String, filter: String, fieldList: String, apiKey: String, format: String): Flowable<BaseModel<ArrayList<GameModel>>> {
        return initService()!!.getGameListData(offset, limit, sort, filter, fieldList, apiKey, format).flatMap(BaseResponseFunc1())
    }

    override fun searchList(keyword:String, limit: Int, page: Int, fieldList: String, resource: String, apiKey: String, format: String): Flowable<BaseModel<ArrayList<SearchResult>>> {
        return initService()!!.getSearchListData(keyword, limit, page, fieldList, resource, apiKey, format).flatMap(BaseResponseFunc1())
    }

    override fun videoList(offset: Int, limit: Int, sort: String, fieldList: String, apiKey: String, format: String): Flowable<BaseModel<ArrayList<VideoModel>>> {
        return initService()!!.getVideoListData(offset, limit, sort, fieldList, apiKey, format).flatMap(BaseResponseFunc1())
    }

    companion object {
        @JvmStatic
        var instance: GameAPIRepoImp? = null
            get() {
                if (field == null) field = GameAPIRepoImp()
                return field
            }
            private set
    }
}