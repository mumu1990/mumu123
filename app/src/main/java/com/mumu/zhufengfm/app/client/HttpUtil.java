package com.mumu.zhufengfm.app.client;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
 * Date: 15-7-28
 */

import android.os.Build;
import com.mumu.zhufengfm.app.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * 网络工具类，用于 HTTP 的 GET、POST 请求
 */
public final class HttpUtil {

    public static final int TIMEOUT_CONNECT = 5000;
    public static final int TIMEOUT_READ = 5000;

    private HttpUtil(){}

    //////////////////////////

    /**
     * 获取GET 请求，返回字节数组
     * @param url String url
     * @return byte[]
     */
    public static byte[] doGet(String url){
        byte[] ret = null;

        if (url != null) {

            //注意释放连接

            HttpURLConnection conn = null;
            InputStream in = null;

            try {
                URL u = new URL(url);
                conn = (HttpURLConnection) u.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept-Encoding", "gzip"); //设置HTTP 请求头
                conn.setRequestProperty("User-Agent","ting_4.1.7(MX4, "+ Build.VERSION.SDK_INT + ")");

                //设置联网超时，只能够用于短时间的联网操作
                //长时间的自己再重新写

                //Socket 打开连接的时间
                conn.setConnectTimeout(TIMEOUT_CONNECT);
                //连接打开之后，可以读取数据多长时间
                conn.setReadTimeout(TIMEOUT_READ);

                ///////////////////////////////
                //连接属性

                //设置自动处理 302、307跳转 通常会返回200
                conn.setInstanceFollowRedirects(true);

                //连接
                conn.connect();

                //状态码
                int code = conn.getResponseCode();

                if(code == 200){

                    in = conn.getInputStream();

                    //获取服务器头信息，内容是否压缩

                    //获取指定的服务器返回的头信息
                    String contentEncoding = conn.getHeaderField("Content-Encoding");
                    if (contentEncoding == null) {
                        contentEncoding = conn.getHeaderField("content-encoding");
                    }

                    if (contentEncoding != null && contentEncoding.equals("gzip")) {
                        //代表数据经过压缩
                        //使用GZIPInputStream 解压缩
                        in = new GZIPInputStream(in);
                    }

                    //读 in
                    ret = StreamUtil.readStream(in);

                } else {
                    // TODO 其他情况
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtil.close(in);
                StreamUtil.close(conn);
            }
        }

        return ret;
    }
}
