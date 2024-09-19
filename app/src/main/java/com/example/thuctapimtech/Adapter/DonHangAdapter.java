package com.example.thuctapimtech.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.thuctapimtech.DAO.DaoKhachHang;
import com.example.thuctapimtech.DAO.DaoSanPham;
import com.example.thuctapimtech.Model.HoaDon;
import com.example.thuctapimtech.Model.KhachHang;
import com.example.thuctapimtech.Model.SanPham;
import com.example.thuctapimtech.R;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {

    private RecyclerView.RecycledViewPool viewpool = new RecyclerView.RecycledViewPool();
    Context context;
    List<HoaDon> lstHoaDon;



    public DonHangAdapter(Context context, List<HoaDon> lstHoaDon) {
        this.context = context;
        this.lstHoaDon = lstHoaDon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdon_hang, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DaoKhachHang daoKH = new DaoKhachHang(context);
        DaoSanPham daoSP = new DaoSanPham(context);

        HoaDon hoaDon = lstHoaDon.get(position);

        SanPham sanPham = daoSP.getID(String.valueOf(hoaDon.getMaSP()));

        holder.txtdonHang.setText("Đơn hàng:" + hoaDon.getIdDonHang());

        KhachHang kh = daoKH.getID(hoaDon.getMaKH());
        holder.txtMakh.setText("Mã khách hàng:" + kh.getMaKH());
        holder.txtTenSP.setText("Tên sản phẩm:" + sanPham.getTenSP());
        holder.txtTongTien.setText("Tổng tiền:" + String.valueOf(hoaDon.getTongTien()) +" đ");
        holder.txtNgayDat.setText("Ngày đặt:" + hoaDon.getNgayDat());
    }

    @Override
    public int getItemCount() {
         return lstHoaDon != null ? lstHoaDon.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView  txtdonHang, txtMakh, txtTongTien, txtNgayDat, txtTenSP;
        RecyclerView reChiTiet;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdonHang = itemView.findViewById(R.id.idDonHang);
            txtMakh = itemView.findViewById(R.id.idKhachHang);
            txtTongTien = itemView.findViewById(R.id.tongTien);
            txtNgayDat = itemView.findViewById(R.id.ngayDat);
            txtTenSP  = itemView.findViewById(R.id.idSanPham);
        }
    }
}
