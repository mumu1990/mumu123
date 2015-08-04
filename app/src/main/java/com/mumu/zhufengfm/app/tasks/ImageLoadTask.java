package com.mumu.zhufengfm.app.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.mumu.zhufengfm.app.cache.FileCache;
import com.mumu.zhufengfm.app.cache.MemoryCache;
import com.mumu.zhufengfm.app.client.HttpUtil;
import com.mumu.zhufengfm.app.util.ImageUtil;

/**
 * Created by Administrator on 2015/7/31.
 */

/**
 * 下载图片的异步任务
 */
public class ImageLoadTask extends AsyncTask<String, Integer, Bitmap> {

    /**
     * 当前任务要设置的目标
     */

    private ImageView imageView;
    private String url;

    public ImageLoadTask(ImageView imageView) {
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap ret = null;
        if (params != null && params.length > 0) {
            url = params[0];
            //1.
            ret = MemoryCache.getInstance().getBitmap(url);
            if (ret == null) {

                byte[] data = FileCache.getInstance().loadFile(url);
                if (data == null) {
                    data = HttpUtil.doGet(url);

                    //文件缓存
                    FileCache.getInstance().saveFile(url, data);
                }

                //1.文件缓存
                //2.图片的内存缓存
                //3.图片的二次采样
                // 因此要存储字节数组
                if (data != null) {

                    //TODOD 进行采样
                    //1.只获取尺寸
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;

                    //2计算原始尺寸和目标尺寸的采样比率
                    //3.设置options 为实际解析图片，并且设置采样比率

                    options.inJustDecodeBounds = false;

                    options.inPurgeable = true;

                    //设置解码器可以使用的解码像素颜色配置
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;

                    options.inSampleSize = ImageUtil.calculateInSampleSize(options, imageView.getWidth(), imageView.getHeight());

                    //TODO 保存文件
                    //TODO 转换图片 bitmap
                    ret = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                    data = null;
                    MemoryCache.getInstance().putBitmap(url, ret);
                }


            }

            byte[] data = FileCache.getInstance().loadFile(url);
            if (data == null) {
                data = HttpUtil.doGet(url);

                //文件缓存
                FileCache.getInstance().saveFile(url, data);
            }

            //1.文件缓存
            //2.图片的二次采样
            //3.图片的内存缓存
            // 因此要存储字节数组
            if (data != null) {
                //TODO 保存文件
                //TODO 转换图片 bitmap
                ret = BitmapFactory.decodeByteArray(data, 0, data.length);

                data = null;
            }


        }
        return ret;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            if (imageView != null) {
                Object tag = imageView.getTag();
                if (tag != null) {
                    String str = null;
                    if (tag instanceof String) {
                        str = (String) tag;
                    } else if (tag instanceof String[]) {
                        String[] ss = (String[]) tag;
                        str = ss[0];
                    }if (str.equals(url)) {
                        imageView.setImageBitmap(bitmap);
                    }

                }
            }
        }
    }
}
