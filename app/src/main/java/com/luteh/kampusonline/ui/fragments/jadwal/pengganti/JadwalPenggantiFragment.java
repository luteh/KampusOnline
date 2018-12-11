package com.luteh.kampusonline.ui.fragments.jadwal.pengganti;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.common.utils.RecyclerTouchListener;
import com.luteh.kampusonline.model.JadwalPengganti;
import com.luteh.kampusonline.ui.fragments.jadwal.pengganti.adapter.JadwalPenggantiAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
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

    private List<JadwalPengganti> jadwalPenggantiList = new ArrayList<>();

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

        /**
         * On long press on RecyclerView item, open Pengganti dialog
         * to see Ditiadakan and Pengganti jadwal
         * */
        rvJadwalPengganti.addOnItemTouchListener(new RecyclerTouchListener(context, rvJadwalPengganti, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
                showPenggantiDialog(position);
            }
        }));
    }

    private void showPenggantiDialog(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.jadwal_pengganti_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);

        TextView tvPenggantiDialogMatkul = view.findViewById(R.id.tvPenggantiDialogMatkul);
        TextView tvPenggantiDialogOldHari = view.findViewById(R.id.tvPenggantiDialogOldHari);
        TextView tvPenggantiDialogOldTanggal = view.findViewById(R.id.tvPenggantiDialogOldTanggal);
        TextView tvPenggantiDialogOldStartClass = view.findViewById(R.id.tvPenggantiDialogOldStartClass);
        TextView tvPenggantiDialogOldFinishClass = view.findViewById(R.id.tvPenggantiDialogOldFinishClass);
        TextView tvPenggantiDialogOldRuangan = view.findViewById(R.id.tvPenggantiDialogOldRuangan);
        TextView tvPenggantiDialogNewHari = view.findViewById(R.id.tvPenggantiDialogNewHari);
        TextView tvPenggantiDialogNewTanggal = view.findViewById(R.id.tvPenggantiDialogNewTanggal);
        TextView tvPenggantiDialogNewStartClass = view.findViewById(R.id.tvPenggantiDialogNewStartClass);
        TextView tvPenggantiDialogNewFinishClass = view.findViewById(R.id.tvPenggantiDialogNewFinishClass);
        TextView tvPenggantiDialogNewRuangan = view.findViewById(R.id.tvPenggantiDialogNewRuangan);

        tvPenggantiDialogMatkul.setText(jadwalPenggantiList.get(position).mata_kuliah);
        tvPenggantiDialogOldHari.setText(jadwalPenggantiList.get(position).old_hari);
        tvPenggantiDialogOldTanggal.setText(jadwalPenggantiList.get(position).old_tanggal);
        tvPenggantiDialogOldStartClass.setText(jadwalPenggantiList.get(position).old_start_class);
        tvPenggantiDialogOldFinishClass.setText(jadwalPenggantiList.get(position).old_finish_class);
        tvPenggantiDialogOldRuangan.setText(jadwalPenggantiList.get(position).old_ruangan);
        tvPenggantiDialogNewHari.setText(jadwalPenggantiList.get(position).new_hari);
        tvPenggantiDialogNewTanggal.setText(jadwalPenggantiList.get(position).new_tanggal);
        tvPenggantiDialogNewStartClass.setText(jadwalPenggantiList.get(position).new_start_class);
        tvPenggantiDialogNewFinishClass.setText(jadwalPenggantiList.get(position).new_finish_class);
        tvPenggantiDialogNewRuangan.setText(jadwalPenggantiList.get(position).new_ruangan);

        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().getAttributes().windowAnimations = libs.mjn.prettydialog.R.style.pdlg_default_animation;
        dialog.show();
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

        if (this.jadwalPenggantiList != null) this.jadwalPenggantiList.clear();
        this.jadwalPenggantiList.addAll(jadwalPenggantiList);

//        Common.dismissProgressBar();
    }
}
