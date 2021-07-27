package ru.haazad.onlinestorage.lesson2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.haazad.onlinestorage.lesson2.config.AppConfig;
import ru.haazad.onlinestorage.lesson2.service.CartService;
import ru.haazad.onlinestorage.lesson2.service.ProductRepositoryService;

import java.util.Scanner;


public class MainApp {
    private static CartService cartService;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner scanner = new Scanner(System.in);

        runApp(scanner, context);

        scanner.close();
    }

    private static void runApp(Scanner scanner, AnnotationConfigApplicationContext context) {
        ProductRepositoryService productRepository = context.getBean(ProductRepositoryService.class);
        printMenu();
        label:
        while (true) {
            System.out.print("Enter the command: ");
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "1":
                    System.out.println(productRepository.getAllProducts());
                    break;
                case "2":
                    cartService = context.getBean(CartService.class);
                    System.out.println("Success create new cart");
                    break;
                case "3":
                    if (cartService != null) {
                        System.out.println(cartService);
                        break;
                    }
                    System.out.println("Cart is not created");
                    break;
                case "4":
                    System.out.print("Please enter productId to add in cart: ");
                    long productId = Long.parseLong(scanner.nextLine());
                    if (cartService != null) {
                        cartService.addProduct(productId);
                        break;
                    }
                    System.out.println("Cart is not created");
                    break;
                case "5":
                    System.out.print("Please enter productId to remove from cart: ");
                    long prodId = Long.parseLong(scanner.nextLine());
                    if (!cartService.isEmpty()) {
                        System.out.println(cartService.removeProduct(prodId));
                        break;
                    }
                    System.out.println("Sorry the cart is empty");
                    break;
                case "9":
                    printMenu();
                    break;
                case "0":
                    break label;
            }
        }
    }

    private static void printMenu() {
        String sb = "Current system command: " +
                System.lineSeparator() +
                "1 - print list product" + System.lineSeparator() +
                "2 - create new cart" + System.lineSeparator() +
                "3 - get cart" + System.lineSeparator() +
                "4 - add product in cart" + System.lineSeparator() +
                "5 - remove product from cart" + System.lineSeparator() +
                "9 - print menu" + System.lineSeparator() +
                "0 - exit";
        System.out.println(sb);
    }

}
