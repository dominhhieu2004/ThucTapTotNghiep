package com.example.thuctapimtech.Model;

public class Top10SanPham {
    private String tenSP;
    private int soLuong;

    public Top10SanPham() {
    }

    public Top10SanPham(String tenSP, int soLuong) {
        this.tenSP = tenSP;
        this.soLuong = soLuong;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}

