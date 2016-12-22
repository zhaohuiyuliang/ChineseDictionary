package com.zi.dian.dao.model;

import java.io.Serializable;

/**
 * Created by wangliang on 6/14/16.
 */
public class Radicals implements Serializable {

    public int ID;

    public String radical;

    public String stroke;

    public String linkUrl;

    public Radicals(String radical, String stroke, String linkUrl) {
        this.radical = radical;
        this.stroke = stroke;
        this.linkUrl = linkUrl;
    }

    public Radicals() {
    }
}
