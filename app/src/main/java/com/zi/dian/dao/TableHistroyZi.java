package com.zi.dian.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.zi.dian.dao.impl.ZDDatabaseUtils;
import com.zi.dian.dao.model.HistroyZi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 6/24/16.
 */
public class TableHistroyZi {

    private Context context;

    public TableHistroyZi(Context context) {
        this.context = context;
    }

    public void insertData(HistroyZi useZi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("zi", useZi.zi);
        database.insert("table_histroy_zi", null, contentValues);
    }


    public void updateData(HistroyZi useZi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        database.update("table_histroy_zi", contentValues, "zi = ?", new String[]{useZi.zi});
    }

    public void batchInsertData(List<HistroyZi> listRadicals) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        String sql = "insert into table_use_zi (zi) values(?)";
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (HistroyZi useZi : listRadicals) {
            Log.d("table_histroy_zi", useZi.zi + " " + useZi.zi);
            statement.bindString(1, useZi.zi);
            statement.executeInsert();
        }
        statement.close();
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public List<HistroyZi> queryData() {
        List<HistroyZi> ziList = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("table_histroy_zi", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                HistroyZi zi = new HistroyZi();
                zi.zi = cursor.getString(0);
                ziList.add(zi);
                cursor.moveToNext();
            }
        }
        return ziList;
    }

    public HistroyZi queryDataByZi(String zi) {
        HistroyZi hanZiParaphrase = null;
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("table_histroy_zi", null, " zi = ?", new String[]{zi}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            hanZiParaphrase = new HistroyZi();
            hanZiParaphrase.zi = cursor.getString(0);
        }
        return hanZiParaphrase;
    }


    public boolean isData(String zi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor localCursor = database.query("table_histroy_zi", null,
                "zi = ?", new String[]{zi}, null, null, null);
        return (localCursor != null && localCursor.getCount() > 0);
    }

    public void clearTable() {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        database.delete("table_histroy_zi", null, null);
    }
}

