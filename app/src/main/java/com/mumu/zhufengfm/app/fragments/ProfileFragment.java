package com.mumu.zhufengfm.app.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mumu.zhufengfm.app.R;

/**
 * Created with IntelliJ IDEA.
 * User: mumu
 * Date: 15-7-29
 */
public class ProfileFragment extends Fragment {

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
