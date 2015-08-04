package com.mumu.zhufengfm.app.model;

/**
 * Created by Administrator on 2015/7/29.
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * 发现的大分类
 */
public class DiscoverCategory implements Comparable<DiscoverCategory>{
    /**
     *  "id": 4,
     "name": "entertainment",
     "title": "综艺娱乐",
     "isChecked": false,
     "orderNum": 3,
     "coverPath": "http://fdfs.xmcdn.com/group7/M09/18/07/wKgDX1VxNA7wl-64AAAH-ITaXnQ496.png",
     "selectedSwitch": false,
     "isFinished": false,
     "contentType": "album"
     */
    private int id;
    private String name;
    private String title;
    private boolean checked;
    private int orderNum;
    private String coverPath;
    private boolean selectedSwitch;
    private boolean finished;
    private String contentType;



    public boolean isChecked() {
        return checked;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public boolean isSelectedSwitch() {
        return selectedSwitch;
    }

    public String getTitle() {
        return title;
    }

    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {
            id=json.getInt("id");
            name=json.optString("name");
            title=json.getString("title");
            checked=json.optBoolean("isChecked");
            orderNum=json.getInt("orderNum");
            coverPath=json.getString("coverPath");
            selectedSwitch=json.optBoolean("selectedSwitch");
            finished=json.optBoolean("isFinished");
            contentType=json.optString("contentType");
        }


    }

    @Override
    public int compareTo(DiscoverCategory another) {
        int ret = 0;
        if (another != null) {
            ret=orderNum - another.orderNum;
        }
        return ret;
    }
}
