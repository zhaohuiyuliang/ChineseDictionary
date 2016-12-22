package com.zi.dian.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.zi.dian.dao.impl.ZDDatabaseUtils;
import com.zi.dian.dao.model.HanZi;
import com.zi.dian.dao.model.LetterSpelling;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangliang on 6/24/16.
 */
public class TableLetter {

    private Context context;

    public TableLetter(Context context) {
        this.context = context;
    }

    public void insertData(LetterSpelling useZi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("letter", useZi.letter);
        contentValues.put("spelling", useZi.spelling);
        contentValues.put("linkUrl", useZi.letter);
        database.insert("table_letter_spelling", null, contentValues);
    }


    public void batchInsertData(List<LetterSpelling> listRadicals) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        String sql = "insert into table_letter_spelling (letter, spelling, linkUrl) values(?, ?, ?)";
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (LetterSpelling useZi : listRadicals) {
            Log.d("table_letter_spelling", useZi.letter + " " + useZi.spelling);
            statement.bindString(1, useZi.letter);
            statement.bindString(2, useZi.spelling);
            statement.bindString(3, useZi.link);
            statement.executeInsert();
        }
        statement.close();
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public Map<String, List<LetterSpelling>> queryAllData() {
        Map<String, List<LetterSpelling>> letterSpellingMap = new HashMap<>();
        List<String> list = queryAllLetterData();
        for (String letter : list) {
            List<LetterSpelling> letterSpellingList = querySpellingByLetter(letter);
            letterSpellingMap.put(letter, letterSpellingList);
        }
        return letterSpellingMap;
    }

    private List<LetterSpelling> querySpellingByLetter(String letter) {
        List<LetterSpelling> letterSpellingList = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("table_letter_spelling", new String[]{"spelling", "linkUrl"}, "letter = ?", new String[]{letter}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                LetterSpelling letterSpelling = new LetterSpelling();
                letterSpelling.letter = letter;
                letterSpelling.spelling = cursor.getString(0);
                letterSpelling.link = cursor.getString(1);
                letterSpellingList.add(letterSpelling);
                cursor.moveToNext();
            }
        }
        return letterSpellingList;

    }

    private List<String> queryAllLetterData() {
        List<String> ziList = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("table_letter_spelling", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Set set = new HashSet();
            while (!cursor.isAfterLast()) {
                set.add(cursor.getString(0));
                cursor.moveToNext();
            }
            ziList.addAll(set);
        }
        return ziList;
    }


    public boolean isData(String letter, String spelling) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor localCursor = database.query("table_letter_spelling", null,
                "letter = ? and spelling =  ?", new String[]{letter, spelling}, null, null, null);
        return (localCursor != null && localCursor.getCount() > 0);
    }

    public void clearTable() {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        database.delete("table_letter_spelling", null, null);
    }
}

