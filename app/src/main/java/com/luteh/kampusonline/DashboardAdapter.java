package com.luteh.kampusonline;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Luthfan Maftuh on 10/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    private Drawable[] drawableItems;
    private String[] labelItems;


    public DashboardAdapter(Drawable[] drawableItems, String[] labelItems) {
        /*super(itemView);
        ButterKnife.bind(this, itemView);*/

        this.drawableItems = drawableItems;
        this.labelItems = labelItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_dashboard_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imgDashboardListItem)
        ImageView imgDashboardListItem;
        @BindView(R.id.tvDashboardListItem)
        TextView tvDashboardListItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
