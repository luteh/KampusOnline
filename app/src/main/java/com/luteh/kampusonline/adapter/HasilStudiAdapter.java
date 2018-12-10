package com.luteh.kampusonline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luteh.kampusonline.R;
import com.luteh.kampusonline.model.hasilstudi.HasilStudi;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Luthfan Maftuh on 26/11/2018.
 * Email luthfanmaftuh@gmail.com
 */
public class HasilStudiAdapter extends RecyclerView.Adapter<HasilStudiViewHolder> {

    private List<HasilStudi> hasilStudiList;
    private Context context;

    public HasilStudiAdapter(Context context, List<HasilStudi> hasilStudiList) {
        this.hasilStudiList = hasilStudiList;
        this.context = context;
    }

    @NonNull
    @Override
    public HasilStudiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hasil_studi_item, parent, false);
        return new HasilStudiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HasilStudiViewHolder holder, int position) {
        holder.tvNumber.setText((position + 1) + "");
        holder.tvKode.setText(hasilStudiList.get(position).kode);
        holder.tvMataKuliah.setText(hasilStudiList.get(position).mata_kuliah);
        holder.tvSks.setText(String.format("%s SKS", hasilStudiList.get(position).sks));
        if (hasilStudiList.get(position).nilai.equals(""))
            holder.tvNilai.setText("-");
        else
            holder.tvNilai.setText(hasilStudiList.get(position).nilai);


//        setNilaiTextStyle(holder, position);
    }

    // change Nilai TextView color
    private void setNilaiTextStyle(HasilStudiViewHolder holder, int position) {
        switch (hasilStudiList.get(position).nilai) {
            case "A":
                holder.tvNilai.setTextColor(context.getResources().getColor(R.color.colorGreenText));
                break;
            case "B":
                holder.tvNilai.setTextColor(context.getResources().getColor(R.color.colorGreenTransparentText));
                break;
            case "C":
                holder.tvNilai.setTextColor(context.getResources().getColor(R.color.colorYellowTransparentText));
                break;
            case "D":
                holder.tvNilai.setTextColor(context.getResources().getColor(R.color.colorRedTransparentText));
                break;
            case "E":
                holder.tvNilai.setTextColor(context.getResources().getColor(R.color.colorRedText));
                break;
            default:
                holder.tvNilai.setTextColor(context.getResources().getColor(R.color.colorSmallText));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return hasilStudiList.size();
    }
}
