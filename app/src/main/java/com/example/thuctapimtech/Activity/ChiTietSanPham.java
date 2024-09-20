package com.example.thuctapimtech.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thuctapimtech.DAO.DaoGioHang;
import com.example.thuctapimtech.DAO.DaoSanPham;
import com.example.thuctapimtech.Model.GioHang;
import com.example.thuctapimtech.Model.SanPham;
import com.example.thuctapimtech.untils;
import com.squareup.picasso.Picasso;
import com.example.thuctapimtech.R;

public class ChiTietSanPham extends AppCompatActivity {

    int maSP;
//test
 //test2
    //test3
    TextView tvTenSP, tvHang, tvMoTa, tvGia;
    ImageView images, imagesGH;

    Button btnAddGioHang;

    SanPham sp;

    DaoGioHang daoGH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        Intent i =  getIntent();
        maSP = i.getIntExtra("id", 0);
        DaoSanPham daoSp = new DaoSanPham(this);
        sp = daoSp.selectID(maSP);

        daoGH = new DaoGioHang(this);

        tvTenSP = findViewById(R.id.tvTenCTSP_kh);
        tvHang = findViewById(R.id.tvHangCTSP_kh);
        tvGia = findViewById(R.id.tvGiaCTSP_kh);
        tvMoTa = findViewById(R.id.tvMoTaCTSP_kh);
        images  = findViewById(R.id.imgCTSP);

        btnAddGioHang = findViewById(R.id.btnAddGioHang);

        tvTenSP.setText("Tên sản phẩm:" + sp.getTenSP().toString());
        tvHang.setText("Tên hãng:"+sp.getTenHang().toString());
        tvGia.setText("Giá:" + String.valueOf(sp.getGiaTien()));
        tvMoTa.setText("Mô tả:"+sp.getMoTa().toString());

        Picasso.get().load(sp.getImages()).into(images);

      initControl();
    }


    private void initControl() {
        btnAddGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theGioHang();
                Toast.makeText(ChiTietSanPham.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(ChiTietSanPham.this, GioHangActivity.class);
                startActivity(in);
            }
        });
    }

    private void theGioHang() {

        imagesGH = findViewById(R.id.images_gioHang);
        if(untils.mangGioHang.size() > 0){
             boolean flag = false;
            int soLuong = 1;
            for (int i = 0; i< untils.mangGioHang.size(); i++){
                if(untils.mangGioHang.get(i).getMaSP() == sp.getMaSP()){
                    flag = true;
                }
            }
            if(flag == false){
                int gia = sp.getGiaTien() * soLuong;

                GioHang gioHang = new GioHang();
                gioHang.setGia(gia);
                gioHang.setSoluong(soLuong);
                gioHang.setMaSP(sp.getMaSP());
                gioHang.setTenSP(sp.getTenSP());
                gioHang.setHangSP(sp.getTenHang());
                gioHang.setImagesGH(sp.getImages());

                untils.mangGioHang.add(gioHang);
            }

        } else {
            int soLuong = 1;
            int gia = sp.getGiaTien() * soLuong;

           GioHang gioHang = new GioHang();
            gioHang.setGia(gia);
            gioHang.setSoluong(soLuong);
            gioHang.setMaSP(sp.getMaSP());
            gioHang.setTenSP(sp.getTenSP());
            gioHang.setHangSP(sp.getTenHang());

            untils.mangGioHang.add(gioHang);
        }
    }


}