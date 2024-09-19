package com.example.thuctapimtech.demo;




import com.example.thuctapimtech.Model.GioHang;

import java.util.ArrayList;
import java.util.List;

public class Cart {


    private List<GioHang> productList;

    public Cart() {
        productList = new ArrayList<>();
    }

    public void addToCart(GioHang product) {
        productList.add(product);
    }

    public List<GioHang> getProductList() {
        return productList;
    }

    public void clearCart() {
        productList.clear();
    }

//    public double getTotalPrice() {
//        double totalPrice = 0;
//        for (GioHangActivity product : productList) {
//            totalPrice += product.get();
//        }
//        return totalPrice;
//    }
}
