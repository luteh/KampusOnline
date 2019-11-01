package com.luteh.kampusonline.ui.fragments.rencanastudi;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.Common;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.model.rencanastudi.FRencanaStudi;
import com.luteh.kampusonline.ui.fragments.rencanastudi.adapter.RencanaStudiAdapter;

import butterknife.BindView;
import butterknife.OnClick;

import static com.luteh.kampusonline.ui.fragments.rencanastudi.adapter.RencanaStudiGroupDataFactory.makeRencanaStudiGroups;

/**
 * A simple {@link Fragment} subclass.
 */
public class RencanaStudiFragment extends BaseFragment implements IRencanaStudiView {

    private IRencanaStudiPresenter iRencanaStudiPresenter;
    private RencanaStudiAdapter adapter;

    @BindView(R.id.rvRencanaStudi)
    ShimmerRecyclerView rvRencanaStudi;
    @BindView(R.id.etTahunAkademik)
    TextInputEditText etTahunAkademik;
    @BindView(R.id.etIpkTerakhir)
    TextInputEditText etIpkSks;
    @BindView(R.id.etJatahSks)
    TextInputEditText etSksMatkul;
    @BindView(R.id.btnRencanaStudi)
    MaterialButton btnRencanaStudi;

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
        rvRencanaStudi.showShimmerAdapter();
    }

    @OnClick(R.id.btnRencanaStudi)
    void onClickBtnRencanaStudi() {
        Common.showSuccessMessage(context, getString(R.string.label_message_success_submit_frs));
    }

    @Override
    public void onRetrieveDataSuccess(FRencanaStudi fRencanaStudi) {
        rvRencanaStudi.hideShimmerAdapter();
        etTahunAkademik.setText(fRencanaStudi.tahun_akademik);
        etIpkSks.setText(fRencanaStudi.ipk_terakhir);
        etSksMatkul.setText(getString(R.string.label_jatah_sks, fRencanaStudi.jatah_sks));
        adapter = new RencanaStudiAdapter(makeRencanaStudiGroups(fRencanaStudi));
        rvRencanaStudi.setAdapter(adapter);
    }
}
