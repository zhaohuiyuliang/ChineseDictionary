package com.zi.dian.dao.impl;

import android.content.Context;

import com.zi.dian.dao.TableCollectZi;
import com.zi.dian.dao.TableLetter;
import com.zi.dian.dao.TableRadicals;
import com.zi.dian.dao.TablePyRead;
import com.zi.dian.dao.TableHistroyZi;
import com.zi.dian.dao.TableSpellingZi;
import com.zi.dian.dao.TableZi;
import com.zi.dian.dao.TableZiParaphrase;

/**
 * Created by wangliang on 6/14/16.
 */
public class DaoManager {
    private TableRadicals tableBS;
    private TableZi tableZiSpelling;
    private TablePyRead tablePyReadg;
    private TableHistroyZi tableUseZi;
    private TableCollectZi tableCollectZi;
    private TableLetter tableLetter;
    private TableZiParaphrase tableZiParaphrase;
    private  TableSpellingZi tableSpellingZi;
    private Context context;

    public DaoManager(Context context) {
        this.context = context;
    }

    public TableCollectZi getTableCollectZi() {
        if (tableCollectZi == null) {
            tableCollectZi = new TableCollectZi(context);
        }
        return tableCollectZi;
    }
    public TableSpellingZi getTableSpellingZi() {
        if (tableSpellingZi == null) {
            tableSpellingZi = new TableSpellingZi(context);
        }

        return tableSpellingZi;
    }
    public TableRadicals getTableBS() {
        if (tableBS == null) {
            tableBS = new TableRadicals(context);
        }
        return tableBS;
    }

    public TableZi getTableZi() {
        if (tableZiSpelling == null) {
            tableZiSpelling = new TableZi(context);
        }
        return tableZiSpelling;
    }

    public TableLetter getTableTableLetter() {
        if (tableLetter == null) {
            tableLetter = new TableLetter(context);
        }
        return tableLetter;
    }
    public TablePyRead getTablePyRead() {
        if (tablePyReadg == null) {
            tablePyReadg = new TablePyRead(context);
        }
        return tablePyReadg;
    }

    public TableHistroyZi getTableHistroyZi() {
        if (tableUseZi == null) {
            tableUseZi = new TableHistroyZi(context);
        }
        return tableUseZi;
    }


    public TableZiParaphrase getTableZiParaphrase() {
        if (tableZiParaphrase == null) {
            tableZiParaphrase = new TableZiParaphrase(context);
        }
        return tableZiParaphrase;
    }
}
