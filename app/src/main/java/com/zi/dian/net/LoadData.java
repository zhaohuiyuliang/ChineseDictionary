package com.zi.dian.net;

import com.zi.dian.ControlApplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by wangliang on 6/13/16.
 */
public abstract class LoadData implements Runnable {
    String url;

    ControlApplication getAppliction() {
        return ControlApplication.getApplication();
    }

    @Override
    public void run() {
        String str = streamPost(url);
        completeLoad(str);
    }

    public  abstract void completeLoad(String str);


    public String streamPost(String remote_addr) {
        String webContent = "";
        InputStream inputStream = getInputStream(remote_addr);
        byte[] bytes = new byte[1024];
        int line;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            while ((line = inputStream.read(bytes)) != -1) {
                stringBuffer.append(new String(bytes, "GBK"));
            }
            inputStream.close();
            webContent = stringBuffer.toString();
        } catch (IOException e) {

        }
        return webContent;
    }

    private InputStream getInputStream(String remote_addr) {
        URL infoUrl;
        InputStream inStream = null;
        try {
            infoUrl = new URL(remote_addr);
            URLConnection connection = infoUrl.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;
            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream();
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return inStream;
    }
}
