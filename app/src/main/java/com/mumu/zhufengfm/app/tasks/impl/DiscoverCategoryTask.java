package com.mumu.zhufengfm.app.tasks.impl;

import com.mumu.zhufengfm.app.Constants;
import com.mumu.zhufengfm.app.client.ClientDiscoverAPI;
import com.mumu.zhufengfm.app.tasks.BaseTask;
import com.mumu.zhufengfm.app.tasks.TaskCallback;
import com.mumu.zhufengfm.app.tasks.TaskResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/29.
 */

/**
 * 发现部分 分类获取的任务
 */
public class DiscoverCategoryTask extends BaseTask {
    public DiscoverCategoryTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {

        TaskResult ret = new TaskResult();
        ret.taskId= Constants.TASK_DISCOVER_CATEGORY;
        //调API
        String str=ClientDiscoverAPI.getDiscoverCategories();

        try {
            ret.data=new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
