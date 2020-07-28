package com.magic.upcoming.games.repository;

import com.magic.upcoming.games.repository.api.GameAPIRepo;
import com.magic.upcoming.games.repository.imp.GameAPIRepoImp;


/**
 * Created by ThinkPad on 2017/11/2.
 */

public class RepositoryFactory {
    public static GameAPIRepo getGameApiRepo() {
        return GameAPIRepoImp.getInstance();
    }
}
