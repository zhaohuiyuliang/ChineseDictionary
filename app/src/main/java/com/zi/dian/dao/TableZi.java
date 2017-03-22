package com.zi.dian.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.zi.dian.dao.impl.ZDDatabaseUtils;
import com.zi.dian.dao.model.ChineseCharacter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by wangliang on 6/14/16.
 */
public class TableZi {
    private Context context;

    public TableZi(Context context) {
        this.context = context;
    }

    public void insertData(ChineseCharacter chineseCharacter) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("radical", chineseCharacter.radical);
        contentValues.put("zi", chineseCharacter.zi);
        contentValues.put("spelling", chineseCharacter.spelling);
        contentValues.put("stroke", chineseCharacter.stroke);
        contentValues.put("linkUrl", chineseCharacter.linkUrl);
        database.insert("bs_zi_table", null, contentValues);
    }

    public void batchInsertData(List<ChineseCharacter> listRadicals) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        String sql = "insert into bs_zi_table (radical, zi,spelling, stroke, linkUrl) values(?,?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        database.beginTransaction();
        for (ChineseCharacter zi : listRadicals) {
            Log.d("TableZi", zi.zi + " " + zi.spelling);
            statement.bindString(1, zi.radical);
            statement.bindString(2, zi.zi);
            statement.bindString(3, zi.spelling);
            statement.bindLong(4, zi.stroke);
            statement.bindString(5, zi.linkUrl);
            statement.executeInsert();
        }
        statement.close();
        database.setTransactionSuccessful();
        database.endTransaction();
    }

    public Map<Integer, List<ChineseCharacter>> queryAllData(String radicals) {
        Map<Integer, List<ChineseCharacter>> ziList = new HashMap<>();
        List<Integer> list = queryAllStrokeByRadicals(radicals);
        for (Integer integer : list) {
            List<ChineseCharacter> chineseCharacterList = queryDataByStrokeAndRadicals(radicals, integer);
            ziList.put(integer, chineseCharacterList);
        }
        return ziList;
    }

    public List<Integer> queryAllStrokeByRadicals(String radicals) {
        List<Integer> strokeList = new ArrayList<>();
        Set<Integer> integerSet = new HashSet<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("bs_zi_table", new String[]{"stroke"}, "radical = ?", new String[]{radicals}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                integerSet.add(cursor.getInt(0));
                cursor.moveToNext();
            }
        }
        strokeList.addAll(integerSet);
        return strokeList;
    }

    /**
     * 所有的笔画数
     *
     * @return
     */
    public List<Integer> queryAllStroke() {
        List<Integer> strokeList = new ArrayList<>();
        Set<Integer> integerSet = new TreeSet<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("bs_zi_table", new String[]{"stroke"}, null, null, null, null, "stroke desc");
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                integerSet.add(cursor.getInt(0));
                cursor.moveToNext();
            }
        }
        strokeList.addAll(integerSet);
        return strokeList;
    }



    public List<ChineseCharacter> queryDataByStrokeAndRadicals(String radicals, int stroke) {
        List<ChineseCharacter> ziList = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor;
        if ("".compareTo(radicals) == 0) {
            cursor = database.query("bs_zi_table", null, "stroke = ?", new String[]{String.valueOf(stroke)}, null, null, null);
        } else if (stroke < 1) {
            cursor = database.query("bs_zi_table", null, "radical = ?", new String[]{radicals}, null, null, null);

        } else {
            cursor = database.query("bs_zi_table", null, "radical = ? and stroke = ?", new String[]{radicals, String.valueOf(stroke)}, null, null, null);
        }
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ChineseCharacter zi = new ChineseCharacter();
                zi.radical = cursor.getString(0);
                zi.zi = cursor.getString(1);
                zi.stroke = cursor.getInt(2);
                zi.linkUrl = cursor.getString(3);
                ziList.add(zi);
                cursor.moveToNext();
            }
        }
        return ziList;
    }

    public boolean isData(String zi, String spelling) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor localCursor = database.query("bs_zi_table", null,
                "zi = ? and spelling = ?", new String[]{zi, spelling}, null, null, null);
        return (localCursor != null && localCursor.getCount() > 0);
    }

    public void clearTable() {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        database.delete("bs_zi_table", null, null);
    }
}
