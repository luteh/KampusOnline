package com.luteh.kampusonline;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.luteh.kampusonline.common.Common;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Luthfan Maftuh on 10/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {

    private TypedArray drawableItems;
    private String[] labelItems;
    private Context context;

    public DashboardAdapter(Context context, TypedArray drawableItems, String[] labelItems) {
        this.context = context;
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
        Picasso.get()
                .load(drawableItems.getResourceId(position, -1))
                .resize(150, 150)
                .into(holder.imgDashboardListItem);
        holder.tvDashboardListItem.setText(labelItems[position]);
    }


    @Override
    public int getItemCount() {
        return drawableItems.length();
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

        @OnClick
        public void onClick(View v) {
            /*switch (this.getLayoutPosition()) {
                case 0:
                    Common.showSuccessMessage(context, labelItems[getLayoutPosition()]);
                    break;
            }*/
            Common.showToastMessage(context, labelItems[getLayoutPosition()]);
        }
    }
}
