package com.gorvic.context;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList;


    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    public Product getProductById(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                return p;
            }
        }
        System.out.println("Product not found");
        return null;
    }

    @PostConstruct
    private void initialize() {
        productList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            productList.add(new Product(i, "product_" + i, (float) (i * 0.3f * Math.random() * 10)));
        }
    }
}
