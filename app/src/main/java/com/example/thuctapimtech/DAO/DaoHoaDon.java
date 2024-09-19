package com.example.thuctapimtech.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.thuctapimtech.Database.DbHelper;
import com.example.thuctapimtech.Model.ChiTietHoaDon;
import com.example.thuctapimtech.Model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class DaoHoaDon {
    private DbHelper dbHelper;
    ChiTietHoaDon cthdlDAO;
    public DaoHoaDon(Context context){
        this.dbHelper = new DbHelper(context);
        cthdlDAO = new ChiTietHoaDon();
    }

     public long insertHoaDon(HoaDon obj){
          SQLiteDatabase db = dbHelper.getWritableDatabase();
         ContentValues values = new ContentValues();

         values.put("maKH", obj.getMaKH());
         values.put("maSP", obj.getMaSP());
         values.put("tongTien", obj.getTongTien());
         values.put("ngayDat", obj.getNgayDat());

         return db.insert("HoaDon",null,values);
     }

     public List<HoaDon> getAll(){
        String sql ="SELECT * FROM HoaDon";
        return getData(sql);
     }

    private List<HoaDon> getData(String sql, String... selectionArgs){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<HoaDon> list =  new ArrayList<>();

        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            list.add(new HoaDon(
                    c.getInt(0),
                    c.getString(1),
                    c.getInt(2),
                    c.getInt(3),
                    c.getString(4)
            ));
        }
        if (c != null){
            c.close();
        }
        if (db != null){
            db.close();
        }
        return list;
    }
}
