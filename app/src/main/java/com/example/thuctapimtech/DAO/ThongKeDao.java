package com.example.thuctapimtech.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.thuctapimtech.Database.DbHelper;
import com.example.thuctapimtech.Model.SanPham;
import com.example.thuctapimtech.Model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDao (Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public List<Top> getTop(){
        String sqlTop = "SELECT maSP, COUNT(maSP) as soLuong FROM HoaDon GROUP BY maSP ORDER BY soLuong DESC LIMIT 10";
        List<Top>lstTop = new ArrayList<>();
        DaoSanPham daoSanPham = new DaoSanPham(context);
        Cursor c = db.rawQuery(sqlTop, null);
        while (c.moveToNext()){
            SanPham sanPham = daoSanPham.getID(c.getString(0));
            lstTop.add(new Top(sanPham.getTenSP(),
                    Integer.parseInt(c.getString(1))));
        }
        return lstTop;
    }

    public int getDoanhThu(String tuNgay, String denNgay){
        String sql = " SELECT SUM(tongTien) as doanhThu FROM HoaDon WHERE ngayDat BETWEEN ? AND ?";
        List<Integer> lstDT = new ArrayList<>();
        Cursor cursor =db.rawQuery(sql, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()){
            try {
                lstDT.add(Integer.parseInt(cursor.getString(0)));
            } catch (Exception e){
                lstDT.add(0);
            }
        }

        return lstDT.get(0);
    }
}
