package com.mumu.zhufengfm.app.data;

/**
 * Created by Administrator on 2015/7/29.
 */

import com.mumu.zhufengfm.app.model.DiscoverCategory;
import com.mumu.zhufengfm.app.model.DiscoverRecommends;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 公共的数据存储区
 */
public class DataStore {
    private static DataStore ourInstance ;

    private List<DiscoverCategory> discoverCategories;

    private DiscoverRecommends discoverRecommends;

    public static DataStore getInstance() {

        if (ourInstance == null) {
            ourInstance = new DataStore();
        }
        return ourInstance;
    }

    public static void release(){

        ourInstance =  null;

    }

    private DataStore() {
        discoverCategories= new LinkedList<DiscoverCategory>();

        discoverRecommends=new DiscoverRecommends();
    }

    public DiscoverRecommends getDiscoverRecommends() {
        return discoverRecommends;
    }

    public  void setDiscoverCategories(List<DiscoverCategory> categories){
        if(categories!=null && !categories.isEmpty()){
            discoverCategories.clear();
            discoverCategories.addAll(categories);
            Collections.sort(discoverCategories);
        }
    }



    /**
     * 获取已经加载过的分类列表
     * @return
     */
    public List<DiscoverCategory> getDiscoverCategories() {
        return discoverCategories;
    }
}
