package com.tangyan.zhufengfm.app.util;

/**
 * Created with IntelliJ IDEA.
 * User: tangyan
 * Date: 15-7-28
 */

import java.io.*;
import java.net.HttpURLConnection;

/**
 * I/O 流操作的工具类
 */
public final class StreamUtil {
    private StreamUtil(){}

    public static void close(Object stream){
        if (stream != null) {
            try {
                if(stream instanceof InputStream){
                    ((InputStream)stream).close();
                } else if (stream instanceof OutputStream){
                    ((OutputStream)stream).close();
                } else if (stream instanceof Reader){
                    ((Reader)stream).close();
                } else if (stream instanceof Writer){
                    ((Writer)stream).close();
                } else if (stream instanceof HttpURLConnection){
                    ((HttpURLConnection)stream).disconnect();
                }
            } catch (Exception e){

            }
        }
    }


    /**
     * 将输入流中的数据读出来，存储在字节数组中
     * @param in
     * @return
     */
    public static byte[] readStream(InputStream in) throws IOException {
        byte[] ret = null;

        if (in != null) {
            byte[] buf = new byte[128];

            int len;

            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            while(true){
                len = in.read(buf);

                if(len == -1){
                    break;
                }

                bos.write(buf,0,len);
            }

            buf = null;
            ret = bos.toByteArray();

            bos.close();
        }

        return ret;
    }
}
