package com.luteh.kampusonline.ui.fragments.hasilstudi;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.adapter.HasilStudiAdapter;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.model.hasilstudi.HasilStudi;

import java.io.FileNotFoundException;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class HasilStudiFragment extends BaseFragment implements AdapterView.OnItemSelectedListener, IHasilStudiView {
    private RecyclerView.Adapter mAdapter;
    private static final String TAG = "Hasil_Studi_Fragment";
    @BindView(R.id.semesterSpinner)
    Spinner semesterSpinner;
    @BindView(R.id.rvHasilStudi)
    RecyclerView rvHasilStudi;
    @BindView(R.id.llHasilStudi)
    LinearLayout llHasilStudi;

    private IHasilStudiPresenter iHasilStudiPresenter;

    public HasilStudiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hasil_studi, container, false);
    }

    @Override
    protected void onInit() {
        super.onInit();
        iHasilStudiPresenter = new HasilStudiPresenterImp(context, this);

        if (rvHasilStudi != null) {
            rvHasilStudi.setHasFixedSize(true);
            // use a linear layout manager
            rvHasilStudi.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, Common.semesterLists);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        semesterSpinner.setAdapter(adapter);

        semesterSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Common.showProgressBar(context);
        iHasilStudiPresenter.getHasilStudi(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showHasilStudi(List<HasilStudi> hasilStudiList) {
        mAdapter = new HasilStudiAdapter(context, hasilStudiList);
        mAdapter.notifyDataSetChanged();
        rvHasilStudi.setAdapter(mAdapter);

        Common.dismissProgressBar();
    }

    @OnClick(R.id.btnHasilStudiSaveToPdf)
    protected void saveToPdf() {
        try {
            Common.layoutToImage(llHasilStudi, getString(R.string.title_hasil_studi_fragment));
            Common.showSuccessMessage(context, getString(R.string.label_message_success_save_doc));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
