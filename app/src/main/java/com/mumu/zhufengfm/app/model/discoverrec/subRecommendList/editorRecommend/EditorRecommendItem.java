package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/8/1.
 * "trackId": 8054513,
 * "uid": 1412917,
 * "playUrl64": "http://fdfs.xmcdn.com/group16/M03/54/86/wKgDalW5_iaRPW7kAUy6yiybY0w504.mp3",
 * "playUrl32": "http://fdfs.xmcdn.com/group16/M07/54/9D/wKgDbFW5_jTBGoBiAKZd0E89T5E280.mp3",
 * "downloadUrl": "http://download.xmcdn.com/group16/M07/54/9D/wKgDbFW5_kDiNxtMAKZhbAKJC0I515.aac",
 * "playPathAacv164": "http://audio.xmcdn.com/group9/M01/54/59/wKgDZlW5_jPj0A-KAW2DaO9VHpI958.m4a",
 * "playPathAacv224": "http://audio.xmcdn.com/group9/M01/54/1F/wKgDYlW5_ifRqQdqAIuC3_pG56Y536.m4a",
 * "downloadAacUrl": "http://audio.xmcdn.com/group9/M01/54/1F/wKgDYlW5_ifRqQdqAIuC3_pG56Y536.m4a",
 * "title": "中国也有一个愚人节[罗辑思维]No.132",
 * "duration": 2962,
 * "processState": 2,
 * "createdAt": 1438252647000,
 * "coverSmall": "http://fdfs.xmcdn.com/group11/M02/36/33/wKgDa1WVdFCBiI1nAAHjHCQ8i2g167_mobile_small.jpg",
 * "coverLarge": "http://fdfs.xmcdn.com/group11/M02/36/33/wKgDa1WVdFCBiI1nAAHjHCQ8i2g167_mobile_large.jpg",
 * "nickname": "罗辑思维脱口秀",
 * "smallLogo": "http://fdfs.xmcdn.com/group5/M07/4A/35/wKgDtlS4cmeAM8R4AAC2jG7vGBo443_mobile_small.jpg",
 * "userSource": 2,
 * "albumId": 239463,
 * "albumTitle": "罗辑思维 全集",
 * "albumImage": "http://fdfs.xmcdn.com/group5/M09/4A/32/wKgDtlS4cZeCzmE2AAHjHCQ8i2g083_mobile_small.jpg",
 * "orderNum": 99999999,
 * "opType": 1,
 * "isPublic": true,
 * "likes": 1581,
 * "playtimes": 290099,
 * "comments": 317,
 * "shares": 0,
 * "status": 1,
 * "downloadSize": 10903916,
 * "downloadAacSize": 9143007
 */
public class EditorRecommendItem {
    private String title;
    private int trackId;
    private int playtimes;
    private int duration;
    private int comments;
    private String playUrl64;

    public void parseJSON(JSONObject json) throws JSONException {
        if (json != null) {
            title = json.getString("title");
            playUrl64 = json.getString("playUrl64");
            trackId = json.getInt("trackId");
            playtimes = json.getInt("playtimes");
            duration = json.getInt("duration");
            comments = json.getInt("comments");
        }
        Log.d("xxx=Recommenditem==", "xxx" + this.toString());
    }

    public int getComments() {
        return comments;
    }

    public int getDuration() {
        return duration;
    }

    public int getPlaytimes() {
        return playtimes;
    }

    public String getPlayUrl64() {
        return playUrl64;
    }

    public String getTitle() {
        return title;
    }

    public int getTrackId() {
        return trackId;
    }

    @Override
    public String toString() {
        return "EditorRecommendItem{" +
                "comments=" + comments +
                ", title='" + title + '\'' +
                ", trackId=" + trackId +
                ", playtimes=" + playtimes +
                ", duration=" + duration +
                ", playUrl64='" + playUrl64 + '\'' +
                '}';
    }
}
