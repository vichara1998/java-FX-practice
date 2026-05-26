package com.example.demod14.dto;

public class PhoneDto {
    private int pid;
    private String brand;
    private String model;
    private double price;
    private int qnt;

    public PhoneDto() {
        this.pid = pid;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.qnt = qnt;
    }

    public PhoneDto(String brand, String model, double price, int qnt) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.qnt = qnt;
    }public PhoneDto(int pid,String brand, String model, double price, int qnt) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.qnt = qnt;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }
}
