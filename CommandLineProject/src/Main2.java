import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main2 {

    private static Scanner scanner;
    private String option;

    private long start, endTime;
    private double duration;
    private ArrayList times = new ArrayList();

    private String[][] products = {
            {"apple", "Apples are not native to North America. They originated in Kazakhstan, in central Asia east\n" +
                    "of the Caspian Sea. The capital of Kazakhstan, Alma Ata, means “full of apples.” By 1500 BC\n" +
                    "apple seeds had been carried throughout Europe. The Greeks, Etruscans, and Romans cultivated\n" +
                    "apples."},
            {"banana", "Bananas were originally found in South East Asia, mainly in India. They were brought west\n" +
                    "by Arab conquerors in 327 B.C. and moved from Asia Minor to Africa and finally carried to the\n" +
                    "New World by the first explorers and missionaries to the Caribbean."},
            {"strawberry", "The first garden strawberry was grown in Brittany, France, during the late 18th\n" +
                    "century. Prior to this, wild strawberries and cultivated selections from wild strawberry\n" +
                    "species were the common source of the fruit. The strawberry fruit was mentioned in ancient\n" +
                    "Roman literature in reference to its medicinal use."},
            {"orange", "The orange originated in a region encompassing Southern China, Northeast India, and Myanmar,\n" +
                    "and the earliest mention of the sweet orange was in Chinese literature in 314 BC. As of 1987,\n" +
                    "orange trees were found to be the most cultivated fruit tree in the world."},
            {"pineapple", "Pineapple is believed to have originated in the Brazilian rainforests. Pineapples were\n" +
                    "harvested by the native tribes and spread throughout South and Central America. When Christopher\n" +
                    "Columbus landed in the new world in 1493, the Spaniards named the fruit 'piña' due to its\n" +
                    "resemblance to a pinecone."},
            {"kiwi", "The original fruit is from the Far East, having been grown in what is now modern-day China\n" +
                    "for many centuries. It was only at the turn of the 20th Century, in 1904, that it arrived on\n" +
                    "New Zealand shores, when New Zealand school principal Isabel Fraser brought some kiwifruit\n" +
                    "seeds back from her travels."},
            {"grape", "Archeological evidence suggests humans began growing grapes as early as 6500 B.C. during the\n" +
                    "Neolithic era. By 4000 B.C., grape growing extended from Transcaucasia to Asia Minor and\n" +
                    "through the Nile Delta of Egypt."},
            {"blackberry", "The blackberry is an edible fruit produced by many species in the genus Rubus in the\n" +
                    "family Rosaceae, hybrids among these species within the subgenus Rubus, and hybrids between\n" +
                    "the subgenera Rubus and Idaeobatus. The taxonomy of blackberries has historically been\n" +
                    "confused because of hybridization and apomixis, so that species have often been grouped\n" +
                    "together and called species aggregates."},
            {"mango", "Mangoes originated in India over 4,000 years ago and are considered a sacred fruit.\n" +
                    "Mangoes spread gradually throughout Asia and then to the rest of the world. Due to a mangos\n" +
                    "large center seed, the fruit relied on humans to transport them across the world."},
            {"peach", "The peach probably originated in China and then spread westward through Asia to the\n" +
                    "Mediterranean countries and later to other parts of Europe. The Spanish explorers took the\n" +
                    "peach to the New World, and as early as 1600 the fruit was found in Mexico."}
    };

    private ArrayList<String> cart = new ArrayList<String>();

    public static void main(String[] args){
        scanner = new Scanner(System.in);
        new Main2().menu();
    }

    private void menu(){
        start = System.nanoTime();

        System.out.print("----SAMPLE FIRST MENU----\n" +
                "\t1) Exit\n" +
                "\t2) List all products\n" +
                ">");

        option = scanner.nextLine();

        switch(option){
            case "1" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("StartMenu: " + (duration/1000000));
                exitProgram();
                break;
            case "2" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("StartMenu: " + (duration/1000000));
                listProducts();
                break;
            default:
                menu();
                break;
        }
    }

    private void listProducts(){
        start = System.nanoTime();

        System.out.println("\n----PRODUCTS----\n" +
                "\t1) Exit\n" + "\t2) Return\n" + "\t3) View Cart");
        for(int i=0; i<products.length; i++){
            System.out.println("\t"+ (i+4) + ") " + products[i][0]);
        }
        System.out.print(">");

        option = scanner.nextLine();

        switch(option){
            case "1" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("Products: " + (duration/1000000));
                exitProgram();
                break;
            case "2" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("Products: " + (duration/1000000));
                menu();
                break;
            case "3" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("Products: " + (duration/1000000));
                viewCart();
                break;
            default:
                int temp = ((Integer.valueOf(option))-4);
                String temp2 = products[temp][0];

                for(int i=0; i<products.length; i++){
                    if(products[i][0].equals(temp2)) {

                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("Products: " + (duration/1000000));

                        seeProductDescription(temp2);
                        break;
                    }
                }
                listProducts();
                break;
        }
    }

    private void seeProductDescription(String product){

        start = System.nanoTime();


        System.out.println("\n----VIEW PRODUCT----\n" +
                "\t1) Exit\n" + "\t2) Return\n" + "\t3) Add to cart");
        for(int i=0; i<products.length; i++){
            if(products[i][0].equals(product)){
                System.out.println(products[i][1]);
            }
        }
        System.out.print(">");

        option = scanner.nextLine();

        switch(option){
            case "1" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("ProductDesc: " + (duration/1000000));
                exitProgram();
                break;
            case "2" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("ProductDesc: " + (duration/1000000));
                listProducts();
                break;
            case "3" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("ProductDesc: " + (duration/1000000));
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

        start = System.nanoTime();

        System.out.println("\n----CART----\n" +
                "\t1) Exit\n" +
                "\t2) Return\n" +
                "\t3) Check out");

        for(int i=0; i < cart.size(); i++){
            System.out.println("\t- " + cart.get(i));
        }

        System.out.print(">");
        option = scanner.nextLine();


        switch(option){
            case "1" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("viewCart: " + (duration/1000000));
                exitProgram();
                break;
            case "2" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("viewCart: " + (duration/1000000));
                listProducts();
                break;
            case "3" :
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("viewCart: " + (duration/1000000));
                checkOut();
                break;
            default:
                viewCart();
                break;
        }
    }

    private void checkOut(){

        start = System.nanoTime();

        System.out.print("\tName: ");
        String name = scanner.nextLine();
        System.out.print("\tAddress: ");
        String address = scanner.nextLine();

        System.out.print("Continue?\n\t1) Exit\n\t2) Yes\n\t3) No\n>");
        String option = scanner.nextLine();
        switch(option) {
            case "1":
                exitProgram();
                break;
            case "2":
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("Checkout: " + (duration/1000000));
                System.out.println("Thank you.\n"+ name +", " + address);
                exitProgram();
                break;
            default:
                viewCart();
                break;
        }
    }


    private void exitProgram(){
        System.out.println("Goodbye!\n");

        for(int i=0; i<times.size(); i++){
            System.out.println("\t" + times.get(i));
        }

        System.exit(1);
    }
}