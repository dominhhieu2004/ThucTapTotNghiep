package com.example.thuctapimtech.Model;

public class Item {
    int idsp;
    String tensp;

    public Item() {
    }

    public Item(int idsp, String tensp) {
        this.idsp = idsp;
        this.tensp = tensp;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }
}
