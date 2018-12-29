package com.luteh.kampusonline.ui.fragments.rencanastudi.adapter;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.luteh.kampusonline.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by Luthfan Maftuh on 28/12/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class RencanaStudiGroupViewHolder extends GroupViewHolder {

    @BindView(R.id.tvFrsSemester)
    TextView tvFrsSemester;
    @BindView(R.id.imgFrsArrow)
    ImageView imgFrsArrow;

    public RencanaStudiGroupViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setGroupTitle(ExpandableGroup genre){
        tvFrsSemester.setText(genre.getTitle());
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        imgFrsArrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        imgFrsArrow.setAnimation(rotate);
    }
}
