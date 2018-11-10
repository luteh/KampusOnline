package com.luteh.kampusonline.common.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Luthfan Maftuh on 10/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class BaseFragment extends Fragment {

    protected Context context;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        this.context = getContext();
    }
}
