package com.magic.upcoming.games.repository.imp;

import com.magic.upcoming.games.model.translate.TranslateModel;
import com.magic.upcoming.games.network.RetrofitBuilder;
import com.magic.upcoming.games.network.rx.BaseResponseFunc1;
import com.magic.upcoming.games.repository.api.TranslateRepo;
import com.magic.upcoming.games.service.TranslateService;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;


/**
 * Created by ThinkPad on 2017/11/2.
 */
public class TranslateRepoImp implements TranslateRepo {

    private static TranslateRepoImp instance;

    public static TranslateRepoImp getInstance(){
        if (instance == null)
            instance = new TranslateRepoImp();
        return instance;
    }

    RetrofitBuilder mRetrofitBuilder = RetrofitBuilder.getInstance();
    private TranslateService mTranslateService;

    TranslateService initService() {
        if (mTranslateService == null)
            mTranslateService = mRetrofitBuilder.build().create(TranslateService.class);
        return mTranslateService;
    }

    @Override
    public Call <ResponseBody> getTranslate() {
        return initService().getTranslate();
    }
}
