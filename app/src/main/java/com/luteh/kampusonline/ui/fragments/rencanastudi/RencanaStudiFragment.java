package com.luteh.kampusonline.ui.fragments.rencanastudi;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RencanaStudiFragment extends Fragment {


    public RencanaStudiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rencana_studi, container, false);
    }

}