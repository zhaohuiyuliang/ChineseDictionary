package com.zi.dian.unitl;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wangliang on 6/24/16.
 */
public class PreferenceUtil {
    private static final String name = "settings";

    public static void setKey(String key, String value, Context con) {
        SharedPreferences pref = con.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.putString(key, value);
        e.commit();
    }

    public static String getValue(String key, String defaultValue, Context con) {
        SharedPreferences pre = con.getSharedPreferences(name, Context.MODE_PRIVATE);
        return pre.getString(key, defaultValue);
    }

    public static void setLong(String key, long value, Context con) {
        SharedPreferences pref = con.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.putLong(key, value);
        e.commit();
    }

    public static long getLong(String key, long defaultValue, Context con) {
        SharedPreferences pre = con.getSharedPreferences(name, Context.MODE_PRIVATE);
        return pre.getLong(key, defaultValue);
    }

    public static void setInt(String key, int value, Context con) {
        SharedPreferences pref = con.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.putInt(key, value);
        e.commit();
    }

    public static int getInt(String key, int defaultValue, Context con) {
        SharedPreferences pre = con.getSharedPreferences(name, Context.MODE_PRIVATE);
        return pre.getInt(key, defaultValue);
    }

    public static void setBoolean(String key, boolean value, Context con) {
        SharedPreferences pref = con.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.putBoolean(key, value);
        e.commit();
    }

    public static boolean getBoolean(String key, boolean defaultValue, Context con) {
        SharedPreferences pre = con.getSharedPreferences("mdm", Context.MODE_PRIVATE);
        return pre.getBoolean(key, defaultValue);
    }

    public static void clear(Context con) {
        SharedPreferences pre = con.getSharedPreferences("mdm", Context.MODE_PRIVATE);
        pre.edit().clear().commit();
    }

    public static void removeKey(String key, Context con) {
        SharedPreferences pref = con.getSharedPreferences("mdm", Context.MODE_PRIVATE);
        SharedPreferences.Editor e = pref.edit();
        e.remove(key);
        e.commit();
    }
}

