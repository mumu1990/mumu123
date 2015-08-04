package com.mumu.zhufengfm.app.model.discoverrec;

import android.util.Log;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.SubHotRecommends;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.SubSpecialColumn;
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
 * "columnType": 1,
 * "specialId": 348,
 * "title": "5个如雷贯耳的脱口秀，高智商必听！",
 * "subtitle": "人生不应该只有眼前的苟且，还有诗和远方！\r\n生活从来就不应该只有简单的吃饭穿衣，安身立命，人生除了温饱，还应该有另一个精神的高度，最终决定这个高度的，一定是眼界和见识，以及你碰到一个什么样的人生导师",
 * "footnote": "5张专辑",
 * "coverPath": "http://fdfs.xmcdn.com/group14/M04/4E/68/wKgDY1WyIl_jgde2AAG79hZ3Ml4209_mobile_small.jpg",
 * "contentType": "1"
 */
public class SpecialColumn extends BaseRecommend{
    private List<SubSpecialColumn>subSpecialColumnList;

    public List<SubSpecialColumn> getSubSpecialColumnList() {
        return subSpecialColumnList;
    }

    public void parseJSON(JSONObject json) throws JSONException {
        super.parseJSON(json);

        if (json != null) {
            JSONArray jsonArray = json.getJSONArray("list");
            int length = jsonArray.length();
            if (length > 0) {
                subSpecialColumnList = new LinkedList<SubSpecialColumn>();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    SubSpecialColumn subSpecialColumn = new SubSpecialColumn();
                    subSpecialColumn.parseJSON(jsonObject);

                    subSpecialColumnList.add(subSpecialColumn);

                }                                         
            }

        }
    }


    @Override
    public String toString() {
        return "SpecialColumn{" +
                "subSpecialColumnList=" + subSpecialColumnList +
                '}';
    }
}
