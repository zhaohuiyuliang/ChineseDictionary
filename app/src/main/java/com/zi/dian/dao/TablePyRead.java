package com.zi.dian.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.zi.dian.dao.impl.ZDDatabaseUtils;
import com.zi.dian.dao.model.PyRead;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangliang on 6/20/16.
 */
public class TablePyRead {
    private Context context;

    public TablePyRead(Context context) {
        this.context = context;
    }

    public void insertData(PyRead hanZi) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("py", hanZi.py);
        contentValues.put("tone", hanZi.tone);
        database.insert("py_read_table", null, contentValues);
    }

    public PyRead queryDataByZi(String py) {
        PyRead pyRead = new PyRead();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("py_read_table", null, "py = '" +  py+"'", null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            pyRead.py = cursor.getString(0);
            pyRead.tone = cursor.getString(1);
        }
        return pyRead;
    }

    public List<PyRead> queryAllData() {
        List<PyRead> hanZiParaphrase = new ArrayList<>();
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor cursor = database.query("py_read_table", null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                PyRead pyRead = new PyRead();
                pyRead.py = cursor.getString(0);
                pyRead.tone = cursor.getString(1);
                hanZiParaphrase.add(pyRead);
                cursor.moveToNext();
            }
        }
        return hanZiParaphrase;
    }

    public boolean isData(String spelling) {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        Cursor localCursor = database.query("py_read_table", null,
                "py = ?", new String[]{spelling}, null, null, null);
        return (localCursor != null && localCursor.getCount() > 0);
    }

    public void clearTable() {
        SQLiteDatabase database = ZDDatabaseUtils.getInstance(context).openDatabase();
        database.delete("py_read_table", null, null);

    }

}
