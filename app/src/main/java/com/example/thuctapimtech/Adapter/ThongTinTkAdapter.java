package com.example.thuctapimtech.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.thuctapimtech.DAO.DAOQuanTriVien;
import com.example.thuctapimtech.Fragment.DoiMatKhauFragment;
import com.example.thuctapimtech.Model.QuanTriVien;
import com.example.thuctapimtech.R;

import java.util.ArrayList;

public class ThongTinTkAdapter extends ArrayAdapter<QuanTriVien> {

    private Context context;
    DoiMatKhauFragment fragment;
    private ArrayList<QuanTriVien> list;
    TextView tvMaQTV,tvTenTK,tvMatKhau;

    Button btndoimatkhau1;
    ImageView imgDel;
    DAOQuanTriVien dao;

    public ThongTinTkAdapter(@NonNull Context context, DoiMatKhauFragment fragment, ArrayList<QuanTriVien> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }


    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        dao = new DAOQuanTriVien(getContext());
        if (v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_quantrivien,null);

        }

        final QuanTriVien item = list.get(position);
        if (item != null){
            tvMaQTV = v.findViewById(R.id.tvMaTT);
            tvMaQTV.setText("Mã QTV: "+item.getMaQTV());
            tvTenTK = v.findViewById(R.id.tvTenTT);
            tvTenTK.setText("Họ tên: "+item.getHoTen());
            tvMatKhau = v.findViewById(R.id.tvMatKhau);
            tvMatKhau.setText("Mật khẩu: "+item.getMatKhau());


//            imgDel = v.findViewById(R.id.imgDeleteLS);
        }




//        imgDel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // pt xoa
//                fragment.xoa(item.getMaQTV());
//            }
//        });
        return v;
    }
}
