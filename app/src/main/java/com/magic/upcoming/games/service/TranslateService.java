package com.magic.upcoming.games.service;

import com.magic.upcoming.games.model.translate.TranslateModel;
import com.magic.upcoming.games.network.response.BaseResponse;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public interface TranslateService {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=global%20world")
    Call<ResponseBody> getTranslate();

}
