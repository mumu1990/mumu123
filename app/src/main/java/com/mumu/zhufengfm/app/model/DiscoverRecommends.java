package com.mumu.zhufengfm.app.model;

import android.util.Log;
import com.mumu.zhufengfm.app.model.discoverrec.*;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
 * Date: 15-7-29
 */


/**
 * “发现”页面
 */
public class DiscoverRecommends  {

    private DiscoveryColumns discoveryColumns;
    private EditorRecommendAlbums editorRecommendAlbums;
    private FocusImages focusImages;
    private HotRecommends hotRecommends;
    private SpecialColumn specialColumn;

    public DiscoveryColumns getDiscoveryColumns() {
        return discoveryColumns;
    }

    public EditorRecommendAlbums getEditorRecommendAlbums() {
        return editorRecommendAlbums;
    }

    public FocusImages getFocusImages() {
        return focusImages;
    }

    public HotRecommends getHotRecommends() {
        return hotRecommends;
    }

    public SpecialColumn getSpecialColumn() {
        return specialColumn;
    }

    public void setDiscoveryColumns(DiscoveryColumns discoveryColumns) {
        this.discoveryColumns = discoveryColumns;
    }

    public void setEditorRecommendAlbums(EditorRecommendAlbums editorRecommendAlbums) {
        this.editorRecommendAlbums = editorRecommendAlbums;
    }

    public void setFocusImages(FocusImages focusImages) {
        this.focusImages = focusImages;
    }

    public void setHotRecommends(HotRecommends hotRecommends) {
        this.hotRecommends = hotRecommends;
    }

    public void setSpecialColumn(SpecialColumn specialColumn) {
        this.specialColumn = specialColumn;
    }

    public void parseJSON(JSONObject json) throws JSONException {


        if (json != null) {
            discoveryColumns=new DiscoveryColumns();
            discoveryColumns.parseJSON(json);



            editorRecommendAlbums=new EditorRecommendAlbums();
            editorRecommendAlbums.parseJSON(json);


            focusImages=new FocusImages();
            focusImages.parseJSON(json);


            hotRecommends=new HotRecommends();
            hotRecommends.parseJSON(json);


            specialColumn=new SpecialColumn();
            specialColumn.parseJSON(json);


        }
    }

    @Override
    public String toString() {
        return "DiscoverRecommends{" +
                "discoveryColumns=" + discoveryColumns +
                ", editorRecommendAlbums=" + editorRecommendAlbums +
                ", focusImages=" + focusImages +
                ", hotRecommends=" + hotRecommends +
                ", specialColumn=" + specialColumn +
                '}';
    }
}
