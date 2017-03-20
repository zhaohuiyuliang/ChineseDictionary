package com.zi.dian.dao.model;

import java.io.Serializable;

/**
 * Created by wangliang on 6/14/16.
 */
public class ChineseCharacter implements Serializable {

    public int ID;

    public String radical;

    public int stroke;

    public String zi;

    public String spelling;

    public String linkUrl;

    public ChineseCharacter(String radical, int stroke, String zi, String  spelling, String linkUrl) {
        this.radical = radical;
        this.zi = zi;
        this.stroke = stroke;
        this.spelling = spelling;
        this.linkUrl = linkUrl;
    }

    public ChineseCharacter() {
    }
}
