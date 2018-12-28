package com.luteh.kampusonline.ui.fragments.rencanastudi.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;
import com.thoughtbot.expandablecheckrecyclerview.CheckableChildRecyclerViewAdapter;
import com.thoughtbot.expandablecheckrecyclerview.models.CheckedExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by Luthfan Maftuh on 28/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class RencanaStudiAdapter extends
        CheckableChildRecyclerViewAdapter<RencanaStudiGroupViewHolder, RencanaStudiChildViewHolder> {
    public RencanaStudiAdapter(List<? extends CheckedExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public RencanaStudiChildViewHolder onCreateCheckChildViewHolder(ViewGroup parent, int viewType) {
        return new RencanaStudiChildViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.fragment_rencana_studi_child_item, parent, false)
        );
    }

    @Override
    public void onBindCheckChildViewHolder(RencanaStudiChildViewHolder holder, int flatPosition, CheckedExpandableGroup group, int childIndex) {

    }

    @Override
    public RencanaStudiGroupViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return new RencanaStudiGroupViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_rencana_studi_group_item, parent, false)
        );
    }

    @Override
    public void onBindGroupViewHolder(RencanaStudiGroupViewHolder holder, int flatPosition, ExpandableGroup group) {

    }
}
