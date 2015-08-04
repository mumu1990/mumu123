package com.mumu.zhufengfm.app.model.discoverrec.subRecommendList;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/7/30.
 * <p/>
 * "albumId": 343042,
 * "coverLarge": "http://fdfs.xmcdn.com/group6/M0A/E0/7E/wKgDhFUc5qbjBmDbAAAps8N-LbI468_mobile_large.jpg",
 * "title": "重点关注",
 * "tags": "东广新闻台",
 * "tracks": 572,
 * "playsCounts": 100781,
 * "isFinished": 0,
 * "trackId": 7991015,
 * "trackTitle": "“电梯吃人”事故,谁来负责？"
 * },
 */
public class AlbumRecommends {

    private int albumId;
    private String coverLarge;
    private String title;
    private String tags;
    private int tracks;
    private int playsCounts;
    private int finished;
    private int trackId;
    private String trackTitle;

    public void parseJSON(JSONObject json) throws JSONException {

        if (json != null) {
            albumId = json.getInt("albumId");
            tracks = json.getInt("tracks");
            playsCounts = json.getInt("playsCounts");
            finished = json.getInt("isFinished");
            trackId = json.getInt("trackId");
            coverLarge = json.getString("coverLarge");
            title = json.getString("title");
            tags = json.getString("tags");
            trackTitle = json.getString("trackTitle");

        }
    }

    public int getAlbumId() {
        return albumId;
    }

    public String getCoverLarge() {
        return coverLarge;
    }

    public int getFinished() {
        return finished;
    }

    public int getPlaysCounts() {
        return playsCounts;
    }

    public String getTags() {
        return tags;
    }

    public String getTitle() {
        return title;
    }

    public int getTrackId() {
        return trackId;
    }

    public int getTracks() {
        return tracks;
    }

    public String getTrackTitle() {
        return trackTitle;
    }


    @Override
    public String toString() {
        return "AlbumRecommends{" +
                "albumId=" + albumId +
                ", coverLarge='" + coverLarge + '\'' +
                ", title='" + title + '\'' +
                ", tags='" + tags + '\'' +
                ", tracks=" + tracks +
                ", playsCounts=" + playsCounts +
                ", finished=" + finished +
                ", trackId=" + trackId +
                ", trackTitle='" + trackTitle + '\'' +
                '}';
    }
}
