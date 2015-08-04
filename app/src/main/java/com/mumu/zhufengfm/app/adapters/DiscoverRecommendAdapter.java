package com.mumu.zhufengfm.app.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.mumu.zhufengfm.app.R;
import com.mumu.zhufengfm.app.model.DiscoverRecommends;
import com.mumu.zhufengfm.app.model.discoverrec.DiscoveryColumns;
import com.mumu.zhufengfm.app.model.discoverrec.EditorRecommendAlbums;
import com.mumu.zhufengfm.app.model.discoverrec.HotRecommends;
import com.mumu.zhufengfm.app.model.discoverrec.SpecialColumn;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.*;
import com.mumu.zhufengfm.app.tasks.ImageLoadTask;

import java.util.List;

/**
 * Created by Administrator on 2015/7/30.
 */

/**
 * 发现部分 推荐列表的适配器
 * 需要支持多布局的处理
 */
public class DiscoverRecommendAdapter extends BaseAdapter {

    private Context context;
    /**
     * 从接口获取的discoverRecommends内容（完整的推荐）
     */
    private DiscoverRecommends recommends;
    /**
     * 更多/专辑/点击的操作接口
     */
    private View.OnClickListener onClickListener;

    public DiscoverRecommendAdapter(View.OnClickListener onClickListener, Context context) {
        this.onClickListener = onClickListener;
        this.context = context;
    }

    /**
     * 设置实际的数据<br/>
     * 这个方法需要在主线程调用更新
     *
     * @param recommends
     */
    public void setRecommends(DiscoverRecommends recommends) {
        this.recommends = recommends;
        notifyDataSetChanged();
    }


    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (recommends != null) {
            //3是“小编推荐”，“精品听单”，“发现新奇”
            int hotCount = 0;
            HotRecommends hotRecommends = recommends.getHotRecommends();
            if (hotRecommends != null) {
                List<SubHotRecommends> list = hotRecommends.getSubHotRecommendsList();
                if (list != null) {

                    hotCount = list.size();
                }
            }
            ret = 3 + hotCount;
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        Object ret = null;
        switch (position) {
            case 0:
                ret = recommends.getEditorRecommendAlbums();
                break;
            case 1:
                ret = recommends.getSpecialColumn();
                break;
            case 2:
                ret = recommends.getDiscoveryColumns();
                break;
            default:
                HotRecommends hotRecommends = recommends.getHotRecommends();
                if (hotRecommends != null) {
                    ret = hotRecommends.getSubHotRecommendsList().get(position - 3);
                }
                break;
        }
        return ret;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;

        Object item = getItem(position);
        int itemViewType = getItemViewType(position);

        switch (itemViewType) {
            case 0:
                ret = bindEditorRecommendView(item, convertView, parent);
                break;
            case 1:
                ret = bindSpecialColumnView(item, convertView, parent);
                break;
            case 2:
                ret = bindDiscoverColumnsView(item, convertView, parent);
                break;
            case 3:
                ret = bindHotColumnView(item, convertView, parent);
                break;

        }


        return ret;
    }

    private View bindDiscoverColumnsView(Object item, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        } else {
            ret = LayoutInflater.from(context)
                    .inflate(R.layout.item_recommend_discov, parent, false);

        }


        DiscoverColumnsViewHolder holder = (DiscoverColumnsViewHolder) ret.getTag();

