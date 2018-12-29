package com.luteh.kampusonline.ui.fragments.rencanastudi;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.model.rencanastudi.FRencanaStudi;
import com.luteh.kampusonline.ui.fragments.rencanastudi.adapter.RencanaStudiAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import static com.luteh.kampusonline.ui.fragments.rencanastudi.adapter.RencanaStudiGroupDataFactory.makeRencanaStudiGroups;

/**
 * A simple {@link Fragment} subclass.
 */
public class RencanaStudiFragment extends BaseFragment implements IRencanaStudiView {

    private IRencanaStudiPresenter iRencanaStudiPresenter;
    private RencanaStudiAdapter adapter;

    @BindView(R.id.rvRencanaStudi)
    RecyclerView rvRencanaStudi;

    public RencanaStudiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rencana_studi, container, false);
    }

    @Override
    protected void onInit() {
        super.onInit();

        rvRencanaStudi.setHasFixedSize(true);
        rvRencanaStudi.setLayoutManager(new LinearLayoutManager(getContext()));

        iRencanaStudiPresenter = new RencanaStudiPresenterImp(this);

        iRencanaStudiPresenter.retrieveFrsData();
    }

    @Override
    public void onRetrieveDataSuccess(FRencanaStudi fRencanaStudi) {
        adapter = new RencanaStudiAdapter(makeRencanaStudiGroups(fRencanaStudi));
        rvRencanaStudi.setAdapter(adapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            adapter.onRestoreInstanceState(savedInstanceState);
        }
    }
}
