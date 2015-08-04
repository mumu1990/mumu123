package com.mumu.zhufengfm.app.tasks.impl;

import com.mumu.zhufengfm.app.Constants;
import com.mumu.zhufengfm.app.client.ClientDiscoverAPI;
import com.mumu.zhufengfm.app.tasks.BaseTask;
import com.mumu.zhufengfm.app.tasks.TaskCallback;
import com.mumu.zhufengfm.app.tasks.TaskResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/8/1.
 */
public class EditorRecommendItemTask extends BaseTask {

    public EditorRecommendItemTask(TaskCallback callback) {
        super(callback);
    }

    @Override
    protected TaskResult doInBackground(String... params) {
        TaskResult ret = new TaskResult();
        ret.taskId= Constants.TASK_EDITOR_RECOMMENDS_ITEM;

        String albumId = null;

        if (params != null) {
            albumId = params[0];
        }


        //调用API
        String item = ClientDiscoverAPI.getEditorRecommendItem(albumId);
        if(item!=null) {
            try {
                ret.data = new JSONObject(item);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

}
