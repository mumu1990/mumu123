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

/**
 * category_tag_menu<br/>
 * 这个任务支持一个参数，参数的内容，是type 的值，目前只可以写user
 */
public class CategoryTagMenuTask extends BaseTask {

    public CategoryTagMenuTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {

        //TaskResult 必须创建，用来描述任务类型以及数据
        TaskResult ret = new TaskResult();

        ret.taskId = Constants.TASK_CATEGORY_TAG_MENU;

        ////////////////////////////////////////

        String type = null;

        if (params != null && params.length > 0) {
            type = params[0];
        }

        String str = ClientDiscoverAPI.getCategoryTagMenu(type);

        if (str != null) {

            try {
                ret.data = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        return ret;
    }
}
