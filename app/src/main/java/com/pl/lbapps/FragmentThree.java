package com.pl.lbapps;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/3/29.
 */

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class FragmentThree extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        return inflater.inflate(R.layout.fragment_three,container,false);
    }
}
