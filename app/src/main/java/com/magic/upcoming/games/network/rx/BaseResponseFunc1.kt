package com.magic.upcoming.games.network.rx

import com.magic.upcoming.games.model.base.BaseModel
import com.magic.upcoming.games.network.exception.ResponseFailedException
import com.magic.upcoming.games.network.response.BaseResponse
import io.reactivex.Flowable
import io.reactivex.functions.Function

class BaseResponseFunc1<T> : Function<BaseResponse<T>, Flowable<BaseModel<T>>> {
    @Throws(Exception::class)
    override fun apply(response: BaseResponse<T>): Flowable<BaseModel<T>> {
        return when(response.statusCode){
            100 -> Flowable.error(ResponseFailedException("Invalid API Key"))
            101 -> Flowable.error(ResponseFailedException("Object Not Found"))
            102 -> Flowable.error(ResponseFailedException("Error in URL Format"))
            103 -> Flowable.error(ResponseFailedException("'jsonp' format requires a 'json_callback' argument"))
            104 -> Flowable.error(ResponseFailedException("Filter Error"))
            105 -> Flowable.error(ResponseFailedException("Subscriber only video is for subscribers only"))
            1 ->{
                val baseModel = BaseModel<T>()
                baseModel.result = response.results
                baseModel.limit = response.limit
                baseModel.offset = response.offset
                baseModel.resultsNum = response.numberOfPageResults
                baseModel.totalResults = response.numberOfTotalResults
                Flowable.just(baseModel)
            }
            else -> Flowable.error(ResponseFailedException("Network Error"))
        }
    }
}