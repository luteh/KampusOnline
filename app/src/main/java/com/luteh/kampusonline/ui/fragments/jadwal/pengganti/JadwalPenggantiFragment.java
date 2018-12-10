package com.luteh.kampusonline.ui.fragments.jadwal.pengganti;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalPenggantiFragment extends BaseFragment {


    public JadwalPenggantiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_pengganti, container, false);
    }

}
