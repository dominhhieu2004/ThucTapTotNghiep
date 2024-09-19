package com.example.thuctapimtech.Model;

public class KhachHang {

    private String maKH;
    private String hoTen;
    private String dienThoai;
    private String diaChi;

    private String matKhau;

    public KhachHang() {
    }

    public KhachHang(String maKH, String hoTen, String dienThoai, String diaChi, String matKhau) {
        this.maKH = maKH;
        this.hoTen = hoTen;
        this.dienThoai = dienThoai;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
