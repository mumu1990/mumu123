package com.mumu.zhufengfm.app.model.discoverrec;

import android.util.Log;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.AlbumRecommends;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.SubFocusImages;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.SubHotRecommends;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
 * Date: 15-7-29
 * <p/>
 * "title": "听新闻",
 * "contentType": "album",
 * "isFinished": false,
 * "categoryId": 1,
 * "count": 822,
 * "hasMore": true,
 */
public class HotRecommends extends BaseRecommend{

    private List<SubHotRecommends> subHotRecommendsList;

    public List<SubHotRecommends> getSubHotRecommendsList() {
        return subHotRecommendsList;
    }



    public void parseJSON(JSONObject json) throws JSONException {
        super.parseJSON(json);

        if (json != null) {


            JSONArray jsonArray = json.getJSONArray("list");
            int length = jsonArray.length();
            if (length > 0) {
                subHotRecommendsList = new LinkedList<SubHotRecommends>();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SubHotRecommends subHotRecommends = new SubHotRecommends();
                    subHotRecommends.parseJSON(jsonObject);

                    subHotRecommendsList.add(subHotRecommends);

                }
            }

        }
    }


    @Override
    public String toString() {
        return "HotRecommends{" +
                "subHotRecommendsList=" + subHotRecommendsList +
                '}';
    }
}


