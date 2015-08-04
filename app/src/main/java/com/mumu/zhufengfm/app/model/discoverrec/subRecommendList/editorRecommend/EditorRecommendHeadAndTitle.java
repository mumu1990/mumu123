package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/1.
 */
public class EditorRecommendHeadAndTitle {
    private List<EditorRecommendItem> items;

    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {


            JSONArray list = json.getJSONArray("list");

            int length = list.length();
            items = new LinkedList<EditorRecommendItem>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = list.getJSONObject(i);
                EditorRecommendItem item = new EditorRecommendItem();
                item.parseJSON(jsonObject);
                items.add(item);
            }

            Log.d("xxx=Recommendtitle==", "xxx" + this.toString());
        }
    }


    public List<EditorRecommendItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "EditorRecommendHeadAndTitle{" +
                ", items=" + items +
                '}';
    }
}
