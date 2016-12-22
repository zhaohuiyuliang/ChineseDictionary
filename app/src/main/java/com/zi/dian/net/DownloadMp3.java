package com.zi.dian.net;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by wangliang on 6/20/16.
 */
public class DownloadMp3 extends LoadData {
    String urlXiHua = "http://zd.diyifanwen.com/Upload/duyin/";
    String urlBaiDu = "http://appcdn.fanyi.baidu.com/zhdict/mp3/";
    String tone;

    public DownloadMp3(String tone) {
        this.tone = tone;
    }

    private String getUrl() {
        return getBaiDu();
    }

    private String getBaiDu() {
        if ("da".compareTo(tone) == 0) {
            url = urlBaiDu + tone + "0.mp3";
        } else {
            String newTone = tone.replace("ü", "v");
            url = urlBaiDu + newTone + ".mp3";
        }
        return url;
    }

    private String getXinHua() {
        try {
            String newTone = URLEncoder.encode(tone, "UTF-8");
            url = urlXiHua + newTone + ".mp3";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public void run() {
        try {

            File file = new File(getSDPath() + "/zidian/mp3/baidu" + File.separator + tone + ".mp3");
            if (file.exists()) {
                return;
            }
            HttpURLConnection httpConnection;

            URL url = new URL(getUrl());
            httpConnection = createHttpConnection(url);
            //设置超时间为60秒
            httpConnection.setConnectTimeout(60 * 1000);

            //得到输入流
            InputStream inputStream = httpConnection.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);
            //文件保存位置
            File saveDir = new File(getSDPath() + "/zidian/mp3/baidu");
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.flush();
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED);//判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    private HttpURLConnection createHttpConnection(URL url) throws IOException {
        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestProperty("User-Agent", "NetFox");
        httpConnection.setReadTimeout(60000);
        return httpConnection;
    }

    @Override
    public void completeLoad(String str) {

    }
}
