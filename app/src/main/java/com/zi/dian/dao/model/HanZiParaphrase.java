package com.zi.dian.dao.model;

import java.io.Serializable;

/**
 * Created by wangliang on 6/15/16.
 */
public class HanZiParaphrase extends HanZi {
    public int ID;

    public String baseParaphrase;

    public String detailedParaphrase;

    public String linkUrl;

    public HanZiParaphrase(String radical, int stroke, String zi, String spelling, String linkUrl) {
        this.radical = radical;
        this.zi = zi;
        this.stroke = stroke;
        this.spelling = spelling;
        this.linkUrl = linkUrl;
    }

    public HanZiParaphrase() {
    }
}
