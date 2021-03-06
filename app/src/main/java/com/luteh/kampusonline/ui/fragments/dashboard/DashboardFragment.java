package com.luteh.kampusonline.ui.fragments.dashboard;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.adapter.DashboardAdapter;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.adapter.ItemClicked;
import com.luteh.kampusonline.common.AppConstant;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.ui.activities.PDFActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindArray;
import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements ItemClicked {
    private RecyclerView.Adapter mAdapter;
    private Bundle bundle;

    @BindView(R.id.rvDashboard)
    RecyclerView rvDashboard;

    @BindArray(R.array.label_dashboard_array)
    String[] labelItems;

    @BindArray(R.array.list_dashboard_img_items)
    TypedArray drawableItems;

    @BindArray(R.array.pdf_items)
    String[] pdfItems;

    private static final String TAG = DashboardFragment.class.getSimpleName();

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
        bundle = new Bundle();
    }

    private void initRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        if (rvDashboard != null) {
            rvDashboard.setHasFixedSize(true);
            // use a linear layout manager
            rvDashboard.setLayoutManager(new GridLayoutManager(getContext(), 2));

            // specify an adapter (see also next example)
            mAdapter = new DashboardAdapter(getContext(), drawableItems, labelItems, this);
            rvDashboard.setAdapter(mAdapter);
        }
    }

    @Override
    public void onItemClicked(int position) {
        switch (position) {
            case 0:
                bundle.putString(AppConstant.KEY_PDF_ASSET, pdfItems[position]);
                getBaseActivity().startActivityWithFade(PDFActivity.class, bundle);
                break;
            case 1:
                bundle.putString(AppConstant.KEY_PDF_ASSET, pdfItems[position]);
                getBaseActivity().startActivityWithFade(PDFActivity.class, bundle);
                break;
            case 2:
                bundle.putString(AppConstant.KEY_PDF_ASSET, pdfItems[position]);
                getBaseActivity().startActivityWithFade(PDFActivity.class, bundle);
                break;
            case 3:
                bundle.putString(AppConstant.KEY_PDF_ASSET, pdfItems[position]);
                getBaseActivity().startActivityWithFade(PDFActivity.class, bundle);
                break;
            case 4:
                bundle.putString(AppConstant.KEY_PDF_ASSET, pdfItems[position]);
                getBaseActivity().startActivityWithFade(PDFActivity.class, bundle);
                break;
            case 5:
                bundle.putString(AppConstant.KEY_PDF_ASSET, pdfItems[position]);
                getBaseActivity().startActivityWithFade(PDFActivity.class, bundle);
                break;
        }
    }
}
