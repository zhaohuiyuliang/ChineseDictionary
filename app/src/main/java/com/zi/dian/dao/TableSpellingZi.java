package com.zi.dian.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.zi.dian.dao.impl.ZDDatabaseUtils;
import com.zi.dian.dao.model.SpellingZi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangliang on 6/24/16.
 */
public class TableSpellingZi {

    private Context context;

    public TableSpellingZi(Context context) {
        this.context = context;
    }

    public void insertData(SpellingZi useZi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("spelling", useZi.spelling);
        contentValues.put("zi", useZi.zi);
        contentValues.put("stroke", useZi.stroke);
        database.insert("table_spelling_zi", null, contentValues);
    }


    public void batchInsertData(List<SpellingZi> listRadicals) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        String sql = "insert into table_spelling_zi (spelling, zi, stroke) values(?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (SpellingZi useZi : listRadicals) {
            Log.d("table_spelling_zi", useZi.spelling + " " + useZi.zi);
            statement.bindString(1, useZi.spelling);
            statement.bindString(2, useZi.zi);
            statement.bindLong(3, useZi.stroke);
            statement.executeInsert();
        }
        statement.close();
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public Map<Integer, List<SpellingZi>> queryMapBySpelling(String spelling) {
        Map<Integer, List<SpellingZi>> listMap = new HashMap<>();
        List<Integer> listStroke = queryAllStrokeBySpelling(spelling);
        for (Integer stroke : listStroke) {
            List<SpellingZi> spellingZiList = querySpellingZiListByStrokeAndSpelling(stroke, spelling);
            listMap.put(stroke, spellingZiList);
        }
        return listMap;
    }

    public List<Integer> queryAllStrokeBySpelling(String spelling) {
        List<Integer> list = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("table_spelling_zi", new String[]{"stroke"}, "spelling = ?", new String[]{spelling}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Set<Integer> set = new HashSet();
            while (!cursor.isAfterLast()) {
                set.add(cursor.getInt(0));
                cursor.moveToNext();
            }
            list.addAll(set);
        }
        return list;
    }

    public List<SpellingZi> querySpellingZiListByStrokeAndSpelling(int stroke, String spelling) {
        List<SpellingZi> ziList = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("table_spelling_zi", null, "stroke = ? and spelling = ?", new String[]{String.valueOf(stroke), spelling}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                SpellingZi zi = new SpellingZi();
                zi.spelling = cursor.getString(0);
                zi.zi = cursor.getString(1);
                zi.stroke = cursor.getInt(2);
                ziList.add(zi);
                cursor.moveToNext();
            }
        }
        return ziList;
    }


    public boolean isData(String zi, String spelling) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor localCursor = database.query("table_spelling_zi", null,
                "zi = ? and spelling =  ?", new String[]{zi, spelling}, null, null, null);
        return (localCursor != null && localCursor.getCount() > 0);
    }

    public void clearTable() {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        database.delete("table_spelling_zi", null, null);
    }
}

