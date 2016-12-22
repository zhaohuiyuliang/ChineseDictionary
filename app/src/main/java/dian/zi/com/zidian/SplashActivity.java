package dian.zi.com.zidian;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zi.dian.ControlApplication;
import com.zi.dian.dao.impl.ZDDatabaseUtils;
import com.zi.dian.net.Controller;
import com.zi.dian.unitl.FileCopy;

import java.io.File;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    private Handle handle = new Handle();

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String DB_PATH = "/data/data/dian.zi.com.zidian/databases/";
                String DB_NAME = "ZiDian.db";
                String FILE_MP3_PATH = "/data/data/dian.zi.com.zidian/files/mp3/baidu/";
                if ((new File(DB_PATH + DB_NAME)).exists() == false) {
                    String assetsPath = "ZiDian.db";
                    File f = new File(DB_PATH);
                    if (f.exists() == false) {
                        f.mkdir();
                    }
                    FileCopy.fileCopyFromAssetsTo(assetsPath, DB_PATH + DB_NAME);
                    String assetsZipPath = "baidu.zip";
                    File fileMP3 = new File(FILE_MP3_PATH);
                    if (fileMP3.exists() == false) {
                        fileMP3.mkdirs();
                    }
                    FileCopy.UnZipFolder(assetsZipPath, FILE_MP3_PATH);
                } else {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handle.sendEmptyMessage(1);
            }
        }).start();
    }

    class Handle extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    Intent intent = new Intent(SplashActivity.this, FragmentActivityHome.class);
                    startActivity(intent);
                    finish();
                }
                break;
                default:
                    break;
            }
        }
    }
}
