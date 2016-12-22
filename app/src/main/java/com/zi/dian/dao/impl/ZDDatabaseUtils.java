package com.zi.dian.dao.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wangliang on 6/14/16.
 */
public class ZDDatabaseUtils extends SQLiteOpenHelper {
    private static String loggerName = "ZDDatabaseUtils";


    public SQLiteDatabase mDatabase = null;

    private static ZDDatabaseUtils sql;

    public static synchronized ZDDatabaseUtils getInstance(Context con) {
        if (sql == null) {
            sql = new ZDDatabaseUtils(con, DBVersionController.DATABASE_NAME, null, DBVersionController.DB_VERSION);
        }
        return sql;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (null == mDatabase) {
            mDatabase = sql.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {

        sql = null;
        mDatabase.close();
        mDatabase = null;
    }

    private ZDDatabaseUtils(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d(loggerName, "Creating database " + db.getVersion());
            if (!db.isOpen()) {
                Log.d(loggerName, "Database Not Open");
            }
            db.setLockingEnabled(true);
            db.beginTransaction();
            db.execSQL(DBVersionController.SQL_CREATE_BS_TABLE);
            db.execSQL(DBVersionController.SQL_CREATE_BS_ZI_TABLE);
            db.execSQL(DBVersionController.SQL_CREATE_BS_ZI_Paraphrase_TABLE);
            db.execSQL(DBVersionController.SQL_CREATE_ZI_PY_READ);
            db.execSQL(DBVersionController.SQL_CREATE_TABLE_HISTROY_ZI);
            db.execSQL(DBVersionController.SQL_CREATE_TABLE_COLLENT_ZI);
            db.execSQL(DBVersionController.SQL_CREATE_TABLE_LETTER_SPELLING);
            db.execSQL(DBVersionController.SQL_CREATE_TABLE_SPELLING_ZI);
            db.setTransactionSuccessful();
        } catch (Exception localException) {
            Log.d(loggerName, localException.getMessage());
        } finally {
            if (db != null) {
                db.endTransaction();
                db.setLockingEnabled(false);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(loggerName, "onUpgrade");
    }
}
