package com.mumu.zhufengfm.app.model.discoverrec;

import android.util.Log;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.SubDiscoveryColumns;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
 * Date: 15-7-29
 */
public class DiscoveryColumns extends BaseRecommend{

    private List<SubDiscoveryColumns> subDiscoveryColumnsList;

    public List<SubDiscoveryColumns> getSubDiscoveryColumnsList() {
        return subDiscoveryColumnsList;
    }


    public void parseJSON(JSONObject json) throws JSONException {
        super.parseJSON(json);

        JSONArray jsonArray = json.getJSONArray("list");
        subDiscoveryColumnsList=new LinkedList<SubDiscoveryColumns>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            SubDiscoveryColumns subDiscoveryColumns = new SubDiscoveryColumns();
            subDiscoveryColumns.parseJSON(jsonObject);

            subDiscoveryColumnsList.add(subDiscoveryColumns);
        }



    }


    @Override
    public String toString() {
        return "DiscoveryColumns{" +
                "subDiscoveryColumnsList=" + subDiscoveryColumnsList +
                '}';
    }



}
