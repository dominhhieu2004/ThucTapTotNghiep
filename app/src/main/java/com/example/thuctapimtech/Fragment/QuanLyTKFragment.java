package com.example.thuctapimtech.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.thuctapimtech.Adapter.ThongTinKhAdapter;
import com.example.thuctapimtech.DAO.DaoKhachHang;
import com.example.thuctapimtech.Model.KhachHang;
import com.example.thuctapimtech.R;

import java.util.ArrayList;


public class QuanLyTKFragment extends Fragment {


    ListView lvKH;
    ArrayList<KhachHang> list;



    static DaoKhachHang dao;
    ThongTinKhAdapter thongtinKhAdapter;
    KhachHang item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =  inflater.inflate(R.layout.fragment_quan_ly_t_k, container, false);
        lvKH = v.findViewById(R.id.lvKH);
        dao = new DaoKhachHang(getActivity());
        capNhatLv();
       return  v;
    }

    void capNhatLv() {
        list = (ArrayList<KhachHang>) dao.getAll();
        thongtinKhAdapter = new ThongTinKhAdapter(getActivity(), this, list);
        lvKH.setAdapter(thongtinKhAdapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Không xóa", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    public static QuanLyTKFragment newInstance(){
        QuanLyTKFragment fragment = new QuanLyTKFragment();
        return fragment;
    }
}