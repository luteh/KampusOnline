package com.luteh.kampusonline.ui.fragments.jadwal.ujian;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.model.jadwal.JadwalUjian;
import com.luteh.kampusonline.ui.fragments.jadwal.ujian.adapter.JadwalUjianAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalUjianFragment extends BaseFragment implements IJadwalUjianView, AdapterView.OnItemSelectedListener {

    private static final String TAG = JadwalUjianFragment.class.getSimpleName();


    @BindView(R.id.jadwalUjianSpinner)
    Spinner jadwalUjianSpinner;
    @BindView(R.id.rvJadwalUjian)
    RecyclerView rvJadwalUjian;

    private RecyclerView.Adapter mAdapter;
    private IJadwalUjianPresenter iJadwalUjianPresenter;

    public JadwalUjianFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_ujian, container, false);
    }

    @Override
    protected void onInit() {
        super.onInit();

        iJadwalUjianPresenter = new JadwalUjianPresenterImp(this);

        rvJadwalUjian.setHasFixedSize(true);
        rvJadwalUjian.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Common.ujianSemesterList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jadwalUjianSpinner.setAdapter(adapter);

        jadwalUjianSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Common.showProgressBar(context);
        iJadwalUjianPresenter.retrieveJadwalUjianData(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onRetrieveDataSuccess(List<JadwalUjian> jadwalUjianList) {
        showJadwalUjian(jadwalUjianList);
    }

    public void showJadwalUjian(List<JadwalUjian> jadwalUjianList) {
        if (mAdapter == null) {
            mAdapter = new JadwalUjianAdapter(jadwalUjianList);
            rvJadwalUjian.setAdapter(mAdapter);
        } else
            ((JadwalUjianAdapter) mAdapter).updateItem(jadwalUjianList);

//        Common.dismissProgressBar();
    }
}
