package com.mumu.zhufengfm.app.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2015/7/31.
 */
public class UnCaughtExceptionHandlerImpl implements Thread.UncaughtExceptionHandler{

    /**
     * 用来获取文件路径用的
     */
    private Context context;

    public UnCaughtExceptionHandlerImpl(Context context) {
        this.context = context;
    }

    /**
     * 未捕获异常处理器：当某一个线程发生了未捕获异常时会回调这个方法
     * @param thread
     * @param ex
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {

        //TODO 将异常信息保存到文件中 下次启动 获取 并且上传服务器

        if (context != null) {

            File filesDir = context.getFilesDir();
            String state = Environment.getExternalStorageState();
            if (state ==Environment.MEDIA_MOUNTED) {

               filesDir = context.getExternalFilesDir(null);
            }


            File logFile = new File(filesDir, "app.log");

            FileWriter fw=null;
            PrintWriter pw=null;



                try {
                    fw = new FileWriter(logFile);
                    pw = new PrintWriter(fw);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    StreamUtil.close(fw);
                    StreamUtil.close(pw);
                }


            }

        System.exit(1);
    }
}
