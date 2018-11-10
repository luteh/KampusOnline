package com.luteh.kampusonline;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.common.base.BaseFragment;

import butterknife.BindArray;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment {
    //    private RecyclerView rvDashboard;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


   @Nullable
   @BindView(R.id.rvDashboard)
    RecyclerView rvDashboard;

    @BindArray(R.array.label_dashboard_array)
    String[] labelItems;

    @BindDrawable(R.drawable.img_dashboard_kode_etik)
    Drawable drawableKodeEtik;
    @BindDrawable(R.drawable.img_dashboard_panduan_akademik)
    Drawable drawablePanduanAkademik;
    @BindDrawable(R.drawable.img_dashboard_panduan_skpi)
    Drawable drawablePanduanSkpi;
    @BindDrawable(R.drawable.img_dashboard_panduan_keuangan)
    Drawable drawablePanduanKeuangan;
    @BindDrawable(R.drawable.img_dashboard_panduan_blog)
    Drawable drawablePanduanBlog;
    @BindDrawable(R.drawable.img_dashboard_panduan_ikad)
    Drawable drawablePanduanIkad;

//    private Drawable[] drawableItems;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
//       ButterKnife.bind(this, view);

        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {
        Drawable[] drawableItems = {drawableKodeEtik, drawablePanduanAkademik, drawablePanduanSkpi, drawablePanduanKeuangan, drawablePanduanBlog, drawablePanduanIkad};

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        if (rvDashboard != null) {
            rvDashboard.setHasFixedSize(true);
            // use a linear layout manager
            mLayoutManager = new LinearLayoutManager(getContext());
            rvDashboard.setLayoutManager(mLayoutManager);

            // specify an adapter (see also next example)
            mAdapter = new DashboardAdapter(drawableItems, labelItems);
            rvDashboard.setAdapter(mAdapter);
        }
    }
}
