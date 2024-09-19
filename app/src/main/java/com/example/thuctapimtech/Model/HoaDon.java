package com.example.thuctapimtech.Model;

public class HoaDon {

    private int idDonHang;
    private String maKH;

    private int maSP;
    private int tongTien;
    private String ngayDat;


    public HoaDon() {
    }

    public HoaDon(int idDonHang, String maKH, int maSP, int tongTien, String ngayDat) {
        this.idDonHang = idDonHang;
        this.maKH = maKH;
        this.maSP = maSP;
        this.tongTien = tongTien;
        this.ngayDat = ngayDat;
    }

    public int getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }
}
