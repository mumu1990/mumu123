package com.mumu.zhufengfm.app.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.mumu.zhufengfm.app.R;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
 * Date: 15-7-29
 */

/**
 * 教程页的ViewPager 的适配器
 */
public class GuideAdapter extends PagerAdapter {

    private List<Integer> images;

    private Context context;

    private View.OnClickListener goOnClickListener;

    public GuideAdapter(Context context, List<Integer> images) {
        this.context = context;
        this.images = images;
    }

    public void setGoOnClickListener(View.OnClickListener goOnClickListener) {
        this.goOnClickListener = goOnClickListener;
    }

    @Override
    public int getCount() {
        int ret = 0;

        if (images != null) {
            ret = images.size();
        }

        return ret;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        //对于FragmentPagerAdapter,object 是 Fragment
        //view 与 object 的判断就不能够直接 view == object 了
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View ret = null;

        int resId = images.get(position);

        if(position == images.size()-1){
            // TODO 最后一页，设置布局，添加按钮
            FrameLayout layout = new FrameLayout(context);

            ////////////////     添加图片    //////////////////////

            ImageView imageView = new ImageView(context);
            imageView.setImageResource(resId);

            ViewGroup.LayoutParams lp1 = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );

            imageView.setLayoutParams(lp1);

            layout.addView(imageView);

            ////////////////     添加按钮    ///////////////////

            Button btnGo = new Button(context);

            btnGo.setText(R.string.guide_start_main);

            //第三个参数指定控件在 FrameLayout 的哪个位置
            FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM
            );

            // TODO 需要进行机型适配
            lp2.bottomMargin = 120;

            btnGo.setLayoutParams(lp2);

            btnGo.setOnClickListener(goOnClickListener);

            //代码创建的 Button 是没有ID的，通过tag 可以进行按钮的区分
            btnGo.setTag("Go");

            layout.addView(btnGo);

            ///////////////////////////////////////////////////

            ret = layout;

        } else {
            // TODO 直接是图片
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(resId);

            ret = imageView;
        }

        container.addView(ret);

        return ret;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        if(object instanceof View){
            container.removeView((View) object);
        }


    }
}
