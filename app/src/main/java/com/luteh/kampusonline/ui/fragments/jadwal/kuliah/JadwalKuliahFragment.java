package com.luteh.kampusonline.ui.fragments.jadwal.kuliah;


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
import com.luteh.kampusonline.model.jadwal.JadwalKuliah;
import com.luteh.kampusonline.ui.fragments.jadwal.kuliah.adapter.JadwalKuliahAdapter;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalKuliahFragment extends BaseFragment implements AdapterView.OnItemSelectedListener, IJadwalKuliahView {

    private RecyclerView.Adapter mAdapter;
    private IJadwalKuliahPresenter iJadwalKuliahPresenter;

    @BindView(R.id.jadwalKuliahSpinner)
    Spinner jadwalKuliahSpinner;
    @BindView(R.id.rvJadwalKuliah)
    RecyclerView rvJadwalKuliah;

    public JadwalKuliahFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_kuliah, container, false);
    }

    @Override
    protected void onInit() {
        super.onInit();
        iJadwalKuliahPresenter = new JadwalKuliahPresenterImp(this);

        rvJadwalKuliah.setHasFixedSize(true);
        rvJadwalKuliah.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Common.semesterLists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jadwalKuliahSpinner.setAdapter(adapter);

        jadwalKuliahSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Common.showProgressBar(context);
        iJadwalKuliahPresenter.retrieveJadwalKuliahData(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showJadwalKuliah(List<JadwalKuliah> jadwalKuliahList) {
        if (mAdapter == null) {
            mAdapter = new JadwalKuliahAdapter(jadwalKuliahList);
            rvJadwalKuliah.setAdapter(mAdapter);
        } else
            ((JadwalKuliahAdapter) mAdapter).updateItem(jadwalKuliahList);

        Common.dismissProgressBar();
    }
}
