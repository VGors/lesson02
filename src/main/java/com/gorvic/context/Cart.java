package com.gorvic.context;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {
    private List<Product> products;

    public Boolean addProduct(Product product){
        if (product != null) {
            try {
                products.add(product);
                return true;
            } catch (Exception e) {
                System.err.println("Error");
                return false;
            }
        }
        return false;
    }

    public float getFullPrice(){
        float summ = 0;
        for (Product p :products) {
            summ += p.getPrice();
        }
        return summ;
    }

    public Boolean removeProduct (Product product){
        int targetId = product.getId();
        for (Product p: products) {
            if (p.getId() == targetId) {
                products.remove(p);
                return true;
            }
        }
        return false;
    }

    public void printProducts(){
        for (Product p: products) {
            System.out.println(p);
        }
    }

    @PostConstruct
    private void initialization(){
        products = new ArrayList<>();
    }
}
