package com.example.thuctapimtech.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thuctapimtech.DAO.DaoKhachHang;
import com.example.thuctapimtech.R;

public class DangKyKH_Activity extends AppCompatActivity {

    EditText edHoTen, edDiaChi, edSDT, edTaiKhoan, edMatKhau, edConf;
    Button btnout, btnDK;

    DaoKhachHang daoKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_ky_kh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edHoTen = findViewById(R.id.edHoTen_dk);
        edDiaChi = findViewById(R.id.edDiaChi_dk);
        edSDT = findViewById(R.id.edSDT_dk);
        edTaiKhoan = findViewById(R.id.edTaiKhoan_dk);
        edMatKhau = findViewById(R.id.edMatKhau_dk);
        edConf = findViewById(R.id.edConf_dk);
        btnDK = findViewById(R.id.btnDk_dk);
        btnout = findViewById(R.id.btnOut_dk);
        daoKH = new DaoKhachHang(this);

        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangKyKH_Activity.this, DangNhapKH_Activity.class));
            }
        });


        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = edTaiKhoan.getText().toString();
                String pass = edMatKhau.getText().toString();
                String conf = edConf.getText().toString();
                String hoTen = edHoTen.getText().toString();
                String diaChi = edDiaChi.getText().toString();
                String sdt = edSDT.getText().toString();

                if(user.isEmpty() || pass.isEmpty() || hoTen.isEmpty() || diaChi.isEmpty() || sdt.isEmpty() || conf.isEmpty()){
                    Toast.makeText(DangKyKH_Activity.this, "Hãy nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else if(pass.length() < 8){
                    Toast.makeText(DangKyKH_Activity.this, "Mật khẩu phải lớn hơn 8 ký tự", Toast.LENGTH_SHORT).show();
                } else {
                    if(pass.equals(conf)){
                        boolean ktTK = daoKH.checkTH(user);
                        if(ktTK){
                            Toast.makeText(DangKyKH_Activity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        boolean kt = daoKH.dangKy(user, pass, hoTen, diaChi, sdt);
                        if(kt){
                            Toast.makeText(DangKyKH_Activity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DangKyKH_Activity.this, DangNhapKH_Activity.class));
                        } else {
                            Toast.makeText(DangKyKH_Activity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(DangKyKH_Activity.this, "Trường mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }
}