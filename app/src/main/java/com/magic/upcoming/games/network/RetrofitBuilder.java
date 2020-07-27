package com.magic.upcoming.games.network;

import android.os.Environment;

import com.androidnetworking.interceptors.HttpLoggingInterceptor;
import com.magic.upcoming.games.constant.GameConts;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shendawei on 17/2/8.
 */
public class RetrofitBuilder {

    private static RetrofitBuilder instance;

    public static RetrofitBuilder getInstance(){
        if (instance == null){
            instance = new RetrofitBuilder();
        }
        return instance;
    }

    private static final long TIMEOUT_CONNECT = 30 * 1000;
    private static Retrofit mRetrofit;

    public Retrofit build() {
        if (mRetrofit == null) {
            File cacheDirectory = new File(Environment.getExternalStorageDirectory(), "/UpcomingGame/cache");
            long maxSize = 1024 * 1024 * 1024 ;
            Cache cache = new Cache(cacheDirectory, maxSize);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .connectTimeout(TIMEOUT_CONNECT, TimeUnit.MILLISECONDS)
                    .cache(cache)
                    .retryOnConnectionFailure(true);

            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);


            mRetrofit = new Retrofit.Builder()
                    .baseUrl(GameConts.GIANT_BOMB_BASE_PATH)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }

        return mRetrofit;
    }

}
