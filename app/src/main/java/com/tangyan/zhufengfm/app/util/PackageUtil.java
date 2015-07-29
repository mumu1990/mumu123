package com.tangyan.zhufengfm.app.util;

/**
 * Created with IntelliJ IDEA.
 * User: tangyan
 * Date: 15-7-29
 */

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 软件包工具类，获取版本号信息
 */
public class PackageUtil {

    private PackageUtil() {
    }


    /**
     * 获取应用程序版本号
     *
     * @param context
     * @return
     */
    public static String getPackageVersionName(Context context) {
        String ret = "1.0";

        if (context != null) {

            PackageManager manager = context.getPackageManager();

            //参数1：当前软件包 package 信息
            //      通过Context.getPackageName()获取

            try {
                PackageInfo info =
                        manager.getPackageInfo(
                                context.getPackageName(),
                                PackageManager.GET_ACTIVITIES
                        );

                ret = info.versionName;

            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

        }

        return ret;
    }

}
