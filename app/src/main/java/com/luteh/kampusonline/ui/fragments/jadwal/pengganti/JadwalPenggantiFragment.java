package com.luteh.kampusonline.ui.fragments.jadwal.pengganti;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.model.JadwalPengganti;
import com.luteh.kampusonline.ui.fragments.jadwal.kuliah.adapter.JadwalKuliahAdapter;
import com.luteh.kampusonline.ui.fragments.jadwal.pengganti.adapter.JadwalPenggantiAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalPenggantiFragment extends BaseFragment implements AdapterView.OnItemSelectedListener, IJadwalPenggantiView {

    @BindView(R.id.jadwalPenggantiSpinner)
    Spinner jadwalPenggantiSpinner;
    @BindView(R.id.rvJadwalPengganti)
    RecyclerView rvJadwalPengganti;

    private RecyclerView.Adapter mAdapter;
    private IJadwalPenggantiPresenter iJadwalPenggantiPresenter;

    public JadwalPenggantiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_pengganti, container, false);
    }

    @Override
    protected void onInit() {
        super.onInit();

        iJadwalPenggantiPresenter = new JadwalPenggantiPresenterImp(this);

        rvJadwalPengganti.setHasFixedSize(true);
        rvJadwalPengganti.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Common.semesterLists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jadwalPenggantiSpinner.setAdapter(adapter);

        jadwalPenggantiSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Common.showProgressBar(context);
        iJadwalPenggantiPresenter.retrieveJadwalPenggantiData(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showJadwalPengganti(List<JadwalPengganti> jadwalPenggantiList) {
        if (mAdapter == null) {
            mAdapter = new JadwalPenggantiAdapter(jadwalPenggantiList);
            rvJadwalPengganti.setAdapter(mAdapter);
        } else
            ((JadwalPenggantiAdapter) mAdapter).updateItem(jadwalPenggantiList);

//        Common.dismissProgressBar();
    }
}
