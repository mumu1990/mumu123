package com.mumu.zhufengfm.app;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

/**
 * Created by Administrator on 2015/7/30.
 */

/**
 * 基础的Activity
 */
public class BaseActivity extends FragmentActivity {


    private TextView txtTitle;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //TODO 进行公共的一些控件内容的初始化

        //只要调用了 super.setContentView 就可以findViewById()了

        txtTitle = (TextView) findViewById(R.id.myTitle);
        if (txtTitle != null) {
            txtTitle.setText("shezhi");
        }


    }

    /**
     * Activity设置标题的方法
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);

        if (txtTitle != null) {
            txtTitle.setText(title);
        }
    }

    /**
     * startActivity之后 新的Activity进入的动画<br/>
     * 默认从右往左 如果定制不同动画 子类重写即可
     * @return
     */
    protected int getEnterAnimationId(){
        return R.anim.anim_slide_to_left;
    }

    protected int getExitAnimationId(){
        return R.anim.anim_dropdown;
    }

    /**
     * 启动Activity并且给它指定动画
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);

        overridePendingTransition(getEnterAnimationId(),0);
    }

    @Override
    public void finish() {
        super.finish();

        int exitAnimationid=getExitAnimationId();
        if (exitAnimationid != 0) {
            overridePendingTransition(0, getExitAnimationId());
        }

    }
}
