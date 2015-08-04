package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/1.
 *
 *  "albumId": 239463,
 "categoryId": 4,
 "categoryName": "综艺娱乐",
 "title": "罗辑思维 全集",
 "coverOrigin": "http://fdfs.xmcdn.com/group5/M09/4A/32/wKgDtlS4cZeCzmE2AAHjHCQ8i2g083.jpg",
 "coverSmall": "http://fdfs.xmcdn.com/group5/M09/4A/32/wKgDtlS4cZeCzmE2AAHjHCQ8i2g083_mobile_small.jpg",
 "coverLarge": "http://fdfs.xmcdn.com/group5/M09/4A/32/wKgDtlS4cZeCzmE2AAHjHCQ8i2g083_mobile_large.jpg",
 "coverWebLarge": "http://fdfs.xmcdn.com/group5/M09/4A/32/wKgDtlS4cZeCzmE2AAHjHCQ8i2g083_web_large.jpg",
 "createdAt": 1392604228000,
 "updatedAt": 1435857982000,
 "uid": 1412917,
 "nickname": "罗辑思维脱口秀",
 "isVerified": true,
 "avatarPath": "http://fdfs.xmcdn.com/group5/M07/4A/35/wKgDtlS4cmeAM8R4AAC2jG7vGBo443_mobile_small.jpg",
 "intro": "罗振宇的个人视频脱口秀。罗胖读书，讲给您听。我们在知识中寻找独立的见识，您在把玩知识中寻找思维的乐趣。我们的口号是，死磕自己，愉悦大家。    为了大家收听方便，我们把所有节目合并到一起，以后的节目，我们都会在这里持续更新，请继续关注收听罗辑思维。",
 "introRich": "罗振宇的个人视频脱口秀。罗胖读书，讲给您听。我们在知识中寻找独立的见识，您在把玩知识中寻找思维的乐趣。我们的口号是，死磕自己，愉悦大家。<br />\n<br />\n为了大家收听方便，我们把所有节目合并到一起，以后的节目，我们都会在这里持续更新，请继续关注收听罗辑思维。",
 "tags": "罗振宇,脱口秀",
 "tracks": 130,
 "shares": 0,
 "hasNew": false,
 "isFavorite": false,
 "playTimes": 82473586,
 "status": 1,
 "serializeStatus": 0
 */
public class EditorRecommendHead {


    private int albumId;
    private int categoryId;
    private String categoryName;
    private String title;
    private String coverLarge;
    private String coverSmall;
    private String intro;
    private int playTimes;
    private String nickname;
    private String tags;

    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {

            categoryName = json.getString("categoryName");
            title = json.getString("title");
            categoryId = json.getInt("categoryId");
            coverLarge = json.getString("coverLarge");
            coverSmall = json.getString("coverSmall");
            intro = json.getString("intro");
            nickname = json.getString("nickname");
            tags = json.getString("tags");
            playTimes = json.getInt("playTimes");
            albumId = json.getInt("albumId");


        }
        Log.d("xxx=RecommendHead==", "xxx" + this.toString());
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public String getIntro() {
        return intro;
    }

    public String getNickname() {
        return nickname;
    }

    public int getPlayTimes() {
        return playTimes;
    }

    public String getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }


    @Override
    public String toString() {
        return "EditorRecommendHead{" +
                "albumId=" + albumId +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", title='" + title + '\'' +
                ", coverLarge='" + coverLarge + '\'' +
                ", coverSmall='" + coverSmall + '\'' +
                ", intro='" + intro + '\'' +
                ", playTimes=" + playTimes +
                ", nickname='" + nickname + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}

