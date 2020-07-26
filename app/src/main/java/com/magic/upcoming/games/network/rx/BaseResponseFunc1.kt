package com.magic.upcoming.games.network.rx

import com.magic.upcoming.games.model.BaseModel
import com.magic.upcoming.games.network.exception.ResponseFailedException
import com.magic.upcoming.games.network.response.BaseResponse
import io.reactivex.Flowable
import io.reactivex.functions.Function

class BaseResponseFunc1<T : BaseModel?> : Function<BaseResponse<T>, Flowable<T>> {
    @Throws(Exception::class)
    override fun apply(response: BaseResponse<T>): Flowable<T> {
        println("------------------>${response.results.toString()}")
        return when(response.status_code){
            100 -> Flowable.error(ResponseFailedException("Invalid API Key"))
            101 -> Flowable.error(ResponseFailedException("Object Not Found"))
            102 -> Flowable.error(ResponseFailedException("Error in URL Format"))
            103 -> Flowable.error(ResponseFailedException("'jsonp' format requires a 'json_callback' argument"))
            104 -> Flowable.error(ResponseFailedException("Filter Error"))
            105 -> Flowable.error(ResponseFailedException("Subscriber only video is for subscribers only"))
            1 -> {
                response.results?.limit = response.limit
                response.results?.offset = response.offset
                response.results?.resultsNum = response.number_of_page_results
                response.results?.totalResults = response.number_of_total_results
                Flowable.just(response.results)
            }
            else -> Flowable.error(ResponseFailedException("Network Error"))
        }
    }
}