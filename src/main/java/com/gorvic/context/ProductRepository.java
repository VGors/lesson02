package com.gorvic.context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class ProductRepository {
    private List<Product> productList;


    public void getProductList() {
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    public void getProductById(int id) {
        for (Product p : productList) {
            if (p.getId() == id) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("Product id = " + id + " not found");
    }

    @PostConstruct
    public void initialize() {
        productList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            productList.add(new Product(i, "product " + i, (float) (i * 0.3f * Math.random() * 10)));
        }
    }
}
