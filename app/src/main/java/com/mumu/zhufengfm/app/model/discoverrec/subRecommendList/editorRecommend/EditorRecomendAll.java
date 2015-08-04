package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/8/1.
 */
public class EditorRecomendAll {
    private EditorRecommendHeadAndTitle list;
    private EditorRecommendHead head;

    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {
            head=new EditorRecommendHead();
            head.parseJSON(json);

            list=new EditorRecommendHeadAndTitle();
            list.parseJSON(json);
        }

        Log.d("xxx=RecommendAll==","xxx"+this.toString());
    }

    public EditorRecommendHead getHead() {
        return head;
    }

    public EditorRecommendHeadAndTitle getList() {
        return list;
    }

    public void setHead(EditorRecommendHead head) {

        this.head = head;
    }

    public void setList(EditorRecommendHeadAndTitle list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "EditorRecomendAll{" +
                "head=" + head +
                ", list=" + list +
                '}';
    }
}
