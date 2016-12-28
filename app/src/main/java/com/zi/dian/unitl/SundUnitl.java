package com.zi.dian.unitl;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by wangliang on 6/21/16.
 */
public class SundUnitl {


    public static void startPlayVideo(String fileName) {
        final MediaPlayer mediaPlayer = new MediaPlayer();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {//播出完毕事件
            @Override
            public void onCompletion(MediaPlayer arg0) {
                mediaPlayer.release();
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {// 错误处理事件
            @Override
            public boolean onError(MediaPlayer player, int arg1, int arg2) {
                mediaPlayer.release();
                return false;
            }
        });
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();//重置为初始状态
        }
        try {
            String mapFilePath = "/data/data/dian.zi.com.zidian/files/mp3/baidu/" + fileName + ".mp3";
            mediaPlayer.setDataSource(mapFilePath);

            mediaPlayer.prepare();
            mediaPlayer.start();//开始或恢复播放
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
