package com.example.thuctapimtech.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.thuctapimtech.Database.DbHelper;
import com.example.thuctapimtech.Model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class DaoKhachHang {
//cdsc
    SQLiteDatabase db;
    DbHelper dbHelper;
    public DaoKhachHang(Context context){
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }


    public boolean dangNhap(String username, String pass){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor =db.rawQuery("SELECT * FROM KhachHang WHERE maKH=? AND matKhau =?", new String[]{username, pass});
        return cursor.getCount() > 0;
    }

    public boolean dangKy(String username, String pass, String hoTen, String diaChi, String sdt){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("maKH", username);
        values.put("matKhau", pass);
        values.put("hoTen", hoTen);
        values.put("diaChi", diaChi);
        values.put("dienThoai", sdt);

        long row =db.insert("KhachHang", null, values);
        return (row > 0);
    }

    public int checkLoginKH(String user, String pass){
        String sql = "SELECT * FROM KhachHang WHERE maKH =? AND matKhau =?";
        List<KhachHang> list  = getData(sql, user, pass);
        if(list.size() == 0){
            return -1;
        }
        return 1;
    }

    public long updatePass(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.getHoTen());
        values.put("matKhau", obj.getMatKhau());
        return db.update("KhachHang", values, "maKH = ?", new String[]{String.valueOf(obj.getMaKH())});
    }

    public long delete(String id) {
        return db.delete("KhachHang", "maKH = ?", new String[]{String.valueOf(id)});
    }

    public List<KhachHang> getAll() {
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM KhachHang WHERE maKH=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }

    private List<KhachHang> getData(String sql, String... selectionArgs){
        List<KhachHang> listKH = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()){
            listKH.add(new KhachHang(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            ));
        }
        return listKH;
    }

}
