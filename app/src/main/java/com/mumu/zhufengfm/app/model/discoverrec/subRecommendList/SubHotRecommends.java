package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList;

import android.util.Log;
import com.mumu.zhufengfm.app.model.discoverrec.BaseRecommend;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/30.
 */
public class SubHotRecommends {
    private List<AlbumRecommends> albumRecommendList;
    private String title;
    private String contentType;
    private boolean finished;
    private int categoryId;
    private int count;
    private boolean hasMore;


    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {

            title = json.getString("title");
            contentType = json.getString("contentType");
            categoryId = json.getInt("categoryId");
            count = json.getInt("count");
            finished = json.getBoolean("isFinished");
            hasMore = json.getBoolean("hasMore");


            JSONArray list = json.getJSONArray("list");
            albumRecommendList = new LinkedList<AlbumRecommends>();
            for (int i = 0; i < list.length(); i++) {
                JSONObject jsonObject = list.getJSONObject(i);
                AlbumRecommends albumRecommends = new AlbumRecommends();
                albumRecommends.parseJSON(jsonObject);
                albumRecommendList.add(albumRecommends);

            }

        }
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getContentType() {
        return contentType;
    }

    public int getCount() {
        return count;
    }

    public boolean isFinished() {
        return finished;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public String getTitle() {
        return title;
    }

    public List<AlbumRecommends> getAlbumRecommendList() {
        return albumRecommendList;
    }

    @Override
    public String toString() {
        return "SubHotRecommends{" +
                "albumRecommendList=" + albumRecommendList +
                ", title='" + title + '\'' +
                ", contentType='" + contentType + '\'' +
                ", finished=" + finished +
                ", categoryId=" + categoryId +
                ", count=" + count +
                ", hasMore=" + hasMore +
                '}';
    }
}
