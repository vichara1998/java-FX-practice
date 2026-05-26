package com.example.demod14.dto;

public class OrderDetailDto {
    private int id;
    private String brand;
    private String model;
    private double price;
    private int qnt;
    private double total;

    public OrderDetailDto(int id, String brand, String model, double price, int qnt, double total) {
        this.setModel(model);
        this.setId(id);
        this.setBrand(brand);
        this.setPrice(price);
        this.setQnt(qnt);
        this.setTotal(total);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
