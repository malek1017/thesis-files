import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {

    private static Scanner scanner;
    private String option;

    private String[][] products = {
            {"apple", "strawberry", "banana", ""},
            {"This is an apple", "This is a pear"}
    };

    private ArrayList<String> cart = new ArrayList<String>();

    public static void main(String[] args){
        scanner = new Scanner(System.in);
        new Main2().getStats();
        new Main2().menu();
    }

    private void menu(){
        System.out.print("----SAMPLE FIRST MENU----\n" +
                "\tExit\n" +
                "\tList all products\n" +
                ">");

        option = scanner.nextLine();

        switch(option){
            case "1" :
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
            System.out.println("\t"+ (i+3) + " " +products[0][i]);
        }
        System.out.print(">");

        option = scanner.nextLine();

        switch(option){
            case "1" :
                System.out.println("Goodbye.");
                System.exit(1);
                break;
            case "2" :
                menu();
                break;
            case "3" :
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
            case "1" :
                System.out.println("Goodbye.");
                System.exit(1);
                break;
            case "2" :
                listProducts();
                break;
            case "3" :
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

    private void getStats() {

        /* Total number of processors or cores available to the JVM */
        System.out.println("Available processors (cores): " +
                Runtime.getRuntime().availableProcessors());

        /* Total amount of free memory available to the JVM */
        System.out.println("Free memory (bytes): " +
                Runtime.getRuntime().freeMemory());

        /* This will return Long.MAX_VALUE if there is no preset limit */
        long maxMemory = Runtime.getRuntime().maxMemory();
        /* Maximum amount of memory the JVM will attempt to use */
        System.out.println("Maximum memory (bytes): " +
                (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));

        /* Total memory currently available to the JVM */
        System.out.println("Total memory available to JVM (bytes): " +
                Runtime.getRuntime().totalMemory());

        /* Get a list of all filesystem roots on this system */
        File[] roots = File.listRoots();

        /* For each filesystem root, print some info */
        for (File root : roots) {
            System.out.println("File system root: " + root.getAbsolutePath());
            System.out.println("Total space (bytes): " + root.getTotalSpace());
            System.out.println("Free space (bytes): " + root.getFreeSpace());
            System.out.println("Usable space (bytes): " + root.getUsableSpace());
        }

    }
}