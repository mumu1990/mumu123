package com.tangyan.zhufengfm.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Window;
import com.tangyan.zhufengfm.app.model.CategoryTagMenu;
import com.tangyan.zhufengfm.app.parsers.DataParser;
import com.tangyan.zhufengfm.app.tasks.TaskCallback;
import com.tangyan.zhufengfm.app.tasks.TaskResult;
import com.tangyan.zhufengfm.app.tasks.impl.CategoryTagMenuTask;
import com.tangyan.zhufengfm.app.util.PackageUtil;
import org.json.JSONObject;

import java.util.List;


public class SplashActivity extends FragmentActivity implements TaskCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //启动扉页没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
    }


    @Override
    protected void onResume() {
        super.onResume();


        //启动扉页，进行网络检查与网络请求，下载数据
        //最终显示主界面

        CategoryTagMenuTask task = new CategoryTagMenuTask(this);

        task.execute("user");

    }

    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int taskId = result.taskId;

            Object data = result.data;


            if (taskId == Constants.TASK_CATEGORY_TAG_MENU) {
                // TODO 获取 category_tag_menu的数据

                if (data != null) {
                    if (data instanceof JSONObject) {
                        JSONObject json = (JSONObject) data;

                        List<CategoryTagMenu> categoryTagMenuList =
                                DataParser.parseCategoryTagMenuResult(json);

                        // TODO 存储CategoryTagMenu

                    }
                }

                // TODO 处理之后，判断教程的启动

                SharedPreferences sp =
                        getSharedPreferences(Constants.SP_NAME, MODE_PRIVATE);

                //获取上一次版本号
                String lastVer = sp.getString(Constants.SP_KEY_GUIDE_LAST_SHOW_VERSION, null);

                String versionName = PackageUtil.getPackageVersionName(this);

                Intent intent = null;

                if(!versionName.equals(lastVer)){
                    //TODO 显示教程
                    intent = new Intent(this, GuideActivity.class);
                } else {
                    //TODO 显示主界面
                    intent = new Intent(this, MainActivity.class);
                }

                //使用API 11 的 CLEAR_TASK 可以实现清除任务栈
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);

                finish();
            }
        }
    }
}
