package com.magic.upcoming.games.repository.api;

import com.magic.upcoming.games.model.translate.TranslateModel;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public interface TranslateRepo {

    Call<ResponseBody> getTranslate();

}
