package com.example.thuctapimtech.Model;

public class CTSanPham {
    private String maCTSP;
    private String maSP;
    private String tenCTSP;
    private String hangCTSP;

    private String moTa;
    private int giaTien;

    public CTSanPham() {
    }

    public CTSanPham(String maCTSP, String maSP, String tenCTSP, String hangCTSP,  int giaTien ,String moTa) {
        this.maCTSP = maCTSP;
        this.maSP = maSP;
        this.tenCTSP = tenCTSP;
        this.hangCTSP = hangCTSP;
        this.moTa = moTa;
        this.giaTien = giaTien;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(String maCTSP) {
        this.maCTSP = maCTSP;
    }

    public String getTenCTSP() {
        return tenCTSP;
    }

    public void setTenCTSP(String tenCTSP) {
        this.tenCTSP = tenCTSP;
    }

    public String getHangCTSP() {
        return hangCTSP;
    }

    public void setHangCTSP(String hangCTSP) {
        this.hangCTSP = hangCTSP;
    }

    public int getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(int giaTien) {
        this.giaTien = giaTien;
    }
}
