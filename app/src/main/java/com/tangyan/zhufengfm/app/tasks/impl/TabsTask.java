package com.tangyan.zhufengfm.app.tasks.impl;

import com.tangyan.zhufengfm.app.Constants;
import com.tangyan.zhufengfm.app.client.ClientDiscoverAPI;
import com.tangyan.zhufengfm.app.tasks.BaseTask;
import com.tangyan.zhufengfm.app.tasks.TaskCallback;
import com.tangyan.zhufengfm.app.tasks.TaskResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: tangyan
 * Date: 15-7-28
 */
public class TabsTask extends BaseTask {
    public TabsTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();

        ret.taskId = Constants.TASK_TABS;

        String str = ClientDiscoverAPI.getTabs();

        try {
            if (str != null) {
                ret.data = new JSONObject(str);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
