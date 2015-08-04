package com.mumu.zhufengfm.app.tasks.impl;

import com.mumu.zhufengfm.app.Constants;
import com.mumu.zhufengfm.app.client.ClientDiscoverAPI;
import com.mumu.zhufengfm.app.tasks.BaseTask;
import com.mumu.zhufengfm.app.tasks.TaskCallback;
import com.mumu.zhufengfm.app.tasks.TaskResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/30.
 */
public class DiscoverRecommendTask extends BaseTask {
    public DiscoverRecommendTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.taskId= Constants.TASK_DISCOVER_RECOMMENDS;

        //调用API
        String discoverRecommend = ClientDiscoverAPI.getDiscoverRecommend();
        if(discoverRecommend!=null) {
            try {
                ret.data = new JSONObject(discoverRecommend);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
