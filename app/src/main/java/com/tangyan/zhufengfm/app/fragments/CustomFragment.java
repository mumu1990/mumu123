package com.tangyan.zhufengfm.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tangyan.zhufengfm.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: tangyan
 * Date: 15-7-29
 */
public class CustomFragment extends Fragment {

    public CustomFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_custom, container, false);
    }
}
