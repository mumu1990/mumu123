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
public class DiscoverTabTask extends BaseTask {
    public DiscoverTabTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {

        TaskResult ret=new TaskResult();

        ret.taskId= Constants.TASK_DISCOVER_TABS;
        String str = ClientDiscoverAPI.getDiscoverTabs();

        if (str != null) {
            try {
                //返回json是为了让接收数据的部分接口实现 来检查数据的情况，不直接返回实际数据的实体
                ret.data=new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
