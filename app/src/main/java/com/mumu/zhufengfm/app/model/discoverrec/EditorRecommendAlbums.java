package com.mumu.zhufengfm.app.model.discoverrec;

import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.SubEditorRecommendAlbums;
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
public class EditorRecommendAlbums extends BaseRecommend{

    private List<SubEditorRecommendAlbums> editorRecommendAlbumsList;

    public List<SubEditorRecommendAlbums> getEditorRecommendAlbumsList() {
        return editorRecommendAlbumsList;
    }
    public void parseJSON(JSONObject json) throws JSONException {
        super.parseJSON(json);

        JSONArray jsonArray = json.getJSONArray("list");
        int length = jsonArray.length();
        if(length>0){
            editorRecommendAlbumsList=new LinkedList<SubEditorRecommendAlbums>();
            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SubEditorRecommendAlbums subEditorRecommendAlbums=new SubEditorRecommendAlbums();
                subEditorRecommendAlbums.parseJSON(jsonObject);

                editorRecommendAlbumsList.add(subEditorRecommendAlbums);

            }
        }
    }


    @Override
    public String toString() {
        return "EditorRecommendAlbums{" +
                "editorRecommendAlbumsList=" + editorRecommendAlbumsList +
                '}';
    }
}
