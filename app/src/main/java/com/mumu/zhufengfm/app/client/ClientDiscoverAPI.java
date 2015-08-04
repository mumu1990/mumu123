package com.mumu.zhufengfm.app.client;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
 * Date: 15-7-28
 */

import java.io.UnsupportedEncodingException;

/**
 * “发现”部分的API接口调用
 */
public final class ClientDiscoverAPI {

    public static final String SERVER_MOBILE = "http://mobile.ximalaya.com";

    private ClientDiscoverAPI(){}

    ////////////////////////////////

    /**
     * 获取发现界面Tab的定义<br/>
     * 接口：http://mobile.ximalaya.com/mobile/discovery/v1/tabs?device=android<br/>
     * 参数：无<br/>
     * @return
     */
    public  static String getDiscoverTabs(){
        String ret=null;
        //RESTful
        String url=SERVER_MOBILE+"/mobile/discovery/v1/tabs?device=android";

        byte[] bytes = HttpUtil.doGet(url);
        if (bytes != null) {
            try {
                ret=new String(bytes,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                ret=new String(bytes);
            }
        }
        return ret;
    }


    /**
     * 获取分类Tag 菜单<br/>
     * 调用接口：http://mobile.ximalaya.com/m/category_tag_menu<br/>
     * 请求方法：GET<br/>
     *
     *
     * @param type 可选 默认是 user
     * @return
     */
    public static String getCategoryTagMenu(String type){
        String ret = null;

        String url = null;

        StringBuilder sb = new StringBuilder();

        sb.append(SERVER_MOBILE).append("/m/category_tag_menu");

        if (type != null) {
            sb.append("?type=").append(type);
            sb.append("&device=android");
        }
        url = sb.toString();
        sb = null;

        ret = getJSONString(url);

        return ret;
    }

    /**
     * 获取发现栏目推荐部分 小编推荐的信息
     * URL:http://mobile.ximalaya.com/mobile/others/ca/album/track/239463
     * /true/1/20?device=android&pageSize=20&albumId=239463&isAsc=true
     * @return
     */
    public static String getEditorRecommendItem(String albumId) {
        String ret = null;

        String url=null;

        StringBuilder sb = new StringBuilder();
        sb.append(SERVER_MOBILE).append("/mobile/others/ca/album/track/").
                append(albumId).append("/true/1/20?device=android&pageSize=20&albumId=")
                .append(albumId).append("&isAsc=true");

         url = sb.toString();

        byte[] bytes = HttpUtil.doGet(url);
        if (bytes != null) {
            try {
                ret=new String(bytes,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                ret=new String(bytes);
            }
        }

        return ret;
    }


    /**
     * 获取手机应用设置信息<br/>
     * 调用接口：http://mobile.ximalaya.com/mobile/switch/app_set?device=android<br/>
     * 请求方法：GET<br/>
     *
     * @return
     */
    public static String getAppSet(){

        String url = SERVER_MOBILE + "/mobile/switch/app_set?device=android";

        String ret = getJSONString(url);

        return ret;
    }


    /**
     * 获取“猜你喜欢”信息<br/>
     * 调用接口：http://mobile.ximalaya.com/mobile/discovery/v1/recommend/guessYouLike/unlogin<br/>
     * 请求方法：GET<br/>
     *
     * @param pageSize
     * @param pageId
     * @return
     */
    public static String getYouLike(int pageSize, int pageId){

        StringBuilder sb = new StringBuilder();

        sb.append(SERVER_MOBILE).append("/mobile/discovery/v1/recommend/guessYouLike/unlogin");

        if (pageSize > 0 && pageId > 0) {
            sb.append("?pageSize=").append(pageSize);
            sb.append("&pageId=").append(pageId);
            sb.append("&device=android");
        }
        String url = sb.toString();
        sb = null;

        String ret = getJSONString(url);

        return ret;
    }


    /**
     * 获取“发现”页面上方Tab 按钮信息<br/>
     * 调用接口：http://mobile.ximalaya.com/mobile/discovery/v1/tabs?device=android<br/>
     * 请求方法：GET<br/>
     *
     * @return
     */
    public static String getTabs(){
        String url = SERVER_MOBILE + "/mobile/discovery/v1/tabs?device=android";

        String ret = getJSONString(url);

        return ret;
    }


    /**
     * 联网获取JSON 字符串方法
     * @param url
     * @return
     */
    private static String getJSONString(String url) {
        String ret = null;
        byte[] data = HttpUtil.doGet(url);

        if (data != null) {
            try {
                ret = new String(data,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                ret = new String(data);
            }
        }
        return ret;
    }

    /**
     * 获取发现的分类<br/>
     * 调用的接口<br/>
     * http://mobile.ximalaya.com/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2<br/>
     *
     * @return
     */
    public static String getDiscoverCategories() {
        String ret = null;
        String url=SERVER_MOBILE +
                "/mobile/discovery/v1/categories"
                +"?device=android"
                +"&picVersion=10"
                +"&scale=2";

        byte[] bytes = HttpUtil.doGet(url);
        if (bytes != null) {

            try {
                ret=new String(bytes,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                ret=new String(bytes);
            }
        }
        return ret;
    }

    /**
     * 获取发现部分推荐栏目的信息<br/>
     * 调用接口：<br/>
     * http://mobile.ximalaya.com/mobile/discovery/v1/recommends
     * ?channel=and-f6&device=android&includeActivity=true&includeSpecial=true&scale=2&version=4.1.7.1
     * 参数：<br/>
     * <ul>
     *<li>channel=and-f6</li>
     *<li> device=android</li>
     *<li>includeActivity=true</li>
     *<li>includeSpecial=true</li>
     *<li>scale=2</li>
     *<li>version=4.1.7.1</li>
     * </ul>
     * @return
     */
    public static String getDiscoverRecommend(){
        String ret=null;
        String url=SERVER_MOBILE+"/mobile/discovery/v1/recommends"
                +"?channel=and-f6"
                +"&device=android"
                +"&includeActivity=true"
                +"&includeSpecial=true"
                +"&scale=2"
                +"&version=4.1.7.1";

        byte[] bytes = HttpUtil.doGet(url);
        if (bytes != null) {
            try {
                ret=new String(bytes,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                ret=new String(bytes);
            }
        }
        return ret;
    }


}
