package com.mumu.zhufengfm.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import com.mumu.zhufengfm.app.adapters.EditorRecommendAdapter;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend.EditorRecomendAll;
import com.mumu.zhufengfm.app.parsers.DataParser;
import com.mumu.zhufengfm.app.tasks.TaskCallback;
import com.mumu.zhufengfm.app.tasks.TaskResult;
import com.mumu.zhufengfm.app.tasks.impl.EditorRecommendItemTask;
import org.json.JSONObject;


public class EditorRecommendItemActivity extends BaseActivity implements TaskCallback {

    private ListView listView;
    private EditorRecommendAdapter editorRecommendAdapter;
    private View.OnClickListener onClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor_recommend_item);

        Intent intent = getIntent();
        String albumId = intent.getStringExtra("albumId");
        EditorRecommendItemTask task = new EditorRecommendItemTask(this);
        task.execute(albumId);

        listView= (ListView) findViewById(R.id.editorRecommendImageListView);
        editorRecommendAdapter=new EditorRecommendAdapter(this,onClickListener);
        listView.setAdapter(editorRecommendAdapter);




    }


    @Override
    public void onTaskFinished(TaskResult result) {
        int taskId = result.taskId;

        Object data = result.data;

        if (taskId == Constants.TASK_EDITOR_RECOMMENDS_ITEM) {
            if(data != null && data instanceof JSONObject){
                JSONObject obj = (JSONObject) data;

                EditorRecomendAll editorRecomendAll = DataParser.parseEditorRecommendAll(obj);

                editorRecommendAdapter.setEditorRecommends(editorRecomendAll);
            }
        }
    }
}
