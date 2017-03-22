package com.zi.dian.util;

import android.os.Environment;

import com.zi.dian.ControlApplication;

import java.io.File;

/**
 * Created by wangliang on 2017/3/22.
 */

public final class CommonUtilities {

    public static String getExternalPublishPath() {
        File file;
        if (isExternalStorageWritable()) {
            file = Environment.getExternalStorageDirectory();
        } else {
            return "";
        }
        return file.toString();
    }


    public static String getExternalPrivatePath() {
        File file;
        if (isExternalStorageWritable()) {
            file = ControlApplication.getApplication().getExternalFilesDir(null);
        } else {
            return "";
        }
        return file.toString();
    }


    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
