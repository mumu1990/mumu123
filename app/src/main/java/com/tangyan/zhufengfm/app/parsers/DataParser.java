package com.tangyan.zhufengfm.app.parsers;

import android.app.ActionBar;
import com.tangyan.zhufengfm.app.model.CategoryTagMenu;
import com.tangyan.zhufengfm.app.model.DiscoverTab;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tangyan
 * Date: 15-7-28
 */
public final class DataParser {

    private DataParser(){}

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