        if (holder == null) {
            holder = new DiscoverColumnsViewHolder();
            //TODO 加载 View
            holder.txtTitle = (TextView) ret.findViewById
                    (R.id.item_recommend_discov_title);

            /////////////////////////////////////////设置更多的点击事件

            holder.block0 = (RelativeLayout) ret.findViewById
                    (R.id.item_recommend_discov_block0);
            holder.block1 = (RelativeLayout) ret.findViewById
                    (R.id.item_recommend_discov_block1);
            holder.block2 = (RelativeLayout) ret.findViewById
                    (R.id.item_recommend_discov_block2);
            holder.block3 = (RelativeLayout) ret.findViewById
                    (R.id.item_recommend_discov_block3);


            holder.block0ImageButton = (ImageButton) holder.block0.getChildAt(0);
            holder.block0ImageButton.setOnClickListener(onClickListener);
            holder.block0TitleText = (TextView) holder.block0.getChildAt(1);
            holder.block0SubTitle = (TextView) holder.block0.getChildAt(2);


            holder.block1ImageButton = (ImageButton) holder.block1.getChildAt(0);
            holder.block1ImageButton.setOnClickListener(onClickListener);
            holder.block1TitleText = (TextView) holder.block1.getChildAt(1);
            holder.block1SubTitle = (TextView) holder.block1.getChildAt(2);

            holder.block2ImageButton = (ImageButton) holder.block2.getChildAt(0);
            holder.block2ImageButton.setOnClickListener(onClickListener);
            holder.block2TitleText = (TextView) holder.block2.getChildAt(1);
            holder.block2SubTitle = (TextView) holder.block2.getChildAt(2);

            holder.block3ImageButton = (ImageButton) holder.block3.getChildAt(0);
            holder.block3ImageButton.setOnClickListener(onClickListener);
            holder.block3TitleText = (TextView) holder.block3.getChildAt(1);
            holder.block3SubTitle = (TextView) holder.block3.getChildAt(2);


            ret.setTag(holder);
        }

        DiscoveryColumns discov = (DiscoveryColumns) item;
        String title = discov.getTitle();
        holder.txtTitle.setText(title);


        List<SubDiscoveryColumns> list = discov.getSubDiscoveryColumnsList();
        if (list != null) {
            int size = list.size();
            if (size > 4) {
                size = 4;
            }

            for (int i = 0; i < size; i++) {

                SubDiscoveryColumns columns = list.get(i);

                //图片的网址
                String coverPath = columns.getCoverPath();
                String tit = columns.getTitle();
                String subtitle = columns.getSubtitle();


                ImageView imageView = null;

                switch (i) {
                    case 0:
                        //TODO 需要显示图片
                        holder.block0TitleText.setText(tit);
                        holder.block0SubTitle.setText(subtitle);
                        imageView = holder.block0ImageButton;
                        break;
                    case 1:
                        //TODO 需要显示图片
                        holder.block1TitleText.setText(tit);
                        holder.block1SubTitle.setText(subtitle);
                        imageView = holder.block1ImageButton;
                        break;
                    case 2:
                        //TODO 需要显示图片
                        holder.block2TitleText.setText(tit);
                        holder.block2SubTitle.setText(subtitle);
                        imageView = holder.block2ImageButton;
                        break;
                    case 3:
                        //TODO 需要显示图片
                        holder.block3TitleText.setText(tit);
                        holder.block3SubTitle.setText(subtitle);
                        imageView = holder.block3ImageButton;
                        break;
                }

                if (imageView != null && coverPath != null) {

                    //设置ImageView 的Tag ，在异步任务中需要检查这个Tag

                    imageView.setImageResource(R.mipmap.ic_launcher);
                    imageView.setTag(coverPath);
                    ImageLoadTask task = new ImageLoadTask(imageView);

                    //手机版本的适配
                    if (Build.VERSION.SDK_INT >= 11) {
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, coverPath);
                    } else {
                        task.execute(coverPath);
                    }
                }
            }

        }
        return ret;

    }

    /**
     * 精品听单
     *
     * @param item
     * @param convertView
     * @param parent
     * @return
     */
    private View bindSpecialColumnView(Object item, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        } else {
            ret = LayoutInflater.from(context)
                    .inflate(R.layout.item_recommend_special, parent, false);

        }

        SpecialViewHolder holder = (SpecialViewHolder) ret.getTag();

        if (holder == null) {
            holder = new SpecialViewHolder();
            //TODO 加载 View
            holder.txtTitle = (TextView) ret.findViewById
                    (R.id.item_recommend_special_title);
            holder.txtMore = (TextView) ret.findViewById
                    (R.id.item_recommend_special_more);

            /////////////////////////////////////////设置更多的点击事件
            holder.txtMore.setOnClickListener(onClickListener);
            holder.block0 = (RelativeLayout) ret.findViewById
                    (R.id.item_recommend_special_block0);
            holder.block1 = (RelativeLayout) ret.findViewById
                    (R.id.item_recommend_special_block1);


            holder.block0ImageButton = (ImageButton) holder.block0.getChildAt(0);
            holder.block0ImageButton.setOnClickListener(onClickListener);
            holder.block0TitleText = (TextView) holder.block0.getChildAt(1);
            holder.block0SubTitle = (TextView) holder.block0.getChildAt(2);
            holder.block0foot = (TextView) holder.block0.getChildAt(3);

            holder.block1ImageButton = (ImageButton) holder.block1.getChildAt(0);
            holder.block1ImageButton.setOnClickListener(onClickListener);
            holder.block1TitleText = (TextView) holder.block1.getChildAt(1);
            holder.block1SubTitle = (TextView) holder.block1.getChildAt(2);
            holder.block1foot = (TextView) holder.block1.getChildAt(3);


            ret.setTag(holder);
        }

        SpecialColumn special = (SpecialColumn) item;
        String title = special.getTitle();
        holder.txtTitle.setText(title);
