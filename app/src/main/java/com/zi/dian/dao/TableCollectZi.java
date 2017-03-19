package com.zi.dian.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.zi.dian.dao.impl.ZDDatabaseUtils;
import com.zi.dian.dao.model.CollectZi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 6/24/16.
 */
public class TableCollectZi {

    private Context context;

    public TableCollectZi(Context context) {
        this.context = context;
    }

    public void insertData(CollectZi useZi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("zi", useZi.zi);
        database.insert("table_collect_zi", null, contentValues);
    }


    public void updateData(CollectZi useZi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        database.update("table_collect_zi", contentValues, "zi = ?", new String[]{useZi.zi});
    }

    public void batchInsertData(List<CollectZi> listRadicals) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        String sql = "insert into table_use_zi (zi) values(?)";
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (CollectZi useZi : listRadicals) {
            Log.d("table_collect_zi", useZi.zi + " " + useZi.zi);
            statement.bindString(1, useZi.zi);
            statement.executeInsert();
        }
        statement.close();
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public List<CollectZi> queryData() {
        List<CollectZi> ziList = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("table_collect_zi", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                CollectZi zi = new CollectZi();
                zi.zi = cursor.getString(0);
                ziList.add(zi);
                cursor.moveToNext();
            }
        }
        return ziList;
    }

    public void deleteDataByZi(String zi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        database.execSQL("delete from  table_collect_zi where zi = '" + zi + "'");
    }


    public boolean isData(String zi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor localCursor = database.query("table_collect_zi", null,
                "zi = ?", new String[]{zi}, null, null, null);
        return (localCursor != null && localCursor.getCount() > 0);
    }

    public void clearTable() {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        database.delete("table_collect_zi", null, null);
    }
}

