package com.example.thuctapimtech.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.thuctapimtech.Database.DbHelper;
import com.example.thuctapimtech.Model.GioHang;

import java.util.ArrayList;
import java.util.List;

public class DaoGioHang {
    private SQLiteDatabase db;
    private Context context;
    DbHelper dbHelper;

    public DaoGioHang(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(GioHang obj) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("maSP",obj.getMaSP());
        contentValues.put("tenSP",obj.getTenSP());
        contentValues.put("tenHang",obj.getHangSP());
        contentValues.put("giaTien",obj.getGia());
        contentValues.put("soLuong",obj.getSoluong());


        return db.insert("GioHang",null,contentValues);
    }

    public int delete(String id) {
        return db.delete("GioHang","maSP = ?",new String[]{String.valueOf(id)});
    }

    private List<GioHang> getData(String sql, String ... selectionArgs) {
        List<GioHang> lstGioHang = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()) {
            lstGioHang.add(new GioHang(
                    Integer.parseInt(cursor.getString(0)),
                    Integer.parseInt(cursor.getString(1)),
                    cursor.getString(2),
                    cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)),
                    Integer.parseInt(cursor.getString(5)),
                    cursor.getString(6)
            ));
        }
        return lstGioHang;
    }

    public GioHang getID (String id) {
        String sql = "SELECT * FROM GioHang WHERE maSP = ?";
        List<GioHang> lsGioHang = getData(sql,id);
        return lsGioHang.get(0);
    }

    public List<GioHang> getAll() {
        String sql = "SELECT * FROM GioHang";
        return getData(sql);
    }

public boolean insertToCart(GioHang gh){
    // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase database = dbHelper.getWritableDatabase();
    // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
    Cursor cursor = database.rawQuery("SELECT * FROM GioHang WHERE maSP = ?",new String[]{String.valueOf(gh.getMaSP())});
    long check;

    if(cursor.getCount() > 0){
        // Nếu sản phẩm đã tồn tại, số lượng trong giỏ hàng tăng thêm 1
        cursor.moveToFirst();
        int currentQuantity = cursor.getInt(5);
        currentQuantity +=1;

        ContentValues values = new ContentValues();
        values.put("soLuong", currentQuantity);

        check = database.update("GioHang", values, "maSP = ?", new String[]{String.valueOf(gh.getMaSP())});
    } else {
        // Nếu sản phẩm chưa tồn tại, thêm mới vào giỏ hàng
        int quantity = 1;
        ContentValues values = new ContentValues();
        values.put("maSP", gh.getMaSP());
        values.put("tenSP", gh.getTenSP());
        values.put("hangSP", gh.getHangSP());
        values.put("giaSP", gh.getGia());
        values.put("soLuong", gh.getSoluong());
        check = database.insert("GioHang", null , values);
    }

    if (check == -1) {
            return false;
        }
        return true;
    }
}


//    public boolean insertToCart(GioHangActivity gh, String email) {
//        // Mở cơ sở dữ liệu để ghi
//        SQLiteDatabase database = dbHelper.getWritableDatabase();
//        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
//        Cursor cursor = database.rawQuery("SELECT * FROM " + DBHelper.TABLE_CART + " WHERE name = ? AND emailCus = ?", new String[]{product.getName(), email});
//        long check;
//
//        if (cursor.getCount() > 0) {
//            // Nếu sản phẩm đã tồn tại, số lượng trong giỏ hàng tăng thêm 1
//            cursor.moveToFirst();
//            int currentQuantity = cursor.getInt(3);
//            currentQuantity += 1;
//
//            ContentValues values = new ContentValues();
//            values.put("quantity", currentQuantity);
//            check = database.update(DBHelper.TABLE_CART, values, "name = ? AND emailCus = ?", new String[]{product.getName(), email});
//
//        } else {
//            // Nếu sản phẩm chưa tồn tại, thêm mới vào giỏ hàng
//            int quantity = 1;
//            ContentValues values = new ContentValues();
//            values.put("idProduct", product.getId());
//            values.put("name", product.getName());
//            values.put("quantity", quantity);
//            values.put("price", product.getPrice());
//            values.put("emailCus", email);
//            check = database.insert(DBHelper.TABLE_CART, null , values);
//        }
//        if (check == -1) {
//            return false;
//        }
//        return true;
//    }

