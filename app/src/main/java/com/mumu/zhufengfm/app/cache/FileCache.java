package com.mumu.zhufengfm.app.cache;

import android.content.Context;
import android.os.Environment;
import com.mumu.zhufengfm.app.util.StreamUtil;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2015/7/31.
 */
public class FileCache {
    private static FileCache outInstance;

    public static FileCache createInstance(Context context) {
        if (context != null) {
            if (outInstance == null) {
                outInstance = new FileCache(context);
            }
            return outInstance;
        } else {
            throw new IllegalArgumentException("context must exist");
        }
    }

    public static FileCache getInstance() {
        if (outInstance != null) {
            return outInstance;
        } else {

            throw new IllegalArgumentException("u must invoke create instance");
        }
    }

    /**
     * 用于获取CatcheDir  存储卡上main的
     */
    private Context context;

    private FileCache(Context context) {

        this.context = context;
    }

    /**
     * 通过网址加载图片
     *
     * @param url
     * @return
     */
    public byte[] loadFile(String url) {

        byte[]ret=null;
        String state = Environment.getExternalStorageState();
        File cacheDir = null;
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = context.getExternalCacheDir();
        } else {
            cacheDir = context.getCacheDir();
        }
        if (url != null) {
            //TODO url 转换

            String name = md5(url);

            File file=new File(cacheDir,name);

            if (file.exists()) {
                FileInputStream fin=null;
                try {
                    fin=new FileInputStream(file);
                    ret= StreamUtil.readStream(fin);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    StreamUtil.close(fin);
                }
            }
        }
        return ret;
    }

    public void saveFile(String url, byte[] data){

        String state = Environment.getExternalStorageState();
        File cacheDir = null;
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = context.getExternalCacheDir();
        } else {
            cacheDir = context.getCacheDir();
        }
        if (url != null && data != null) {
            //TODO url 转换

            String name = md5(url);

            File file=new File(cacheDir,name);

            FileOutputStream fout=null;
            try {
                fout=new FileOutputStream(file);
                fout.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                StreamUtil.close(fout);

        }
        }
    }

    /**
     * 转换网址
     *
     * @param url
     * @return
     */
    public static String md5(String url) {

        String ret = null;
        if (url != null) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                //经过md5的内容，字节数组无法new String
                byte[] data = digest.digest(url.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : data) {
                    int h = (b >> 4) & 0x0f;
                    int l = b & 0x0f;
                    sb.append(Integer.toHexString(h));
                    sb.append(Integer.toHexString(l));

                }

                ret=sb.toString();
                sb=null;
                data=null;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
