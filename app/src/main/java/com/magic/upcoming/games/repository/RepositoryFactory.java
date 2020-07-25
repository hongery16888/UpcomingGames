package com.magic.upcoming.games.repository;

import android.content.Context;

import com.magic.upcoming.games.repository.api.TranslateRepo;
import com.magic.upcoming.games.repository.imp.TranslateRepoImp;


/**
 * Created by ThinkPad on 2017/11/2.
 */

public class RepositoryFactory {

//    public static TranslateRepo getTranslateRepo(Context context){
//        return TranslateRepoImp_.getInstance_(context);
//    }

    public static TranslateRepo getTranslateRepo(Context context) {
        return TranslateRepoImp.getInstance();
    }
}
