package com.luteh.kampusonline;


import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.common.base.BaseFragment;

import butterknife.BindArray;
import butterknife.BindDrawable;
import butterknife.BindInt;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment {
    private RecyclerView.Adapter mAdapter;

    private Unbinder unbinder;

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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        unbinder = ButterKnife.bind(this, view);

        initRecyclerView();

        return view;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
