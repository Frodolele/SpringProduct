package com.frodolele.springproduct.entities;

import javax.persistence.*;

//
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="brand")
    private String brand;

    @Column(name="price")
    private Integer price;

    @Column(name="quantity")
    private Integer quantity;

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(){

    }


}
