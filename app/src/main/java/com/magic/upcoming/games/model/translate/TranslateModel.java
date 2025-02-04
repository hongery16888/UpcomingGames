package com.magic.upcoming.games.model.translate;

import com.magic.upcoming.games.model.BaseModel;

/**
 * Created by ThinkPad on 2017/11/2.
 */

public class TranslateModel extends BaseModel {

    /**
     * from : en-EU
     * to : zh-CN
     * out : 示例
     * vendor : ciba
     * err_no : 0
     */

    private String from;
    private String to;
    private String out;
    private String vendor;
    private int err_no;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public int getErr_no() {
        return err_no;
    }

    public void setErr_no(int err_no) {
        this.err_no = err_no;
    }
}
