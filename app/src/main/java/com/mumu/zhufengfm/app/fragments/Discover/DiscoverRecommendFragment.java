package com.mumu.zhufengfm.app.fragments.Discover;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.mumu.zhufengfm.app.Constants;
import com.mumu.zhufengfm.app.EditorRecommendItemActivity;
import com.mumu.zhufengfm.app.R;
import com.mumu.zhufengfm.app.Test1Activity;
import com.mumu.zhufengfm.app.adapters.DiscoverRecommendAdapter;
import com.mumu.zhufengfm.app.adapters.EditorRecommendAdapter;
import com.mumu.zhufengfm.app.data.DataStore;
import com.mumu.zhufengfm.app.model.DiscoverRecommends;
import com.mumu.zhufengfm.app.model.discoverrec.subRecommendList.editorRecommend.EditorRecomendAll;
import com.mumu.zhufengfm.app.parsers.DataParser;
import com.mumu.zhufengfm.app.tasks.TaskCallback;
import com.mumu.zhufengfm.app.tasks.TaskResult;
import com.mumu.zhufengfm.app.tasks.impl.DiscoverCategoryTask;
import com.mumu.zhufengfm.app.tasks.impl.DiscoverRecommendTask;
import com.mumu.zhufengfm.app.tasks.impl.EditorRecommendItemTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverRecommendFragment extends Fragment implements TaskCallback, AdapterView.OnItemClickListener, View.OnClickListener {


    private DiscoverRecommendAdapter adapter;

    private EditorRecommendAdapter editorRecommendAdapter;

    public DiscoverRecommendFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//       DiscoverRecommends discoverRecommends = DataStore.getInstance().getDiscoverRecommends();
//
//        if (discoverRecommends != null) {
//        }else {
        //TODO 开启异步任务
        DiscoverRecommendTask task = new DiscoverRecommendTask(this);
        task.execute();
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate
                (R.layout.fragment_discover_recommend, container, false);
        PullToRefreshListView pullToRefresh =
                (PullToRefreshListView) ret.findViewById(R.id.discover_recommend_list);

        if (pullToRefresh != null) {
            adapter = new DiscoverRecommendAdapter(this, getActivity());
            adapter.setOnClickListener(this);
            pullToRefresh.setAdapter(adapter);
            pullToRefresh.setMode(PullToRefreshBase.Mode.PULL_FROM_START);

            ListView listView = pullToRefresh.getRefreshableView();

            // listView.setDivider(new ColorDrawable(999));
            listView.setDividerHeight(5);

        }


        // ListView listView = (ListView) ret.findViewById(R.id.discover_recommend_list);

//        if (listView != null) {
////
////            //TODO 设置实际数据的 Adapter
////
//            adapter = new DiscoverRecommendAdapter(this, getActivity());
//            listView.setAdapter(adapter);


//            //添加头部
//
//            ImageView imageView=new ImageView(getActivity());
//            imageView.setImageResource(R.mipmap.ic_launcher);
//            //通过这个方法添加跟随滚动的头部
//            listView.addHeaderView(imageView);
//            //添加底部视图
//
//            TextView textView=new TextView(getActivity());
//            textView.setText("点击加载更多");
//
//            listView.addFooterView(textView);
//
//
//            ArrayList<String> strings = new ArrayList<String>();
//            for (int i = 0; i < 20; i++) {
//                strings.add("mumu"+i);
//            }
//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
//                    getActivity(),
//                    android.R.layout.simple_list_item_1,
//                    strings
//            );
//
//
//            listView.setAdapter(adapter);
//
//            listView.setOnItemClickListener(this);
//        }
        return ret;
    }


    public void onTaskFinished(TaskResult result) {
        if (result != null) {
            int taskId = result.taskId;
            Object data = result.data;
            if (taskId == Constants.TASK_DISCOVER_RECOMMENDS) {
                if (data != null) {
                    if (data instanceof JSONObject) {

                        DiscoverRecommends discoverRecommends =
                                DataParser.parserDiscoverRecommends((JSONObject) data);

                        adapter.setRecommends(discoverRecommends);

                        //TODO 利用分类制作UI界面

                    }
                }
            } else if (taskId == Constants.TASK_EDITOR_RECOMMENDS_ITEM) {
                if (data != null) {
                    if (data instanceof JSONObject) {

                        EditorRecomendAll editorRecomendAll = DataParser.parseEditorRecommendAll((JSONObject) data);
                        editorRecommendAdapter = new EditorRecommendAdapter(getActivity(), this);
                        editorRecommendAdapter.setEditorRecommends(editorRecomendAll);

                    }
                }
            }
        }
    }


    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        FragmentActivity activity = getActivity();

        int headerViewsCount = 0;
        int footerViewsCount = 0;
        if (parent instanceof ListView) {
            ListView listView = (ListView) parent;
            headerViewsCount = listView.getHeaderViewsCount();
            footerViewsCount = listView.getFooterViewsCount();
            //调整因为headView导致的偏移
            position -= headerViewsCount;


            if (footerViewsCount > 0) {
                if (position >= 20) {
                    //点的不是数据
                } else {
                    //
                }
            } else {
                //数据点上了
            }
        }
        Toast.makeText(activity, "点击" + position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Object tag = v.getTag();
        if (id == R.id.item_recommend_album_more) {//进入更多界面
            String s = (String) tag;
            if ("editor".equals(tag)) {
                Toast.makeText(getActivity(), "点了小编推荐", Toast.LENGTH_SHORT).show();
            } else if (s.startsWith("hotRecommend")) {
                //TODO 热门推荐更多
                int index = s.indexOf(':');
                s = s.substring(index + 1);
                int cid = Integer.parseInt(s);
            }

        } else if (v instanceof ImageButton) {
            //TODO 如果是图片相当于点击了专辑，跳专辑列表
            if (tag != null) {
                if (tag instanceof String[]) {
                    String[] ss = (String[]) tag;
                    if (ss.length > 2) {
                        String albumId = ss[1];
                        String trackId = ss[2];
                        //TODO 打开新界面 调用接口17

                        Intent intent = new Intent(getActivity(), EditorRecommendItemActivity.class);

                        intent.putExtra("albumId",albumId);

                        startActivity(intent);


                    }
                }
            }
        }
    }
}
