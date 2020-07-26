package com.magic.upcoming.games.repository.imp

import com.magic.upcoming.games.conts.GameConts
import com.magic.upcoming.games.model.game.GameDetailModel
import com.magic.upcoming.games.model.translate.TranslateModel
import com.magic.upcoming.games.network.RetrofitBuilder
import com.magic.upcoming.games.network.rx.BaseResponseFunc1
import com.magic.upcoming.games.repository.api.GameRepo
import com.magic.upcoming.games.service.GameApiService
import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.Call

/**
 * Created by ThinkPad on 2017/11/2.
 */
class GameRepoImp : GameRepo {
    var mRetrofitBuilder: RetrofitBuilder = RetrofitBuilder.getInstance()
    private var mGameApiService: GameApiService? = null
    private fun initService(): GameApiService? {
        if (mGameApiService == null) mGameApiService = mRetrofitBuilder.build().create(GameApiService::class.java)
        return mGameApiService
    }

    override val translate: Call<ResponseBody>
        get() = initService()!!.translate

    override val translateFlow: Flowable<TranslateModel>
        get() = initService()!!.translateFlow.flatMap(BaseResponseFunc1())

    override fun gameDetail(guid: String, fieldList:String, apiKey:String, format:String): Flowable<GameDetailModel> {
        println("------------------>$guid  :  $fieldList  :  $apiKey  :  $format")
        return initService()!!.getGameDetailData(guid, fieldList, apiKey, format).flatMap(BaseResponseFunc1())
    }

    companion object {
        @JvmStatic
        var instance: GameRepoImp? = null
            get() {
                if (field == null) field = GameRepoImp()
                return field
            }
            private set
    }
}