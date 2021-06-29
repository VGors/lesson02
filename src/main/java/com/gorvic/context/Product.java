package com.gorvic.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


public class Product {
    private int id;
    private String name;
    private float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ": " + name + " " + price;
    }
}
