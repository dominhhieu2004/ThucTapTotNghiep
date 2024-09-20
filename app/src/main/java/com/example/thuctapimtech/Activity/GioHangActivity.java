package com.example.thuctapimtech.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuctapimtech.Adapter.GioHangAdapter;
import com.example.thuctapimtech.DAO.DaoGioHang;
import com.example.thuctapimtech.DAO.DaoHoaDon;
import com.example.thuctapimtech.DAO.DaoKhachHang;
import com.example.thuctapimtech.DAO.DaoSanPham;
import com.example.thuctapimtech.Model.EventBus.TinhTongEvent;
import com.example.thuctapimtech.Model.GioHang;
import com.example.thuctapimtech.Model.HoaDon;
import com.example.thuctapimtech.Model.IClickItemRCV;
import com.example.thuctapimtech.Model.KhachHang;

import com.example.thuctapimtech.Adapter.GioHangAdapter;
import com.example.thuctapimtech.DAO.DaoGioHang;
import com.example.thuctapimtech.DAO.DaoSanPham;
import com.example.thuctapimtech.Model.GioHang;
import com.example.thuctapimtech.Model.KhachHang;
import com.example.thuctapimtech.R;
import com.example.thuctapimtech.TrangChuKH;
import com.example.thuctapimtech.untils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class GioHangActivity extends AppCompatActivity {


    RecyclerView rcvGioHang;
    ArrayList<GioHang> lstGioHang;

    static DaoGioHang daoGH;

    GioHangAdapter gioAdapter;

    TextView tvTongTien;

    static DaoSanPham daoSanPham;

    GioHang gh;

    KhachHang kh;

    Button btnAddDonHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);

        tvTongTien = findViewById(R.id.tvTongTienGioHang);
        btnAddDonHang = findViewById(R.id.btnMuaHang);

        lstGioHang = new ArrayList<>();
        daoSanPham = new DaoSanPham(this);
        daoGH = new DaoGioHang(this);


        rcvGioHang = findViewById(R.id.rcv_gioHang);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvGioHang.setLayoutManager(layoutManager);
        gioAdapter = new GioHangAdapter(getApplicationContext(), untils.mangGioHang, new IClickItemRCV() {
            @Override
            public void iclickItem(RecyclerView.ViewHolder viewHolder, int position, int type) {

            }
        });
        rcvGioHang.setAdapter(gioAdapter);


        intColtrol();

        if(untils.mangMuaHang != null){
            untils.mangMuaHang.clear();
        }
        tinhTongTien();


        if(untils.mangMuaHang != null){
            untils.mangMuaHang.clear();
        }
        tinhTongTien();
//        bottm = findViewById(R.id.navigation);
//        bottm.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if(item.getItemId() == R.id.navigation_home){
//                    Home_KH_Fragment home = new Home_KH_Fragment();
//                    replaceFragment(home);
//                } else if(item.getItemId() == R.id.navigation_person){
//                    ThongTin_KH_Fragment thongTin = new ThongTin_KH_Fragment();
//                    replaceFragment(thongTin);
//                } else if(item.getItemId() == R.id.navigation_cart){
//                    startActivity(new Intent(GioHangActivity.this, GioHangActivity.class));
//                } else if(item.getItemId() == R.id.navigation_out){
//                    AlertDialog.Builder buider = new AlertDialog.Builder(GioHangActivity.this);
//                    buider.setMessage("Bạn có muốn đăng xuất?");
//                    buider.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            startActivity(new Intent(GioHangActivity.this, DangNhap_KH.class));
//                        }
//                    });
//
//                    buider.setNegativeButton("Không", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    });
//
//                    buider.create();
//                    buider.show();
//                }
//                return true;
//            }
//        });

    }

    private void intColtrol() {
        btnAddDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themDonHang();
                for (int i =0; i<untils.mangMuaHang.size();i++){
                    GioHang gioHang = untils.mangMuaHang.get(i);
                    if(untils.mangGioHang.contains(gioHang)){
                        untils.mangGioHang.remove(gioHang);
                    }
                }
                untils.mangMuaHang.clear();
//                Toast.makeText(GioHangActivity.this, "Cảm ơn bạn đã mua hàng!", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(GioHangActivity.this, TrangChuKH.class);
                startActivity(in);
            }
        });
    }

    private void themDonHang() {
        DaoKhachHang khDao = new DaoKhachHang(getApplicationContext());

        DaoHoaDon hdDao = new DaoHoaDon(getApplicationContext());
        HoaDon hoaDon = new HoaDon();
        int tongTienSP =0;

        for(int i =0; i<untils.mangMuaHang.size(); i++){
            tongTienSP = tongTienSP + (untils.mangMuaHang.get(i).getGia() * untils.mangMuaHang.get(i).getSoluong());
        }

        int maHD = 1;
        for (int i = 0; i< untils.hoadonAdmin.size(); i++){
            maHD = 1 + (untils.hoadonAdmin.get(i).getIdDonHang());
        }

        int maSP;
        for(int i = 0; i< untils.mangMuaHang.size(); i++){
            maSP = untils.mangMuaHang.get(i).getMaSP();
            hoaDon.setMaSP(maSP);
        }


        SharedPreferences pref = getApplicationContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String user = pref.getString("USERNAME","");

//        hoaDon.setIdDonHang(maHD);
        hoaDon.setTongTien(tongTienSP);

        hoaDon.setMaKH(user);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String dateString = simpleDateFormat.format(date);
        hoaDon.setNgayDat(dateString);

        if(tongTienSP == 0){
            Toast.makeText(this, "Hãy chọn sản phẩm bạn muốn mua", Toast.LENGTH_SHORT).show();
        } else {
//            untils.hoadonAdmin.add(hoaDon);
            DaoHoaDon daoHD = new DaoHoaDon(getApplicationContext());
            if(daoHD.insertHoaDon(hoaDon) > 0){
                Toast.makeText(this, "Cám ơn bạn đã mua hàng", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Mua thất bại!", Toast.LENGTH_SHORT).show();
            }

        }

//        untils.hoadonAdmin.add(hoaDon);
    }

    private void tinhTongTien() {
        long tongTienSP =0;

        for(int i =0; i<untils.mangMuaHang.size(); i++){
            tongTienSP = tongTienSP + (untils.mangMuaHang.get(i).getGia() * untils.mangMuaHang.get(i).getSoluong());
        }
        tvTongTien.setText(String.valueOf(tongTienSP) + " đ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe (sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTong(TinhTongEvent event){
        if(event != null){
            tinhTongTien();
        }
    }
    
}