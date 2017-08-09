package com.example.penny.accessibilityyysp.model;

/**
 * Created on 2017/8/8 0008.
 * by penny
 */

public class ELMModel {

    private String address;
    private String phone;
    private String storeName;
    private String storeInfomation;
    private String brand;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String pBrand) {
        brand = pBrand;
    }

    public String getStoreInfomation() {
        return storeInfomation;
    }

    public void setStoreInfomation(String pStoreInfomation) {
        storeInfomation = pStoreInfomation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String pAddress) {
        address = pAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String pPhone) {
        phone = pPhone;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String pStoreName) {
        storeName = pStoreName;
    }

    @Override
    public String toString() {
        return "ELMModel{" +
                "address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeInfomation='" + storeInfomation + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
