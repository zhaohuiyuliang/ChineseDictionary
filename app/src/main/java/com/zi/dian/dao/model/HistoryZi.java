package com.zi.dian.dao.model;

/**
 * Created by wangliang on 6/24/16.
 */
public class HistoryZi extends HanZi {
    public String histroy = "";

    public HistoryZi(String zi, String histroy) {
        this.zi = zi;
        this.histroy = histroy;
    }

    public HistoryZi() {
    }

}
