package com.gorvic.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;

public class Starter {
    private static Cart cart;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepository = applicationContext.getBean(ProductRepository.class);
        cart = applicationContext.getBean(Cart.class);

        try (BufferedReader commandReader = new BufferedReader(new InputStreamReader(System.in))) {
            ProductRepository products = applicationContext.getBean(ProductRepository.class);
            while (true) {
                printCommands();
                String[] commandString = new String[2];
                int commandId = 0;
                try {
                    commandString = commandReader.readLine().trim().split("\\s+");
                    commandId = Integer.parseInt(commandString[0]);
                } catch (NumberFormatException e) {
                    System.err.println("Wrong command");
                }
                switch (commandId) {
                    //Show product by ID from ProductRepository
                    case 1:
                        System.out.println(products.getProductById(Integer.parseInt(commandString[1])));
                        break;
                    //Show all products from ProductRepository
                    case 2:
                        for (Product p : products.getProductList()) {
                            System.out.println(p);
                        }
                        break;
                    //Adding product to the cart by ID
                    case 3:
                        cart.addProduct(products.getProductById(Integer.parseInt(commandString[1])));
                        break;
                    //Removing product from the cart by ID
                    case 4:
                        cart.removeProduct(products.getProductById(Integer.parseInt(commandString[1])));
                        break;

                    //Print all products from the cart
                    case 5:
                        cart.printProducts();
                        break;
                    //Print cart's price
                    case 6:
                        System.out.println(cart.getFullPrice());
                        break;
                    //Exit
                    case 7:
                        return;
                    default:
                        System.out.println("Wrong number");
                }
            }
        } catch (IOException e) {
            System.err.println("Error " + e);
        }
        Cart cart = applicationContext.getBean(Cart.class);
        commandsTracer(applicationContext);
    }

    private static void commandsTracer(ApplicationContext applicationContext) {

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
