package com.example.thuctapimtech.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.thuctapimtech.R;


public class TrangchuFragment extends Fragment {



    public TrangchuFragment() {
        // Required empty public constructor
    }


    Button btndonhang,btnsanpham,btnquanly,btnthongtin,btndoanhthu,btntop10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trangchu, container, false);
        btndonhang = view.findViewById(R.id.btndonhang);
        btnsanpham = view.findViewById(R.id.btnsanpham);
        btnquanly = view.findViewById(R.id.btnquanlytk);
        btnthongtin = view.findViewById(R.id.btnthongtintk);
        btndoanhthu = view.findViewById(R.id.btndoanhthu);
        btntop10 = view.findViewById(R.id.btntop10);
        return view;


    }
//    public static TrangchuFragment newInstance(){
//        TrangchuFragment fragment = new TrangchuFragment();
//        return fragment;
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btndonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbarTitle("Quản lý Đơn hàng");
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayout_home, HoaDonFragment.newInstance()).commit();

            }
        });

        btnsanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbarTitle("Quản lý Sản phẩm");
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayout_home, SanPhamFragment.newInstance()).commit();
            }
        });

        btnquanly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbarTitle("Quản lý tài khoản");
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayout_home, QuanLyTKFragment.newInstance()).commit();
            }
        });

//        btnthongtin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setToolbarTitle("Thông tin tài khoản");
//                FragmentManager manager = getActivity().getSupportFragmentManager();
//                manager.beginTransaction().replace(R.id.frameLayout_home, DoiMatKhauFragment.newInstance()).commit();
//            }
//        });

        btndoanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbarTitle("Doanh thu");
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayout_home, DoanhThuFragment.newInstance()).commit();
            }
        });

        btntop10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setToolbarTitle("Top 10 sản phẩm bán chạy");
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.frameLayout_home, Top10Fragment.newInstance()).commit();
            }
        });
    }

    private void setToolbarTitle(String title) {
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(title);
            }
        }
    }





}