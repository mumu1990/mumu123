package com.mumu.zhufengfm.app.fragments.Discover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mumu.zhufengfm.app.Constants;
import com.mumu.zhufengfm.app.R;
import com.mumu.zhufengfm.app.data.DataStore;
import com.mumu.zhufengfm.app.model.DiscoverCategory;
import com.mumu.zhufengfm.app.parsers.DataParser;
import com.mumu.zhufengfm.app.tasks.TaskCallback;
import com.mumu.zhufengfm.app.tasks.TaskResult;
import com.mumu.zhufengfm.app.tasks.impl.DiscoverCategoryTask;
import com.mumu.zhufengfm.app.util.MyLog;
import org.json.JSONObject;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * 分类
 */
public class DiscoverCategoryFragment extends Fragment implements TaskCallback {

    private static final String TAG="DCF";
    public DiscoverCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //1.判断是否有分类
        List<DiscoverCategory> categories = DataStore.getInstance().getDiscoverCategories();

        if (categories != null && !categories.isEmpty()) {

            //有分类

               MyLog.d(TAG, "Discover category has.");


        } else {
            //无分类 开启异步任务


            DiscoverCategoryTask task = new DiscoverCategoryTask(this);
            task.execute();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_category, container, false);
    }


    /**
     * @param result
     */
    @Override
    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int taskId = result.taskId;
            Object data = result.data;
            if (taskId == Constants.TASK_DISCOVER_CATEGORY) {
                if (data != null) {
                    if (data instanceof JSONObject) {

                        List<DiscoverCategory> categories = DataParser.parseDiscoverCategories((JSONObject) data);

                        if (categories != null && categories.isEmpty()) {
                            DataStore.getInstance().setDiscoverCategories(categories);

                            //TODO 利用分类制作UI界面
                        }
                    }
                }
            }
        }
    }

}
