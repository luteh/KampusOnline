package com.luteh.kampusonline.ui.fragments.jadwal;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.luteh.kampusonline.R;
import com.luteh.kampusonline.common.base.BaseFragment;
import com.luteh.kampusonline.common.utils.CustomViewPager;
import com.luteh.kampusonline.ui.fragments.jadwal.kuliah.JadwalKuliahFragment;
import com.luteh.kampusonline.ui.fragments.jadwal.pengganti.JadwalPenggantiFragment;
import com.luteh.kampusonline.ui.fragments.jadwal.susulan.JadwalSusulanFragment;
import com.luteh.kampusonline.ui.fragments.jadwal.ujian.JadwalUjianFragment;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class JadwalFragment extends BaseFragment {

    private JadwalPagerAdapter adapter;

    @BindView(R.id.tabsJadwal)
    TabLayout tabsJadwal;
    @BindView(R.id.vpJadwal)
    CustomViewPager vpJadwal;

    public JadwalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jadwal, container, false);
    }

    @Override
    protected void onInit() {
        super.onInit();
        vpJadwal.setPagingEnabled(true);

        setupViewPager();

        tabsJadwal.setupWithViewPager(vpJadwal);
    }

    private void setupViewPager() {
        adapter.addFragment(new JadwalKuliahFragment(), getResources().getString(R.string.title_kuliah_fragment));
        adapter.addFragment(new JadwalPenggantiFragment(), getResources().getString(R.string.title_penggangti_fragment));
        adapter.addFragment(new JadwalUjianFragment(), getResources().getString(R.string.title_ujian_fragment));
        adapter.addFragment(new JadwalSusulanFragment(), getResources().getString(R.string.title_susulan_fragment));
        vpJadwal.setAdapter(adapter);
        vpJadwal.setOffscreenPageLimit(adapter.getCount() - 1);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adapter = new JadwalPagerAdapter(context, getChildFragmentManager());
    }
}
