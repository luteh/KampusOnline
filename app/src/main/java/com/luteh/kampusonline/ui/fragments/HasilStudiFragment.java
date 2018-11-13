package com.luteh.kampusonline.ui.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HasilStudiFragment extends BaseFragment {


    public HasilStudiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hasil_studi, container, false);
    }

    @Override
    protected void onInit() {
        super.onInit();
    }
}
