package com.zi.dian.util;

import com.zi.dian.ControlApplication;
import com.zi.dian.unitl.PreferenceUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

/**
 * Created by wangliang on 2017/3/22.
 */

public final class Logger {

    public static final String FIRST_START = "FirstStart";
    public static final String FIRST_TRUE = "true";
    public static final String FIRST_FALSE = "false";
    private static final String LOG_FILE_NAME = CommonUtilities.getExternalPrivatePath() + "/chineseCharacter.log";
    private static final boolean mDebugEnabled = true;
    private final static String LOG_ENTRY_FORMAT = "[%tF %tT][%s]%s";
    private static Logger mLogger;

    static {
        mLogger = new Logger();
        mLogger.initialize();
    }

    private PrintStream mPrintStream;

    private Logger() {

    }

    public static void d(String tag, String msg) {
        d(tag, msg, null);
    }

    public static boolean isDebugEnabled() {
        return mDebugEnabled;
    }

    public static void d(String tag, String msg, Throwable error) {
        if (!isDebugEnabled())
            return;

        tag = Thread.currentThread().getName() + ": " + tag;

        android.util.Log.d(tag, msg, error);
        write(tag, msg, error);
    }


    private final static void write(String tag, String msg, Throwable error) {
        PrintStream outStream = mLogger.mPrintStream;
        if (null == mLogger.mPrintStream)
            return;
        Date now = new Date();
        outStream.printf(LOG_ENTRY_FORMAT, now, now, tag, msg);
        outStream.println();

        if (error != null) {
            error.printStackTrace(outStream);
            outStream.println();
        }
        if (error != null) {
            StackTraceElement[] traceStack = error.getStackTrace();
            for (StackTraceElement frame : traceStack) {
                outStream.println("File:" + frame.getFileName());
                outStream.println("Method:" + frame.getMethodName());
                outStream.println("Line:" + frame.getLineNumber());
                outStream.println(frame.toString());
            }
        }
    }

    private static PrintStream createFilePrintStream(String fileName) throws IOException {
        File fPrint = new File(fileName);
        boolean flag = Boolean.parseBoolean(PreferenceUtil.getValue(FIRST_START,
                FIRST_FALSE, ControlApplication.getApplication()));
        if (!flag && fPrint.exists()) {

        } else {
            fPrint.createNewFile();
        }
        return new PrintStream(new FileOutputStream(fPrint, true), true);
    }

    private void initialize() {
        try {
            mPrintStream = createFilePrintStream(LOG_FILE_NAME);
        } catch (Exception e) {
        }

    }


}
