package com.example.thuctapimtech;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.thuctapimtech.Activity.DangNhapKH_Activity;
import com.example.thuctapimtech.Activity.GioHangActivity;
import com.example.thuctapimtech.Fragment.Home_KH_Fragment;
import com.example.thuctapimtech.Fragment.ThongTin_KH_Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class TrangChuKH extends AppCompatActivity {

    BottomNavigationView bottm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_kh);

        bottm = findViewById(R.id.navigation);

        bottm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                 if(item.getItemId() == R.id.navigation_home){
                     Home_KH_Fragment home = new Home_KH_Fragment();
                     replaceFragment(home);
                 } else if(item.getItemId() == R.id.navigation_person){
                     ThongTin_KH_Fragment thongTin = new ThongTin_KH_Fragment();
                     replaceFragment(thongTin);
                 } else if(item.getItemId() == R.id.navigation_cart){
                    startActivity(new Intent(TrangChuKH.this, GioHangActivity.class));
                 } else if(item.getItemId() == R.id.navigation_out){
                     AlertDialog.Builder buider = new AlertDialog.Builder(TrangChuKH.this);
                     buider.setMessage("Bạn có muốn đăng xuất?");
                     buider.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             startActivity(new Intent(TrangChuKH.this, DangNhapKH_Activity.class));
                         }
                     });

                     buider.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             dialogInterface.dismiss();
                         }
                     });

                     buider.create();
                     buider.show();
                 }
                return true;
            }
        });

        // Đặt fragment mặc định được hiển thị khi activity bắt đầu
        bottm.setSelectedItemId(R.id.navigation_home);

        if (untils.mangGioHang == null){
            untils.mangGioHang = new ArrayList<>();
        }

        if (untils.donHangAdmin == null){
            untils.donHangAdmin = new ArrayList<>();
        }

        if (untils.hoadonAdmin == null){
            untils.hoadonAdmin = new ArrayList<>();
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }
}