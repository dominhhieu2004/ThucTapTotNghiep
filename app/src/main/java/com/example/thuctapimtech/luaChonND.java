package com.example.thuctapimtech;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thuctapimtech.Activity.DangNhapKH_Activity;
import com.example.thuctapimtech.Activity.DangNhap_Activity;


public class luaChonND extends AppCompatActivity {

    Button btnAdmin, btnKH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lua_chon_nd);

        btnAdmin = findViewById(R.id.admin);
        btnKH  = findViewById(R.id.kh);

//        btnKH.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(luaChonND.this, DangNhap_KH.class);
//                startActivity(i);
//            }
//        });

        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(luaChonND.this, DangNhap_Activity.class);
                startActivity(i);
            }
        });

        btnKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(luaChonND.this, DangNhapKH_Activity.class));
            }
        });
    }
}