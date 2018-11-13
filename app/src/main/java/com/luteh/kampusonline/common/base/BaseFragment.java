package com.luteh.kampusonline.common.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Luthfan Maftuh on 10/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class BaseFragment extends Fragment {

    protected Context context;
    private Unbinder unbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        this.context = getContext();
        onInit();
    }

    protected void onInit() {
        // override
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
