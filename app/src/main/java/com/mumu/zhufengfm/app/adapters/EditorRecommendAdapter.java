package com.mumu.zhufengfm.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mumu.zhufengfm.app.BaseActivity;
import com.mumu.zhufengfm.app.R;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend.EditorRecomendAll;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend.EditorRecommendHeadAndTitle;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend.EditorRecommendItem;

import java.util.List;

/**
 * Created by Administrator on 2015/8/1.
 */
public class EditorRecommendAdapter extends BaseAdapter {
    private Context context;
    private EditorRecomendAll recommends;
    private View.OnClickListener onClickListener;

    public void setEditorRecommends(EditorRecomendAll recommends) {
        this.recommends = recommends;
        notifyDataSetChanged();
    }

    public EditorRecommendAdapter(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (recommends != null) {
            EditorRecommendHeadAndTitle list = recommends.getList();
            List<EditorRecommendItem> items = list.getItems();
            if (items!=null) {
                ret = items.size();
            }
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return recommends.getList().getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;
        ret= LayoutInflater.from(context).inflate(R.layout.editor_recommend_layout,parent,false);
        TextView titleView= (TextView) ret.findViewById(R.id.editorRecommend_title);
        String title = recommends.getList().getItems().get(position).getTitle();
        titleView.setText(title);

        TextView durationView= (TextView) ret.findViewById(R.id.editorRecommend_duration);
        String duration = ""+recommends.getList().getItems().get(position).getDuration();
        durationView.setText(duration);

        TextView countsView= (TextView) ret.findViewById(R.id.editorRecommend_playsCounts);
        String counts = ""+recommends.getList().getItems().get(position).getPlaytimes();
        countsView.setText(counts);

        TextView commentsView= (TextView) ret.findViewById(R.id.editorRecommend_recommends);
        String comments = ""+recommends.getList().getItems().get(position).getComments();
        commentsView.setText(comments);

        return ret;
    }
}
