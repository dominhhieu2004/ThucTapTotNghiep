package com.example.thuctapimtech.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thuctapimtech.DAO.DaoKhachHang;
import com.example.thuctapimtech.R;
import com.example.thuctapimtech.TrangChuKH;
import com.example.thuctapimtech.luaChonND;
import com.google.android.material.textfield.TextInputEditText;

public class DangNhapKH_Activity extends AppCompatActivity {

    Button btnDN, btnDK, btnOut;
    CheckBox chkRe;
    TextInputEditText edtTK, edMK;

    DaoKhachHang daoKhachHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dang_nhap_kh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

       btnDK = findViewById(R.id.btn_dk_kh);
       btnOut = findViewById(R.id.btn_out_kh);
       btnDN = findViewById(R.id.btn_login_kh);
       chkRe = findViewById(R.id.chk_rememberAccount_kh);
       edtTK = findViewById(R.id.txt_username_kh);
       edMK = findViewById(R.id.txt_password_kh);

       daoKhachHang = new DaoKhachHang(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user =pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);

        edtTK.setText(user);
        edMK.setText(pass);
        chkRe.setChecked(rem);

        btnDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtTK.getText().toString().trim();
                String pass = edMK.getText().toString().trim();
                boolean kt = daoKhachHang.dangNhap(user, pass);

                if(kt){
                    Toast.makeText(DangNhapKH_Activity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    rememberLogin(user, pass, chkRe.isChecked());
                    startActivity(new Intent(DangNhapKH_Activity.this, TrangChuKH.class));
                } else {
                    Toast.makeText(DangNhapKH_Activity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
       btnOut.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(DangNhapKH_Activity.this, luaChonND.class));
           }
       });

       btnDK.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(DangNhapKH_Activity.this, DangKyKH_Activity.class));
           }
       });

    }

    public void rememberLogin(String u, String p, boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            // xoa trang thai luu truoc do
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        //lưu lại
        edit.commit();
    }
}