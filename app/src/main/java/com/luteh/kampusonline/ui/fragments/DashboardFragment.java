package com.luteh.kampusonline.ui.fragments;


import android.content.res.TypedArray;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.adapter.DashboardAdapter;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.base.BaseFragment;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment {
    private RecyclerView.Adapter mAdapter;


   @BindView(R.id.rvDashboard)
    RecyclerView rvDashboard;

    @BindArray(R.array.label_dashboard_array)
    String[] labelItems;

    @BindArray(R.array.list_dashboard_img_items)
    TypedArray drawableItems;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    protected void onInit() {
        super.onInit();
        initRecyclerView();
    }

    private void initRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        if (rvDashboard != null) {
            rvDashboard.setHasFixedSize(true);
            // use a linear layout manager
            rvDashboard.setLayoutManager(new GridLayoutManager(getContext(), 2));

            // specify an adapter (see also next example)
            mAdapter = new DashboardAdapter(getContext(),drawableItems, labelItems);
            rvDashboard.setAdapter(mAdapter);
        }
    }

}
