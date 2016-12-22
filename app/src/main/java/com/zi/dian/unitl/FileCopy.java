package com.zi.dian.unitl;

import com.zi.dian.ControlApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by wangliang on 6/21/16.
 */
public class FileCopy {
    /**
     * DeCompress the ZIP to the path
     *
     * @param zipFileString name of ZIP
     * @param outPathString path to be unZIP
     * @throws Exception
     */
    public static void UnZipFolder(String zipFileString, String outPathString) {
        try {
            InputStream inputStream = ControlApplication.getApplication().getClass().getResourceAsStream("/assets/" + zipFileString);

            ZipInputStream inZip = new ZipInputStream(inputStream);
            ZipEntry zipEntry;
            String szName;
            while ((zipEntry = inZip.getNextEntry()) != null) {
                szName = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    // get the folder name of the widget
                    szName = szName.substring(0, szName.length() - 1);
                    File folder = new File(outPathString + File.separator + szName);
                    folder.mkdirs();
                } else {
                    File file = new File(outPathString + File.separator + szName);
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    // get the output stream of the file
                    FileOutputStream out = new FileOutputStream(file);
                    int len;
                    byte[] buffer = new byte[1024];
                    // read (len) bytes into buffer
                    while ((len = inZip.read(buffer)) != -1) {
                        // write (len) byte from buffer at the position 0
                        out.write(buffer, 0, len);
                    }
                    out.close();
                    out.flush();
                }
            }
            inZip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * assets目录下的文件copy到目的地目录
     *
     * @param filePath
     * @param filePath2
     */
    public static void fileCopyFromAssetsTo(String filePath, String filePath2) {
        try {
            InputStream inputStream = ControlApplication.getApplication().getClass().getResourceAsStream("/assets/" + filePath);
            OutputStream outputStream = new FileOutputStream(new File(filePath2));
            byte[] bytes = new byte[1024];
            int line;
            while ((line = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, line);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 文件copy
     *
     * @param filePath  原文件
     * @param filePath2 目的地目录
     */
    public static void fileCopyTo(String filePath, String filePath2) {
        try {
            InputStream inputStream = new FileInputStream(new File(filePath));
            OutputStream outputStream = new FileOutputStream(new File(filePath2));
            byte[] bytes = new byte[1024];
            int line;
            while ((line = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, line);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
