package com.zi.dian.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.zi.dian.dao.impl.ZDDatabaseUtils;
import com.zi.dian.dao.model.ChineseCharacterParaphrase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 6/15/16.
 */
public class TableZiParaphrase {
    private Context context;

    public TableZiParaphrase(Context context) {
        this.context = context;
    }

    public void insertData(ChineseCharacterParaphrase hanZi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("radical", hanZi.radical);
        contentValues.put("zi", hanZi.zi);
        contentValues.put("spelling", hanZi.spelling);
        contentValues.put("stroke", hanZi.stroke);
        contentValues.put("linkUrl", hanZi.linkUrl);
        contentValues.put("baseParaphrase", hanZi.baseParaphrase);
        contentValues.put("detailedParaphrase", hanZi.detailedParaphrase);
        database.insert("bs_zi_paraphrase_table", null, contentValues);
    }

    public void updateData(ChineseCharacterParaphrase hanZi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("baseParaphrase", hanZi.baseParaphrase);
        contentValues.put("detailedParaphrase", hanZi.detailedParaphrase);
        database.update("bs_zi_paraphrase_table", contentValues, "zi = ? and spelling = ?", new String[]{hanZi.zi, hanZi.spelling});
    }

    public void batchInsertData(List<ChineseCharacterParaphrase> listRadicals) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        String sql = "insert into bs_zi_paraphrase_table (radical, zi,spelling, stroke, linkUrl, baseParaphrase,detailedParaphrase ) values(?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (ChineseCharacterParaphrase zi : listRadicals) {
            Log.d("TableZi", zi.zi + " " + zi.spelling);
            statement.bindString(1, zi.radical);
            statement.bindString(2, zi.zi);
            statement.bindString(3, zi.spelling);
            statement.bindLong(4, zi.stroke);
            statement.bindString(5, zi.linkUrl);
            statement.bindString(6, zi.baseParaphrase);
            statement.bindString(7, zi.detailedParaphrase);
            statement.executeInsert();
        }
        statement.close();
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public List<ChineseCharacterParaphrase> queryAllData() {
        List<ChineseCharacterParaphrase> ziList = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("bs_zi_paraphrase_table", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ChineseCharacterParaphrase zi = new ChineseCharacterParaphrase();
                zi.radical = cursor.getString(0);
                zi.stroke = cursor.getInt(1);
                zi.zi = cursor.getString(2);
                zi.linkUrl = cursor.getString(3);
                zi.baseParaphrase = cursor.getString(4);
                zi.detailedParaphrase = cursor.getString(5);
                ziList.add(zi);
                cursor.moveToNext();
            }
        }
        return ziList;
    }

    public ChineseCharacterParaphrase queryDataByZi(String zi) {
        ChineseCharacterParaphrase hanZiParaphrase = new ChineseCharacterParaphrase();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("bs_zi_paraphrase_table", null, " zi = ?", new String[]{zi}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {

            hanZiParaphrase.radical = cursor.getString(0);
            hanZiParaphrase.zi = cursor.getString(1);
            hanZiParaphrase.spelling = cursor.getString(2);
            hanZiParaphrase.stroke = cursor.getInt(3);
            hanZiParaphrase.linkUrl = cursor.getString(4);
            hanZiParaphrase.baseParaphrase = cursor.getString(5);
            hanZiParaphrase.detailedParaphrase = cursor.getString(6);
        }
        return hanZiParaphrase;
    }

    public boolean isData(ChineseCharacterParaphrase hanZiParaphrase) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor localCursor = database.query("bs_zi_paraphrase_table", null,
                "zi = ? AND spelling = ?", new String[]{hanZiParaphrase.zi, hanZiParaphrase.spelling}, null, null, null);
        return (localCursor != null && localCursor.getCount() > 0);
    }

    public void clearTable() {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        database.delete("bs_zi_paraphrase_table", null, null);
    }
}
