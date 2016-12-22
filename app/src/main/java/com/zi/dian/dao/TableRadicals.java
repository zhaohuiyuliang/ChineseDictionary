package com.zi.dian.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.zi.dian.dao.impl.ZDDatabaseUtils;
import com.zi.dian.dao.model.Radicals;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangliang on 6/14/16.
 */
public class TableRadicals {
    private Context context;

    public TableRadicals(Context context) {
        this.context = context;
    }

    public void insertData(Radicals radicals) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("radical", radicals.radical);
        contentValues.put("stroke", radicals.stroke);
        contentValues.put("linkUrl", radicals.linkUrl);
        database.insert("bs_table", null, contentValues);
    }

    public void batchInsertData(List<Radicals> listRadicals) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        String sql = "insert into bs_table(radical, stroke, linkUrl) values(?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (Radicals radicals : listRadicals) {
            statement.bindString(1, radicals.radical);
            statement.bindString(2, radicals.stroke);
            statement.bindString(3, radicals.linkUrl);
            statement.executeInsert();
        }
        statement.close();
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public Set<String> getAllStroke() {
        Set<String> strokeList = new HashSet<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("bs_table", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                strokeList.add(cursor.getString(2));
                cursor.moveToNext();
            }
        }
        return strokeList;
    }

    public List<Radicals> getDataByStroke(String stroke) {
        List<Radicals> radicalsList = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("bs_table", null, " stroke = ?", new String[]{stroke}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Radicals radicals = new Radicals();
                radicals.ID = cursor.getInt(0);
                radicals.radical = cursor.getString(1);
                radicals.stroke = cursor.getString(2);
                radicals.linkUrl = cursor.getString(3);
                radicalsList.add(radicals);
                cursor.moveToNext();
            }
        }
        return radicalsList;
    }

    public List<Radicals> queryAllData() {
        List<Radicals> radicalsList = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("bs_table", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Radicals radicals = new Radicals();
                radicals.ID = cursor.getInt(0);
                radicals.radical = cursor.getString(1);
                radicals.stroke = cursor.getString(2);
                radicals.linkUrl = cursor.getString(3);
                radicalsList.add(radicals);
                cursor.moveToNext();
            }
        }
        return radicalsList;
    }

    public boolean isData(String radical, String stroke) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor localCursor = database.query("bs_table", null,
                "radical = ? and stroke = ?", new String[]{radical, stroke}, null, null, null);
        return (localCursor != null && localCursor.getCount() > 0);
    }


    public void clearTable() {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        database.delete("bs_table", null, null);
    }
}
