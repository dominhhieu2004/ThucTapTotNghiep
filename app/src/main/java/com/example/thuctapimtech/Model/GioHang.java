package com.example.thuctapimtech.Model;

public class GioHang {

    private int maGioHang;

    private int maSP;
    private String tenSP, hangSP;
    private int gia, soluong;

    private String imagesGH;

    boolean isCheck;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public GioHang() {
    }

    public GioHang(int maGioHang, int maSP, String tenSP, String hangSP, int gia, int soluong, String imagesGH) {
        this.maGioHang = maGioHang;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.hangSP = hangSP;
        this.gia = gia;
        this.soluong = soluong;
        this.imagesGH = imagesGH;
    }

    public int getMaGioHang() {
        return maGioHang;
    }

    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }

    public String getImagesGH() {
        return imagesGH;
    }

    public void setImagesGH(String imagesGH) {
        this.imagesGH = imagesGH;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHangSP() {
        return hangSP;
    }

    public void setHangSP(String hangSP) {
        this.hangSP = hangSP;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
