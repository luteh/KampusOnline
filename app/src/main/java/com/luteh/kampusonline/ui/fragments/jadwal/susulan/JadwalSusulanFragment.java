package com.luteh.kampusonline.ui.fragments.jadwal.susulan;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.model.jadwal.JadwalUjian;
import com.luteh.kampusonline.ui.fragments.jadwal.ujian.IJadwalUjianPresenter;
import com.luteh.kampusonline.ui.fragments.jadwal.ujian.JadwalUjianFragment;
import com.luteh.kampusonline.ui.fragments.jadwal.ujian.JadwalUjianPresenterImp;
import com.luteh.kampusonline.ui.fragments.jadwal.ujian.adapter.JadwalUjianAdapter;

import java.io.FileNotFoundException;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalSusulanFragment extends BaseFragment implements IJadwalSusulanView, AdapterView.OnItemSelectedListener {
    private static final String TAG = JadwalUjianFragment.class.getSimpleName();

    @BindView(R.id.jadwalSusulanSpinner)
    Spinner jadwalSusulanSpinner;
    @BindView(R.id.rvJadwalSusulan)
    RecyclerView rvJadwalSusulan;
    @BindView(R.id.llJadwalSusulan)
    LinearLayout llJadwalSusulan;

    private RecyclerView.Adapter mAdapter;
    private IJadwalSusulanPresenter iJadwalSusulanPresenter;

    public JadwalSusulanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal_susulan, container, false);
    }

    @Override
    protected void onInit() {
        super.onInit();

        iJadwalSusulanPresenter = new JadwalSusulanPresenterImp(this);

        rvJadwalSusulan.setHasFixedSize(true);
        rvJadwalSusulan.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));

        initSpinner();
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Common.susulanSemesterList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jadwalSusulanSpinner.setAdapter(adapter);

        jadwalSusulanSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onRetrieveDataSuccess(List<JadwalUjian> jadwalUjianList) {
        showJadwalUjian(jadwalUjianList);

    }

    private void showJadwalUjian(List<JadwalUjian> jadwalUjianList) {
        if (mAdapter == null) {
            mAdapter = new JadwalUjianAdapter(jadwalUjianList);
            rvJadwalSusulan.setAdapter(mAdapter);
        } else
            ((JadwalUjianAdapter) mAdapter).updateItem(jadwalUjianList);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        iJadwalSusulanPresenter.retrieveJadwalSusulanData(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @OnClick(R.id.btnJadwalSusulanSaveToPdf)
    protected void saveToPdf() {
        try {
            Common.layoutToImage(llJadwalSusulan, getString(R.string.text_jadwal, getString(R.string.title_susulan_fragment)));
            Common.showSuccessMessage(context, getString(R.string.label_message_success_save_doc));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
