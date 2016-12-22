package com.zi.dian.net;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/**
 * Created by wangliang on 6/13/16.
 */
public class Controller {
    private String TAG = "Controller";
    private HandlerThread mHandlerThread;
    private volatile Looper mServiceLooper;
    private static Handler mMessageHandler;

    private final int msg_radicals_task = 0;
    private final int msg_zi_task = 1;
    private final int msg_zi_paraphrase_task = 3;
    private final int msg_spelling_read_task = 4;
    private final int msg_letter_spelling_task = 5;

    public Controller() {
        if (mHandlerThread == null) {
            mHandlerThread = new HandlerThread("Controller Message Processing Thread");
            mHandlerThread.start();
        }
        if (mHandlerThread != null && !mHandlerThread.isAlive()) {
            mHandlerThread.start();
        }
        mServiceLooper = mHandlerThread.getLooper();
        mMessageHandler = new Handler(mServiceLooper) {
            @Override
            public void handleMessage(Message msg) {
                Controller.this.handleMessage(msg);
            }
        };
    }

    private void handleMessage(Message msg) {
        LoadData task = null;
        switch (msg.what) {
            case msg_radicals_task://请求部首
                task = new TaskRadicals();
                break;
            case msg_zi_task:
                task = new TaskZi(null);
                break;
            case msg_zi_paraphrase_task://请求字
                task = new TaskZiParaphrase(null);
                break;
            case msg_spelling_read_task://请求拼音的读音
                task = new TaskSpellingRead(null);
                break;
            case msg_letter_spelling_task:
                task = new TaskSpellingOfLetter();
                break;
            default:
                break;
        }
        if (task != null) {
            task.run();
        }
    }

    public void requestRadicals() {
        Message msg = Message.obtain(mMessageHandler, msg_radicals_task);
        mMessageHandler.sendMessage(msg);
    }

    public void requestZi() {
        Message msg = Message.obtain(mMessageHandler, msg_zi_task);
        mMessageHandler.sendMessage(msg);
    }

    public void requestZiParaphrase() {
        Message msg = Message.obtain(mMessageHandler, msg_zi_paraphrase_task);
        mMessageHandler.sendMessage(msg);
    }

    public void requestLetterSpelling() {
        Message msg = Message.obtain(mMessageHandler, msg_letter_spelling_task);
        mMessageHandler.sendMessage(msg);
    }
    public void requestSpellingRead() {
        Message msg = Message.obtain(mMessageHandler, msg_spelling_read_task);
        mMessageHandler.sendMessage(msg);
    }

}