///////////////////////////////////////////////////////////
        boolean hasMore = special.isHasMore();
        if (hasMore) {
            holder.txtMore.setVisibility(View.VISIBLE);
        } else {
            holder.txtMore.setVisibility(View.INVISIBLE);
        }
        ///////////////////////////////////////////////////////////

        List<SubSpecialColumn> list = special.getSubSpecialColumnList();
        if (list != null) {
            int size = list.size();
            if (size > 2) {
                size = 2;
            }

            for (int i = 0; i < size; i++) {

                SubSpecialColumn column = list.get(i);

                //图片的网址
                String coverPath = column.getCoverPath();
                String tit = column.getTitle();
                String subtitle = column.getSubtitle();
                String footnote = column.getFootnote();

                ImageView imageView = null;

                switch (i) {
                    case 0:
                        //TODO 需要显示图片
                        holder.block0TitleText.setText(tit);
                        holder.block0SubTitle.setText(subtitle);
                        holder.block0foot.setText(footnote);
                        imageView = holder.block0ImageButton;
                        break;
                    case 1:
                        //TODO 需要显示图片
                        holder.block1TitleText.setText(tit);
                        holder.block1SubTitle.setText(subtitle);
                        holder.block1foot.setText(footnote);
                        imageView = holder.block1ImageButton;
                        break;
                }

                if (imageView != null && coverPath != null) {

                    //设置ImageView 的Tag ，在异步任务中需要检查这个Tag

                    imageView.setImageResource(R.mipmap.ic_launcher);
                    imageView.setTag(coverPath);
                    ImageLoadTask task = new ImageLoadTask(imageView);

                    //手机版本的适配
                    if (Build.VERSION.SDK_INT >= 11) {
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, coverPath);
                    } else {
                        task.execute(coverPath);
                    }
                }
            }

        }
        return ret;
    }


    private static class HotRecommendViewHolder {

        public TextView txtTitle;
        public TextView txtMore;
        /**
         * 三块图片文字布局
         */
        public ViewGroup[] blocks;
    }

    /**
     * 热门推荐 之前写的代码
     *
     * @param item
     * @param convertView
     * @param parent
     * @return
     */
    private View bindHotColumnView(Object item, View convertView, ViewGroup parent) {
        View ret = null;

        if (convertView != null) {
            ret = convertView;
        } else {
            ret = LayoutInflater.from(context).
                    inflate(R.layout.item_recommend_album, parent, false);
        }
        HotRecommendViewHolder holder = (HotRecommendViewHolder) ret.getTag();
        if (holder == null) {
            holder = new HotRecommendViewHolder();

            holder.txtTitle =
                    (TextView) ret.findViewById(R.id.item_recommend_album_title);
            holder.txtMore =
                    (TextView) ret.findViewById(R.id.item_recommend_album_more);

            holder.txtMore.setOnClickListener(onClickListener);

            holder.blocks = new ViewGroup[3];
            holder.blocks[0] =
                    (ViewGroup) ret.findViewById(R.id.item_recommend_album_block0);
            holder.blocks[1] =
                    (ViewGroup) ret.findViewById(R.id.item_recommend_album_block1);
            holder.blocks[2] =
                    (ViewGroup) ret.findViewById(R.id.item_recommend_album_block2);

            ret.setTag(holder);
        }
        ////////////////////////////////////////////////////////

        SubHotRecommends hot = (SubHotRecommends) item;
        String title = hot.getTitle();
        holder.txtTitle.setText(title);
        boolean hasMore = hot.isHasMore();

        //对于热门推荐“更多”点击时 对象中包含了CategoryId，通过它作为点击事件的入口

        holder.txtMore.setTag("hotRecommend" + ":" + hot.getCategoryId());

        if (hasMore) {
            holder.txtMore.setVisibility(View.VISIBLE);
        } else {
            holder.txtMore.setVisibility(View.INVISIBLE);
        }


//////////////////////////////////////////////////////////////
        List<AlbumRecommends> list = hot.getAlbumRecommendList();
        if (list != null) {
            int size = list.size();
            if (size > 3) {
                size = 3;
            }

            for (int i = 0; i < size; i++) {

                ViewGroup block = holder.blocks[i];
                ImageButton img = (ImageButton) block.getChildAt(0);

                AlbumRecommends recommends = list.get(i);
                String coverLarge = recommends.getCoverLarge();

                boolean needLoad = true;
                Object tag = img.getTag();
                if (tag != null) {
                    if (tag instanceof String) {
                        String s = (String) tag;
                        if (s.equals(coverLarge)) {
                            needLoad = false;
                        }
                    } else if (tag instanceof String[]) {
                        String[] ss = (String[]) tag;
                        if (ss.length > 0) {
                            String s = ss[0];
                            if (s.equals(coverLarge)) {
                                needLoad = false;
                            }
                        }
                    }

                }
                if (needLoad) {
                    //设置“图片加载中显示”

                    img.setImageResource(R.mipmap.ic_launcher);
                }


                img.setOnClickListener(onClickListener);
                TextView txt = (TextView) block.getChildAt(1);

                //TODO 加载图片
                String trackTitle = recommends.getTrackTitle();
                txt.setText(trackTitle);


                //用于在异步任务进行图片识别，避免加载错位
                //       img.setTag(coverLarge);

                //设置字符串数据Tag  索引0 用于ImageView 的图片错位问题
                //其余两个用于用户ImageView 点击事件的处理
                img.setTag(new String[]{coverLarge,
                        Integer.toString(recommends.getAlbumId()),
                        Integer.toString(recommends.getTrackId())

                });

                if (coverLarge != null && needLoad) {

                    ImageLoadTask task = new ImageLoadTask(img);

                    //手机版本的适配
                    if (Build.VERSION.SDK_INT >= 11) {
                        task.executeOnExecutor
                                (AsyncTask.THREAD_POOL_EXECUTOR, coverLarge);
                    } else {
                        task.execute(coverLarge);
                    }
                }
            }
        }
        return ret;
    }

    /**
     * 小编推荐
     *
     * @param item
     * @param convertView
     * @param parent
     * @return
     */

    private View bindEditorRecommendView(Object item, View convertView, ViewGroup parent) {
        View ret = null;
        if (convertView != null) {
            ret = convertView;
        } else {
            ret = LayoutInflater.from(context)
                    .inflate(R.layout.item_recommend_album, parent, false);

        }

        AlbumRecommendViewHolder holder = (AlbumRecommendViewHolder) ret.getTag();

        if (holder == null) {
            holder = new AlbumRecommendViewHolder();
            //TODO 加载 View
            holder.txtTitle = (TextView) ret.findViewById
                    (R.id.item_recommend_album_title);
            holder.txtMore = (TextView) ret.findViewById
                    (R.id.item_recommend_album_more);

            /////////////////////////////////////////设置更多的点击事件
            holder.txtMore.setOnClickListener(onClickListener);
            holder.block0 = (LinearLayout) ret.findViewById
                    (R.id.item_recommend_album_block0);
            holder.block1 = (LinearLayout) ret.findViewById
                    (R.id.item_recommend_album_block1);
            holder.block2 = (LinearLayout) ret.findViewById
                    (R.id.item_recommend_album_block2);

            holder.block0ImageButton = (ImageButton) holder.block0.getChildAt(0);
            holder.block0ImageButton.setOnClickListener(onClickListener);
            holder.block0TextView = (TextView) holder.block0.getChildAt(1);

            holder.block1ImageButton = (ImageButton) holder.block1.getChildAt(0);
            holder.block1ImageButton.setOnClickListener(onClickListener);
            holder.block1TextView = (TextView) holder.block1.getChildAt(1);

            holder.block2ImageButton = (ImageButton) holder.block2.getChildAt(0);
            holder.block2ImageButton.setOnClickListener(onClickListener);
            holder.block2TextView = (TextView) holder.block2.getChildAt(1);

            ret.setTag(holder);
        }

        EditorRecommendAlbums albums = (EditorRecommendAlbums) item;
        String title = albums.getTitle();
        holder.txtTitle.setText(title);
///////////////////////////////////////////////////////////
        boolean hasMore = albums.isHasMore();

        holder.txtMore.setTag("editor");
        if (hasMore) {
            holder.txtMore.setVisibility(View.VISIBLE);
        } else {
            holder.txtMore.setVisibility(View.INVISIBLE);
        }
        ///////////////////////////////////////////////////////////

        List<SubEditorRecommendAlbums> list = albums.getEditorRecommendAlbumsList();
        if (list != null) {
            int size = list.size();
            if (size > 3) {
                size = 3;
            }

            for (int i = 0; i < size; i++) {

                SubEditorRecommendAlbums recommend = list.get(i);

                //图片的网址
                String coverLarge = recommend.getCoverLarge();
                String tit = recommend.getTrackTitle();

                ImageView imageView = null;

                switch (i) {
                    case 0:
                        //TODO 需要显示图片
                        holder.block0TextView.setText(tit);
                        imageView = holder.block0ImageButton;
                        break;
                    case 1:
                        //TODO 需要显示图片
                        holder.block1TextView.setText(tit);
                        imageView = holder.block1ImageButton;
                        break;
                    case 2:
                        //TODO 需要显示图片
                        holder.block2TextView.setText(tit);
                        imageView = holder.block2ImageButton;
                        break;
                }

                if (imageView != null && coverLarge != null) {

                    //设置ImageView 的Tag ，在异步任务中需要检查这个Tag

                    imageView.setImageResource(R.mipmap.ic_launcher);

                    imageView.setTag(new String[]{coverLarge,
                            Integer.toString(recommend.getAlbumId()),
                            Integer.toString(recommend.getTrackId())

                    });
                   // imageView.setTag(coverLarge);
                    ImageLoadTask task = new ImageLoadTask(imageView);

                    //手机版本的适配
                    if (Build.VERSION.SDK_INT >= 11) {
                        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, coverLarge);
                    } else {
                        task.execute(coverLarge);
                    }
                }
            }

        }
        return ret;
    }

    /**
     * 小编推荐 热门推荐使用的
     */
    public static class AlbumRecommendViewHolder {

        public TextView txtTitle;
        public TextView txtMore;
        public LinearLayout block0;
        public LinearLayout block1;
        public LinearLayout block2;

        //////////////每一个Block的子内容
        public ImageButton block0ImageButton;
        public TextView block0TextView;
        public ImageButton block1ImageButton;
        public TextView block1TextView;
        public ImageButton block2ImageButton;
        public TextView block2TextView;
    }

    /**
     * 精品听单
     */
    public static class SpecialViewHolder {

        public TextView txtTitle;
        public TextView txtMore;
        public RelativeLayout block0;
        public RelativeLayout block1;

        //////////////每一个Block的子内容
        public ImageButton block0ImageButton;
        public TextView block0TitleText;
        public TextView block0SubTitle;
        public TextView block0foot;

        public ImageButton block1ImageButton;
        public TextView block1TitleText;
        public TextView block1SubTitle;
        public TextView block1foot;


    }

    /**
     * 发现新奇
     */
    public static class DiscoverColumnsViewHolder {

        public TextView txtTitle;
        public RelativeLayout block0;
        public RelativeLayout block1;
        public RelativeLayout block2;
        public RelativeLayout block3;


        //////////////每一个Block的子内容
        public ImageButton block0ImageButton;
        public TextView block0TitleText;
        public TextView block0SubTitle;


        public ImageButton block1ImageButton;
        public TextView block1TitleText;
        public TextView block1SubTitle;


        public ImageButton block2ImageButton;
        public TextView block2TitleText;
        public TextView block2SubTitle;


        public ImageButton block3ImageButton;
        public TextView block3TitleText;
        public TextView block3SubTitle;


    }

    @Override
    public int getViewTypeCount() {
        //小编推荐，精品听单 发现新奇 热门推荐
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        int ret = 0;
        Object item = getItem(position);
        if (item instanceof EditorRecommendAlbums) {
            ret = 0;
        } else if (item instanceof SpecialColumn) {
            ret = 1;
        } else if (item instanceof DiscoveryColumns) {
            ret = 2;
        } else if (item instanceof SubHotRecommends) {
            ret = 3;
        }
        return ret;
    }
}
