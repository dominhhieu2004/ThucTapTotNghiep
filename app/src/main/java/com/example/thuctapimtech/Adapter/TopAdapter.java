package com.example.thuctapimtech.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.thuctapimtech.Model.Top;
import com.example.thuctapimtech.R;

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder> {

    Context context;
    ArrayList<Top> lstTop;

    public TopAdapter(Context context, ArrayList<Top> lstTop) {
        this.context = context;
        this.lstTop = lstTop;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top10, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
             Top top = lstTop.get(position);

             holder.tv_stt.setText(String.valueOf(holder.getAdapterPosition() + 1));
             holder.tv_sanPham.setText(top.getTenSP());
             holder.tv_soLuong.setText(String.valueOf(top.getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return lstTop != null ? lstTop.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_stt, tv_sanPham, tv_soLuong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_stt = itemView.findViewById(R.id.tv_stt_top);
            tv_sanPham = itemView.findViewById(R.id.tv_tenSanPham_top);
            tv_soLuong = itemView.findViewById(R.id.tv_soluong_top);
        }
    }
}
