package com.mumu.zhufengfm.app.parsers;

import com.mumu.zhufengfm.app.model.CategoryTagMenu;
import com.mumu.zhufengfm.app.model.DiscoverCategory;
import com.mumu.zhufengfm.app.model.DiscoverRecommends;
import com.mumu.zhufengfm.app.model.DiscoverTab;
import com.mumu.zhufengfm.app.model.discoverrec.*;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend.EditorRecomendAll;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend.EditorRecommendHead;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend.EditorRecommendHeadAndTitle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
 * Date: 15-7-28
 */
public final class DataParser {

    private DataParser(){}


    /**
     * 解析小编推荐的信息
     * @param json
     * @return
     * @throws JSONException
     */
    public static EditorRecomendAll parseEditorRecommendAll(JSONObject json) {
        EditorRecomendAll ret=null;

        if (json != null) {
            ret=new EditorRecomendAll();
            try {
                JSONObject album = json.getJSONObject("album");
                EditorRecommendHead head=new EditorRecommendHead();
                head.parseJSON(album);
                ret.setHead(head);

                JSONObject tracks = json.getJSONObject("tracks");
                EditorRecommendHeadAndTitle list=new EditorRecommendHeadAndTitle();
                list.parseJSON(tracks);
                ret.setList(list);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }
    /**
     * 解析发现推荐数据结构
     * @param json
     * @return
     */

    public static DiscoverRecommends parserDiscoverRecommends(JSONObject json){
        DiscoverRecommends ret=null;

        if (json != null) {

            ret=new DiscoverRecommends();
            try {
                JSONObject discoveryColumns = json.getJSONObject("discoveryColumns");
                DiscoveryColumns columns=new DiscoveryColumns();
                columns.parseJSON(discoveryColumns);
                ret.setDiscoveryColumns(columns);

                JSONObject editorRecommendAlbums = json.getJSONObject("editorRecommendAlbums");
                EditorRecommendAlbums albums=new EditorRecommendAlbums();
                albums.parseJSON(editorRecommendAlbums);
                ret.setEditorRecommendAlbums(albums);

                JSONObject hotRecommends = json.getJSONObject("hotRecommends");
                HotRecommends recommends=new HotRecommends();
                recommends.parseJSON(hotRecommends);
                ret.setHotRecommends(recommends);

                JSONObject focusImages = json.getJSONObject("focusImages");
                FocusImages images=new FocusImages();
                images.parseJSON(focusImages);
                ret.setFocusImages(images);

                JSONObject specialColumn = json.getJSONObject("specialColumn");
                SpecialColumn column=new SpecialColumn();
                column.parseJSON(specialColumn);
                ret.setSpecialColumn(column);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     *
     * @param json
     * @return
     */
    public static List<DiscoverCategory> parseDiscoverCategories(JSONObject json){
        List<DiscoverCategory> ret=null;
        if (json != null) {
            try {
                int code = json.getInt("ret");
                if(code==0){
                    JSONArray jsonArray = json.getJSONArray("list");
                    int len = jsonArray.length();
                    if(len>0){
                        ret=new LinkedList<DiscoverCategory>();
                        for (int i = 0; i < len; i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            DiscoverCategory category=new DiscoverCategory();
                            category.parseJSON(jsonObject);
                            ret.add(category);
                        }
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    /**
     * 解析 CategoryTagMenuTask 返回的JSON结果
     * @param json JSONObject
     * @return List&lt;CategoryTagMenu&gt;
     */
    public static List<CategoryTagMenu> parseCategoryTagMenuResult(JSONObject json){
        List<CategoryTagMenu> ret = null;

        if (json != null) {
            try {
                int code = json.getInt("ret");

                if (code == 0){
                    JSONObject data = json.getJSONObject("data");
                    int category_count = data.getInt("category_count");

                    if(category_count > 0){

                        JSONArray array = data.getJSONArray("category_list");

                        category_count = array.length();

                        if(category_count > 0){

                            ret = new LinkedList<CategoryTagMenu>();

                            for (int i = 0; i < category_count; i++) {
                                JSONObject jsonObject = array.getJSONObject(i);

                                CategoryTagMenu menu = new CategoryTagMenu();

                                //实体类自己解析自己的数据
                                menu.parseJSON(jsonObject);

                                ret.add(menu);
                            }
                            
                        }

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }


    /**
     * 解析 TabsTask 返回的JSON结果
     * @param json JSONObject
     * @return List&lt;DiscoverTab&gt;
     */
    public static List<DiscoverTab> parseDiscoverTab(JSONObject json){
        List<DiscoverTab> ret = null;

        if (json != null) {
            try {
                int code = json.getInt("ret");

                if(code == 0){
                    JSONObject tabs = json.getJSONObject("tabs");

                    int count = tabs.getInt("count");
                    if(count > 0){
                        JSONArray array = tabs.getJSONArray("list");
                        count = array.length();
                        if(count > 0){
                            ret = new LinkedList<DiscoverTab>();

                            for (int i = 0; i < count; i++) {
                                JSONObject obj = array.getJSONObject(i);

                                DiscoverTab tab = new DiscoverTab();

                                tab.parseJSON(obj);

                                ret.add(tab);
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return ret;
    }

}
