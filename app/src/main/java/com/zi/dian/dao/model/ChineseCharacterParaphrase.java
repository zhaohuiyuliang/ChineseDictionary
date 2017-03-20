package com.zi.dian.dao.model;

/**
 * Created by wangliang on 6/15/16.
 */
public class ChineseCharacterParaphrase extends ChineseCharacter {
    public int ID;

    public String baseParaphrase;

    public String detailedParaphrase;

    public String linkUrl;

    public ChineseCharacterParaphrase(String radical, int stroke, String zi, String spelling, String linkUrl) {
        this.radical = radical;
        this.zi = zi;
        this.stroke = stroke;
        this.spelling = spelling;
        this.linkUrl = linkUrl;
    }

    public ChineseCharacterParaphrase() {
    }
}
