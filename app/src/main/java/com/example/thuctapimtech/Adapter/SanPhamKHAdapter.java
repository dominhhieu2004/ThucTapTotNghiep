package com.example.thuctapimtech.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuctapimtech.Activity.ChiTietSanPham;
import com.example.thuctapimtech.Model.IClickItemRCV;
import com.example.thuctapimtech.Model.SanPham;
import com.example.thuctapimtech.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamKHAdapter extends RecyclerView.Adapter<SanPhamKHAdapter.ViewHolder> {
    private Context context;
    private List<SanPham> lstSPKH;


    IClickItemRCV clickItemRCV;

    public SanPhamKHAdapter(Context context, List<SanPham> lstSPKH, IClickItemRCV clickItemRCV) {
        this.context = context;
        this.lstSPKH = lstSPKH;
        this.clickItemRCV = clickItemRCV;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham_kh,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPham sanPham = lstSPKH.get(position);

        holder.tvMASP.setText("Mã Sản phẩm:SP " + sanPham.getMaSP());
        holder.tvHangSP.setText("Tên hàng: " + sanPham.getTenHang());
        holder.tvTenSP.setText("Tên sản phẩm: " + sanPham.getTenSP());
        holder.tvGia.setText("Giá tiền:$"+ sanPham.getGiaTien());

        Picasso.get().load(sanPham.getImages()).into(holder.imgaesSPKH);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ChiTietSanPham.class);
                i.putExtra("id", lstSPKH.get(holder.getAdapterPosition()).getMaSP());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstSPKH != null ? lstSPKH.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTenSP, tvHangSP, tvGia, tvMASP;
        ImageView imgaesSPKH;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMASP = itemView.findViewById(R.id.sp_maSPKH);
            tvGia = itemView.findViewById(R.id.sp_giatienKH);
            tvHangSP = itemView.findViewById(R.id.sp_tenHangKH);
            tvTenSP = itemView.findViewById(R.id.sp_tensanphamKH);
            imgaesSPKH = itemView.findViewById(R.id.images_spKH);
        }
    }
}
