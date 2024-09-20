package com.example.thuctapimtech.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuctapimtech.DAO.DaoSanPham;
import com.example.thuctapimtech.Model.EventBus.TinhTongEvent;
import com.example.thuctapimtech.Model.GioHang;
import com.example.thuctapimtech.Model.IClickItemRCV;
import com.example.thuctapimtech.Model.IImageClichListener;
import com.example.thuctapimtech.Model.SanPham;
import com.example.thuctapimtech.R;
import com.example.thuctapimtech.untils;
import com.example.thuctapimtech.DAO.DaoSanPham;
import com.example.thuctapimtech.Model.GioHang;
import com.example.thuctapimtech.Model.IClickItemRCV;
import com.example.thuctapimtech.Model.SanPham;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {

    private Context context;
    private List<GioHang> lstGioHang;


    IClickItemRCV clickItemRCV;

    public GioHangAdapter(Context context, List<GioHang> lstGioHang, IClickItemRCV clickItemRCV) {
        this.context = context;
        this.lstGioHang = lstGioHang;
        this.clickItemRCV = clickItemRCV;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       GioHang gh = lstGioHang.get(position);

       DaoSanPham daosp = new DaoSanPham(context);
        SanPham sanPham = daosp.getID(String.valueOf(gh.getMaSP()));

       holder.tvmaSP.setText(sanPham.getTenSP());
       holder.tvTenSP.setText(sanPham.getTenSP());
       holder.tvHangSP.setText(sanPham.getTenHang());
       holder.tvGia.setText("Giá:" + sanPham.getGiaTien() + "đ" );
       holder.tvSoLuong.setText(gh.getSoluong() + " ");
        Picasso.get().load(sanPham.getImages()).into(holder.images);

        holder.chkGioHang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    //true
                    untils.mangGioHang.get(holder.getAdapterPosition()).setCheck(true);
                        if (!untils.mangMuaHang.contains(gh)){
                            untils.mangMuaHang.add(gh); 
                        }
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }else {
                    untils.mangGioHang.get(holder.getAdapterPosition()).setCheck(false);
                  for (int i = 0; i<untils.mangMuaHang.size(); i++){
                      if(untils.mangMuaHang.get(i).getMaSP() == gh.getMaSP()){
                          untils.mangMuaHang.remove(i);
                          EventBus.getDefault().postSticky(new TinhTongEvent());
                      }
                  }
                }
            }
        });

        holder.chkGioHang.setChecked(gh.isCheck());

       holder.setListenner(new IImageClichListener() {
           @Override
           public void onImageClick(View view, int pos, int giaTri) {
               if(giaTri ==1){
                 if(lstGioHang.get(pos).getSoluong() > 1){
                     int soLuongMoi = lstGioHang.get(pos).getSoluong()-1;
                     lstGioHang.get(pos).setSoluong(soLuongMoi);
                     notifyDataSetChanged();
                 }
               } else if(giaTri == 2){
                   if(lstGioHang.get(pos).getSoluong() < 100){
                       int soLuongMoi = lstGioHang.get(pos).getSoluong()+1;
                       lstGioHang.get(pos).setSoluong(soLuongMoi);
                       notifyDataSetChanged();
                   }
               }

               holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       handleDeleteButtonClick(position);
                   }
               });

//               holder.btnDelete.setOnClickListener(new View.OnClickListener() {
//                   @Override
//                   public void onClick(View view) {
//                       AlertDialog.Builder buider = new AlertDialog.Builder(view.getRootView().getContext());
//                       buider.setTitle("Thông báo");
//                       buider.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏ hàng!");
//                       buider.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialogInterface, int i) {
//
//                           }
//                       });
//
//                       buider.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                           @Override
//                           public void onClick(DialogInterface dialogInterface, int i) {
//                               dialogInterface.dismiss();
//                           }
//                       });
//                       buider.show();
//                   }
//               });
               holder.tvSoLuong.setText(lstGioHang.get(pos).getSoluong() + " ");
              EventBus.getDefault().postSticky(new TinhTongEvent());
           }
       });


    }

    @Override
    public int getItemCount() {
       return lstGioHang != null ? lstGioHang.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvmaSP, tvTenSP, tvHangSP, tvGia, tvSoLuong;
        ImageView btnDelete, images, imagesTru, imagesCong;

        IImageClichListener listenner;

        CheckBox chkGioHang;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvmaSP = itemView.findViewById(R.id.maSP_gioHang);
            tvTenSP = itemView.findViewById(R.id.tensanpham_gioHang);
            tvHangSP = itemView.findViewById(R.id.tenHang_gioHang);
            tvGia = itemView.findViewById(R.id.giatien_gioHang);
            tvSoLuong= itemView.findViewById(R.id.soLuong_gioHang);
            images = itemView.findViewById(R.id.images_gioHang);

            chkGioHang = itemView.findViewById(R.id.item_gioHang_check);
            btnDelete = itemView.findViewById(R.id.btn_delete_gioHang);

            imagesTru = itemView.findViewById(R.id.item_gioHang_tru);
            imagesCong = itemView.findViewById(R.id.item_gioHang_cong);

            //event click
            imagesCong.setOnClickListener(this);
            imagesTru.setOnClickListener(this);
        }

        public void setListenner(IImageClichListener listenner) {
            this.listenner = listenner;
        }

        @Override
        public void onClick(View view) {
            if(view == imagesTru){
                listenner.onImageClick(view, getAdapterPosition(), 1);
                //1 tru
            }else if(view == imagesCong){
                listenner.onImageClick(view, getAdapterPosition(), 2);
                //2 cộng
            }
        }
    }

    private void handleDeleteButtonClick(int position) {
        GioHang gioHang = lstGioHang.get(position);
        lstGioHang.remove(position);
        untils.mangMuaHang.remove(gioHang);
        EventBus.getDefault().postSticky(new TinhTongEvent());
        notifyDataSetChanged();
        Toast.makeText(context, "Đã xoá sản phẩm khỏi giỏ hàng", Toast.LENGTH_SHORT).show();
    }

}
