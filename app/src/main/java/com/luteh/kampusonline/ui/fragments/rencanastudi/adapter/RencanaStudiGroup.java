package com.luteh.kampusonline.ui.fragments.rencanastudi.adapter;

import android.os.Parcel;

import com.luteh.kampusonline.model.rencanastudi.Semester;
import com.thoughtbot.expandablecheckrecyclerview.models.MultiCheckExpandableGroup;

import java.util.List;

/**
 * Created by Luthfan Maftuh on 29/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class RencanaStudiGroup extends MultiCheckExpandableGroup {
    public RencanaStudiGroup(String title, List<Semester> items) {
        super(title, items);
    }
}
