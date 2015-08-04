package com.mumu.zhufengfm.app.util;

/**
 * Created by Administrator on 2015/7/31.
 */

import android.util.Log;
import com.mumu.zhufengfm.app.BuildConfig;

/**
 * 封装android的Log工具 能够增加日志的开关
 * 用于在开发阶段调试用
 * 通过变量的true和false来控制日志是否输出
 */
public final class MyLog {

    private static final boolean DEBUG=true;
    private static final boolean INFO=true;
    /**
     * 日志的开关,在Release（发布软件包的时候）的时候关闭日志
     */
    private static  final boolean ON= BuildConfig.DEBUG;
    private MyLog(){};

    public static void d(String tag,String msg) {

        if (ON) {
            if (DEBUG) {
                Log.d(tag, msg);
            }
        }
    }
    public static void i(String tag,String msg) {

        if (ON) {
            if (INFO) {
                Log.i(tag, msg);
            }
        }

    }
}
