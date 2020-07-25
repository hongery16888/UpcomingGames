package com.magic.upcoming.games.network.rx;

import android.util.Log;

import com.magic.upcoming.games.network.exception.ResponseFailedException;
import com.magic.upcoming.games.network.response.BaseResponse;
import com.magic.upcoming.games.network.response.ErrorCode;
import com.magic.upcoming.games.network.response.ResponseCode;
import com.magic.upcoming.games.network.response.RxVoid;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by shendawei on 17/2/8.
 */

public class BaseResponseFunc1<T> implements Function <BaseResponse <T>, Flowable <T>> {

    @Override
    public Flowable <T> apply(@NonNull final BaseResponse<T> response) throws Exception {

        if (response == null) {
            return Flowable.error(new ResponseFailedException(ErrorCode.NETWORK_ERROR,"网络出现问题1"));
        }
        Log.d("BaseResponse", "response = " + response);

        //如果请求操作成功且发射数据为空，则发送rxvoid
//        if(response.getStatus()==ResponseCode.SUCCESS&&response.getContent()==null)
//        {
//            return Flowable.just((T) new RxVoid());
//        }
        if(response.getContent()!=null)
        {
            return Flowable.just(response.getContent());
        }
        return Flowable.error(new ResponseFailedException(ErrorCode.NETWORK_ERROR,"网络出现问题2"));

    }
}
