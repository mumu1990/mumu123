package com.mumu.zhufengfm.app.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mumu.zhufengfm.app.Constants;
import com.mumu.zhufengfm.app.R;
import com.mumu.zhufengfm.app.adapters.CommonFragmentPagerAdapter;
import com.mumu.zhufengfm.app.fragments.Discover.*;
import com.mumu.zhufengfm.app.model.DiscoverTab;
import com.mumu.zhufengfm.app.parsers.DataParser;
import com.mumu.zhufengfm.app.tasks.TaskCallback;
import com.mumu.zhufengfm.app.tasks.TaskResult;
import com.mumu.zhufengfm.app.tasks.impl.DiscoverTabTask;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment implements View.OnClickListener, TabLayout.OnTabSelectedListener, TaskCallback {

    /**
     * 放子栏目的Viewpager
     */
    private ViewPager pager;

    /**
     * tab信息
     *
     */
    private List<DiscoverTab> tabTitles;

    /**
     * 子栏目的Tab指示器，使用了design support包的内容
     */
    private TabLayout tabBar;

    private List<Fragment> subFragments;
    private ImageView btnSearch;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tabTitles=new LinkedList<DiscoverTab>();
        subFragments=new LinkedList<Fragment>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ret = inflater.inflate(R.layout.fragment_discover, container, false);

        TextView titleTxt= (TextView) ret.findViewById(R.id.myTitle);
        titleTxt.setOnClickListener(this);

        ImageView titleImage= (ImageView) ret.findViewById(R.id.image);
        titleImage.setOnClickListener(this);

        btnSearch = (ImageView) ret.findViewById(R.id.discover_title_search);

        if (btnSearch != null) {
            btnSearch.setOnClickListener(this);
        }

        //////////////////////////////////////////////////////
        tabBar= (TabLayout) ret.findViewById(R.id.discover_tab_bar);
       //TODO  因为Tab的设置是从网络传来的，因此要动态添加


        tabBar.setOnTabSelectedListener(this);

        pager= (ViewPager) ret.findViewById(R.id.discover_page);

        //设置Adapter
        //TODO 由于Tab是动态设置的 所以ViewPager Adapter也需要动态设置



        //ViewPager在滑动页面时添加监听器
        //监听由TabLayoutOnPageChangeListener 来完成 从而实现ViewPager滚动，上面的TabLayout跟随Tab滚动
        pager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabBar)
        );

        //加载Tabs
        DiscoverTabTask task=new DiscoverTabTask(this);

        task.execute();
        return ret;
    }
//////////////////////////////////////////////////////////


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.myTitle:
                Toast.makeText(getActivity(),"么么～",Toast.LENGTH_SHORT).show();
                break;
            case R.id.image:
                Toast.makeText(getActivity(),"u are a pig～",Toast.LENGTH_SHORT).show();
                break;
            case R.id.discover_title_search:
                btnSearch.setImageResource(R.mipmap.search_pressed);
                break;

        }

    }
/////////////////////////////////////////////////
    //TabLayout 的 Tab选中的接口 使用规则和TabBar很类似
    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        int position = tab.getPosition();
        pager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        //TODO 进行刷新
    }
///////////////////////////////////////////////////////
    @Override
    public void onTaskFinished(TaskResult result) {

        if (result != null) {
            int taskId = result.taskId;
            Object data = result.data;
            if (taskId== Constants.TASK_DISCOVER_TABS){
                if (data != null) {
                    //TODO 解析json

                    /**
                     * 解析发现的标题
                     */
                    if(data instanceof JSONObject){
                        JSONObject jsonObject=(JSONObject)data;
                        tabTitles = DataParser.parseDiscoverTab(jsonObject);
                        updateTabs();
                    }

                }else {
                    //TODO 设置data的默认数据
                }

            }
        }
    }

    private void updateTabs() {
        if (tabTitles != null) {


            for (DiscoverTab tabTitle : tabTitles) {
                //TabLayout 创建一个Tab 实例
                TabLayout.Tab tab = tabBar.newTab();
                tab.setText(tabTitle.getTitle());
                tabBar.addTab(tab);

                //根据内容类型，来设置指定的Fragment
                String type = tabTitle.getContentType();

                if ("recommend".equals(type)) {
                    subFragments.add(new DiscoverRecommendFragment());
                } else if ("category".equals(type)) {
                    subFragments.add(new DiscoverCategoryFragment());
                } else if ("live".equals(type)) {
                    subFragments.add(new DiscoverLiveFragment());
                } else if ("ranking".equals(type)) {
                    subFragments.add(new DiscoverRankingFragment());
                } else if ("anchor".equals(type)) {
                    subFragments.add(new DiscoverAnchorFragment());
                }
            }

            CommonFragmentPagerAdapter adapter =
                    new CommonFragmentPagerAdapter(
                            getChildFragmentManager(),
                            subFragments
                    );

            pager.setAdapter(adapter);

        }
    }

}
