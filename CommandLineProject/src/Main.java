import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    private static Scanner scanner;
    private String option;

    private long start, endTime;
    private double duration;
    private ArrayList times = new ArrayList();

    private boolean typing = false;

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

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        new Main().getStats();
        new Main().menu();
    }

    private void menu(){
        start = System.nanoTime();
        System.out.print("----SAMPLE FIRST MENU----\n" +
                "\tExit\n" +
                "\tList all products\n" +
                "Press enter to write..");

        option = scanner.nextLine();

        while(option!=null) {

            if (option.isEmpty()) {
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("StartMenu: " + (duration/1000000));

                start = System.nanoTime();
                System.out.print(">");
                option = scanner.nextLine();

                switch(option){
                    case "exit" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("StartMenu - writing: " + (duration/1000000));
                        exitProgram();
                        break;
                    case "products" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("StartMenu - writing: " + (duration/1000000));
                        listProducts();
                        break;
                    default:
                        menu();
                        break;
                }
            }
            if (scanner.hasNextLine()) {
                option = scanner.nextLine();
            } else {
                option = null;
            }
        }

    }

    private void listProducts(){
        start = System.nanoTime();

        System.out.println("\n----PRODUCTS----\n" +
                "\tExit\n" + "\tReturn\n" + "\tView Cart");
        for(int i=0; i<products.length; i++){
            System.out.println("\t- "+ products[i][0]);
        }
        System.out.print("Press enter to write..");

        option = scanner.nextLine();


        while(option!=null) {

            if (option.isEmpty()) {
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("Products: " + (duration/1000000));

                start = System.nanoTime();
                System.out.print(">");
                option = scanner.nextLine();

                switch(option){
                    case "exit" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("Products - writing: " + (duration/1000000));
                        exitProgram();
                        break;
                    case "return" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("Products - writing: " + (duration/1000000));
                        menu();
                        break;
                    case "view cart" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("Products - writing: " + (duration/1000000));
                        viewCart();
                        break;
                    default:
                        for(int i=0; i<products.length; i++){
                            if(products[i][0].equals(option)) {

                                endTime = System.nanoTime();
                                duration = (endTime - start);
                                times.add("Products - writing: " + (duration/1000000));

                                seeProductDescription(option);
                                break;
                            }
                        }
                        listProducts();
                        break;
                }
            }
            if (scanner.hasNextLine()) {
                option = scanner.nextLine();
            } else {
                option = null;
            }
        }
    }

    private void seeProductDescription(String product){

        start = System.nanoTime();


        System.out.println("\n----VIEW PRODUCT----\n" +
            "\tExit\n" + "\tReturn\n" + "\tAdd to cart");
        for(int i=0; i<products.length; i++){
            if(products[i][0].equals(product)){
                System.out.println(products[i][1]);
            }
        }
        System.out.print("Press enter to write..");

        option = scanner.nextLine();

        while(option!=null) {

            if (option.isEmpty()) {
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("ProductDesc: " + (duration/1000000));

                start = System.nanoTime();
                System.out.print(">");
                option = scanner.nextLine();

                switch(option){
                    case "exit" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("ProductDesc - writing: " + (duration/1000000));
                        exitProgram();
                        break;
                    case "return" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("ProductDesc - writing: " + (duration/1000000));
                        listProducts();
                        break;
                    case "add" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("ProductDesc - writing: " + (duration/1000000));
                        System.out.println(product + " added to cart.");
                        addToCart(product);
                        listProducts();
                        break;
                    default:
                        seeProductDescription(product);
                        break;
                }
            }
            if (scanner.hasNextLine()) {
                option = scanner.nextLine();
            } else {
                option = null;
            }
        }
    }

    private void addToCart(String product){
        cart.add(product);
    }

    private void viewCart(){

        start = System.nanoTime();

        System.out.println("\n----CART----\n" +
                "\tExit\n" +
                "\tReturn\n" +
                "\tCheck out");

        for(int i=0; i < cart.size(); i++){
            System.out.println("\t- " + cart.get(i));
        }

        System.out.print("Press enter to write..");
        option = scanner.nextLine();


        while(option!=null) {

            if (option.isEmpty()) {
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("viewCart: " + (duration/1000000));

                start = System.nanoTime();
                System.out.print(">");
                option = scanner.nextLine();

                switch(option){
                    case "exit" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("viewCart - writing: " + (duration/1000000));
                        exitProgram();
                        break;
                    case "return" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("viewCart - writing: " + (duration/1000000));
                        listProducts();
                        break;
                    case "check out" :
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("viewCart - writing: " + (duration/1000000));
                        checkOut();
                        break;
                    default:
                        viewCart();
                        break;
                }
            }
            if (scanner.hasNextLine()) {
                option = scanner.nextLine();
            } else {
                option = null;
            }
        }
    }

    private void checkOut(){

        start = System.nanoTime();

        System.out.print("\tName: ");
        String name = scanner.nextLine();
        System.out.print("\tAddress: ");
        String address = scanner.nextLine();

        System.out.print("Continue?\nPress enter to write..");
        String option = scanner.nextLine();

        while(option!=null) {

            if (option.isEmpty()) {
                endTime = System.nanoTime();
                duration = (endTime - start);
                times.add("Checkout: " + (duration/1000000));

                start = System.nanoTime();
                System.out.print(">");
                option = scanner.nextLine();

                switch(option) {
                    case "exit":
                        exitProgram();
                        break;
                    case "yes":
                        endTime = System.nanoTime();
                        duration = (endTime - start);
                        times.add("Checkout - writing: " + (duration/1000000));
                        System.out.println("Thank you.\n"+ name +", " + address);
                        exitProgram();
                        break;
                    default:
                        viewCart();
                        break;
                }
            }
            if (scanner.hasNextLine()) {
                option = scanner.nextLine();
            } else {
                option = null;
            }
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

    private void exitProgram(){
        System.out.println("Goodbye!\n");

        for(int i=0; i<times.size(); i++){
            System.out.println("\t" + times.get(i));
        }

        System.exit(1);
    }

    private void checkTyping() {
        final Reader rdr = new InputStreamReader(System.in);
        final Scanner s = new Scanner(rdr);
        while (true) {

            try {
                if (rdr.ready()) {
                System.out.println(s.next());
                } else {
                    Thread.sleep(100);
                System.out.println("No input");
                }
            }catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}