package com.magic.upcoming.games.network.response;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public class BaseResponse<T> {

    private T content;

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

}