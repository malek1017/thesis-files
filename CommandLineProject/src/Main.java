import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private String option;

    private String[][] products = {
            {"apple", "pear"},
            {"This is an apple", "This is a pear"}
    };

    private ArrayList<String> cart = new ArrayList<String>();

    public static void main(String[] args){
        scanner = new Scanner(System.in);
        new Main().menu();
    }

    private void menu(){
        System.out.print("----SAMPLE FIRST MENU----\n" +
                "\tExit\n" +
                "\tList all products\n" +
                ">");

        option = scanner.nextLine();

        switch(option){
            case "exit" :
                System.out.println("Goodbye.");
                System.exit(1);
                break;
            case "2" :
                listProducts();
                break;
            default:
                menu();
                break;
        }
    }

    private void listProducts(){
        System.out.println("----PRODUCTS----\n" +
                "\tExit\n" + "\tReturn\n" + "\tView Cart");
        for(int i=0; i<products.length; i++){
            System.out.println("\t- "+ products[0][i]);
        }
        System.out.print(">");

        option = scanner.nextLine();

        switch(option){
            case "exit" :
                System.out.println("Goodbye.");
                System.exit(1);
                break;
            case "return" :
                menu();
                break;
            case "cart" :
                viewCart();
                break;
            default:
                seeProductDescription(option);
                break;
        }
    }

    private void seeProductDescription(String product){
        System.out.println("----VIEW PRODUCT----\n" +
            "\tExit\n" + "\tReturn\n" + "\tAdd to cart");
        for(int i=0; i<products.length; i++){
            if(products[0][i].equals(product)){
                System.out.println(products[1][i]);
            }
        }
        System.out.print(">");

        option = scanner.nextLine();

        switch(option){
            case "exit" :
                System.out.println("Goodbye.");
                System.exit(1);
                break;
            case "return" :
                listProducts();
                break;
            case "add" :
                System.out.println(product + " added to cart.");
                addToCart(product);
                listProducts();
                break;
            default:
                seeProductDescription(product);
                break;
        }
    }

    private void addToCart(String product){
        cart.add(product);
    }

    private void viewCart(){
        System.out.println("----CART----\n" +
                "\tExit\n" +
                "\tReturn\n" +
                "\tCheck out");

        for(int i=0; i < cart.size(); i++){
            System.out.println("\t- " + cart.get(i));
        }

        System.out.print(">");
        option = scanner.nextLine();


        switch(option){
            case "exit" :
                System.out.println("Goodbye.");
                System.exit(1);
                break;
            case "return" :
                listProducts();
                break;
            case "check out" :
                checkOut();
                break;
            default:
                viewCart();
                break;
        }
    }
    
    private void checkOut(){
        System.out.print("\tName: ");
        String name = scanner.nextLine();
        System.out.print("\tAddress: ");
        String address = scanner.nextLine();

        System.out.print("Continue?\n>");
        String option = scanner.nextLine();
        switch(option) {
            case "exit":
                System.out.println("Goodbye.");
                System.exit(1);
                break;
            case "yes":
                System.out.println("Thank you.\n"+ name +", " + address);
                break;
            default:
                viewCart();
                break;
        }
    }
}