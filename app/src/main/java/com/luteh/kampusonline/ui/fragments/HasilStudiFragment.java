package com.luteh.kampusonline.ui.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import butterknife.BindView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.evrencoskun.tableview.TableView;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.model.hasilstudi.HasilStudi;
import com.luteh.kampusonline.presenter.hasilstudi.HasilStudiPresenterImp;
import com.luteh.kampusonline.presenter.hasilstudi.IHasilStudiPresenter;
import com.luteh.kampusonline.view.IHasilStudiView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HasilStudiFragment extends BaseFragment implements AdapterView.OnItemSelectedListener, IHasilStudiView {

    private static final String TAG = "Hasil_Studi_Fragment";
    @BindView(R.id.semesterSpinner)
    Spinner semesterSpinner;

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



        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.label_semester_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        semesterSpinner.setAdapter(adapter);

        semesterSpinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        iHasilStudiPresenter.getHasilStudi(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showHasilStudi(List<HasilStudi> hasilStudiList) {
        Log.i(TAG, hasilStudiList.size() + "");
        for (HasilStudi hasilStudi : hasilStudiList) {
            Log.i(TAG, hasilStudi.mata_kuliah);
        }
    }
}
