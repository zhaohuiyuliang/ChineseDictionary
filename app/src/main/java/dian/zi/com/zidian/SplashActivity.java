package dian.zi.com.zidian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zi.dian.unitl.FileCopy;

import java.io.File;

public class SplashActivity extends Activity {
    private static final int LOAD_DB_END = 1;

    private Handle handle = new Handle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new CopyDataToApp(102).start();
        int taskId = getTaskId();

    }

    /**
     * singleTop可有有多个实例，但是不允许多个相同的Activity叠加。
     * 即，如果Activity在栈顶的时候，启动相同Activity，不会创建新的实例，而会调用其onNewIntent()方法。
     * @param intent
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private class CopyDataToApp extends Thread {
        private long minPrime;

        public CopyDataToApp(long minPrime) {
            this.minPrime = minPrime;
        }

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
            handle.sendEmptyMessage(LOAD_DB_END);
        }
    }

    private class Handle extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOAD_DB_END: {
                    Intent intent = new Intent(SplashActivity.this, FragmentActivityHome.class);
                    startActivity(intent);
                    break;
                }
                default: {

                    break;
                }
            }
        }
    }
}
