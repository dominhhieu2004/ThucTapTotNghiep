package com.example.thuctapimtech.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.thuctapimtech.DAO.DaoKhachHang;
import com.example.thuctapimtech.Fragment.QuanLyTKFragment;
import com.example.thuctapimtech.Model.KhachHang;
import com.example.thuctapimtech.R;

import java.util.ArrayList;

public class ThongTinKhAdapter extends ArrayAdapter<KhachHang> {

    private Context context;
    QuanLyTKFragment fragment;
    private ArrayList<KhachHang> list;
    TextView MaKH,TenKH,SdtKH,DiaChiKH,MatKhauKH;


    ImageView imgDel;
    DaoKhachHang dao;

    public ThongTinKhAdapter(@NonNull Context context, QuanLyTKFragment fragment, ArrayList<KhachHang> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        dao = new DaoKhachHang(getContext());
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_thongtinkh,null);

        }

        final KhachHang item = list.get(position);
        if (item != null){
            MaKH = v.findViewById(R.id.MaKH);
            MaKH.setText("Mã Khách hàng : "+item.getMaKH());

            TenKH = v.findViewById(R.id.TenKH);
            TenKH.setText("Họ tên : "+item.getHoTen());

            SdtKH = v.findViewById(R.id.SdtKH);
            SdtKH.setText("Số điện thoại : "+item.getDienThoai());

            DiaChiKH  = v.findViewById(R.id.DiaChiKH);
            DiaChiKH.setText("Địa chỉ : "+item.getDiaChi());

//            MatKhauKH = v.findViewById(R.id.MatKhauKH);
//            MatKhauKH.setText("Mật khẩu : "+item.getMatKhau());


            imgDel = v.findViewById(R.id.imgDeleteLS);
        }




        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xoa
                fragment.xoa(item.getMaKH());
            }
        });
        return v;
    }
}
