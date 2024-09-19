package com.example.thuctapimtech.Model;

public class ChiTietHoaDon {
    private int idCTHD;
    private int idHoaDon;
    private int maSP;
    private int soLuong, giaTien;

    private String note;

    public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(int idCTHD, int idHoaDon, int maSP, int soLuong, int giaTien, String note) {
        this.idCTHD = idCTHD;
        this.idHoaDon = idHoaDon;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIdCTHD() {
        return idCTHD;
    }

    public void setIdCTHD(int idCTHD) {
        this.idCTHD = idCTHD;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
