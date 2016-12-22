package com.zi.dian.dao.model;

/**
 * Created by wangliang on 6/20/16.
 */
public class PyRead {
    public int ID;
    public String py;
    public String tone;//lin1, lin2, lin3, lin4

    public PyRead(int ID, String py, String tone) {
        this.ID = ID;
        this.py = py;
        this.tone = tone;
    }

    public PyRead() {
    }
}
