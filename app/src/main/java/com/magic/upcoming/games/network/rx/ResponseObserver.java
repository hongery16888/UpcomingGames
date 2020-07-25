package com.magic.upcoming.games.network.rx;

import android.util.Log;

import com.magic.upcoming.games.network.exception.ResponseFailedException;
import com.magic.upcoming.games.network.exception.RxError;
import com.magic.upcoming.games.network.response.ErrorCode;

import io.reactivex.subscribers.ResourceSubscriber;


/**
 * Created by shendawei on 17/2/8.
 */

public abstract class ResponseObserver<T> extends ResourceSubscriber <T> {
    @Override
    public void onComplete(){
    }

    @Override
    public void onError(Throwable e) {
        // log
        if (e instanceof ResponseFailedException) {
            onError(((ResponseFailedException) e).getRxError());
        } else {
            if(e!=null&&e.getMessage()!=null) {
                Log.e("responseOb", e.getMessage());
            }
            onError(new RxError(ErrorCode.NETWORK_ERROR,"网络出现问题"));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNext(T t) {
        try {
            onSuccess(t);
        }
        catch (Exception ex)
        {
            if(ex!=null&&ex.getMessage()!=null) {
                Log.e("rxerror", ex.getMessage());
            }
            onSuccess(null);
        }
        //onSuccess(t);
    }

    public abstract void onSuccess(T t);

    public abstract void onError(RxError rxError);
}