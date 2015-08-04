package com.mumu.zhufengfm.app.tasks.impl;

import com.mumu.zhufengfm.app.Constants;
import com.mumu.zhufengfm.app.client.ClientDiscoverAPI;
import com.mumu.zhufengfm.app.tasks.BaseTask;
import com.mumu.zhufengfm.app.tasks.TaskCallback;
import com.mumu.zhufengfm.app.tasks.TaskResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
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
