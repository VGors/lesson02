package com.gorvic.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;

public class Starter {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        try (BufferedReader commandReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                printCommands();
                int commandId = Integer.parseInt(commandReader.readLine());
                if (commandId == 3) {
                    break;
                }
                ProductRepository products = applicationContext.getBean(ProductRepository.class);
                if (commandId == 1) {
                    System.out.println("please input product ID:");
                    int productId = Integer.parseInt(commandReader.readLine());
                    products.getProductById(productId);
                }
                if (commandId == 2) {
                    System.out.println("2");

                    products.getProductList();
                }
            }
        } catch (IOException e) {
            System.err.println("Error " + e);
        }
    }

    private static void printCommands() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("commands.txt"))) {
            while (fileReader.ready()) {
                System.out.println(fileReader.readLine());
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
