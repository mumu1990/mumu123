package com.mumu.zhufengfm.app.model.discoverrec;

import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.SubEditorRecommendAlbums;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.SubFocusImages;
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
public class FocusImages extends BaseRecommend{

    private List<SubFocusImages> subFocusImagesList;

    public List<SubFocusImages> getSubFocusImagesList() {
        return subFocusImagesList;
    }

    public void parseJSON(JSONObject json) throws JSONException {
        super.parseJSON(json);
        if (json != null) {
            JSONArray jsonArray = json.getJSONArray("list");
            int length = jsonArray.length();
            if(length>0){
                subFocusImagesList=new LinkedList<SubFocusImages>();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SubFocusImages subFocusImages=new SubFocusImages();
                    subFocusImages.parseJSON(jsonObject);

                    subFocusImagesList.add(subFocusImages);

                }
            }
        }
    }


    @Override
    public String toString() {
        return "FocusImages{" +
                "subFocusImagesList=" + subFocusImagesList +
                '}';
    }
}
