import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {

    private static Scanner scanner;
    private String option;

    private String[][] products = {
            {"apple", "Apples are not native to North America. They originated in Kazakhstan, in central Asia east " +
                    "of the Caspian Sea. The capital of Kazakhstan, Alma Ata, means “full of apples.” By 1500 BC " +
                    "apple seeds had been carried throughout Europe. The Greeks, Etruscans, and Romans cultivated " +
                    "apples."},
            {"banana", "Bananas were originally found in South East Asia, mainly in India. They were brought west " +
                    "by Arab conquerors in 327 B.C. and moved from Asia Minor to Africa and finally carried to the " +
                    "New World by the first explorers and missionaries to the Caribbean."},
            {"strawberry", "The first garden strawberry was grown in Brittany, France, during the late 18th " +
                    "century. Prior to this, wild strawberries and cultivated selections from wild strawberry " +
                    "species were the common source of the fruit. The strawberry fruit was mentioned in ancient " +
                    "Roman literature in reference to its medicinal use."},
            {"orange", "The orange originated in a region encompassing Southern China, Northeast India, and Myanmar, " +
                    "and the earliest mention of the sweet orange was in Chinese literature in 314 BC. As of 1987, " +
                    "orange trees were found to be the most cultivated fruit tree in the world."},
            {"pineapple", "Pineapple is believed to have originated in the Brazilian rainforests. Pineapples were " +
                    "harvested by the native tribes and spread throughout South and Central America. When Christopher " +
                    "Columbus landed in the new world in 1493, the Spaniards named the fruit 'piña' due to its " +
                    "resemblance to a pinecone."},
            {"kiwi", "The original fruit is from the Far East, having been grown in what is now modern-day China " +
                    "for many centuries. It was only at the turn of the 20th Century, in 1904, that it arrived on " +
                    "New Zealand shores, when New Zealand school principal Isabel Fraser brought some kiwifruit " +
                    "seeds back from her travels."},
            {"grape", "Archeological evidence suggests humans began growing grapes as early as 6500 B.C. during the " +
                    "Neolithic era. By 4000 B.C., grape growing extended from Transcaucasia to Asia Minor and " +
                    "through the Nile Delta of Egypt."},
            {"blackberry", "The blackberry is an edible fruit produced by many species in the genus Rubus in the " +
                    "family Rosaceae, hybrids among these species within the subgenus Rubus, and hybrids between " +
                    "the subgenera Rubus and Idaeobatus. The taxonomy of blackberries has historically been " +
                    "confused because of hybridization and apomixis, so that species have often been grouped " +
                    "together and called species aggregates."},
            {"mango", "Mangoes originated in India over 4,000 years ago and are considered a sacred fruit. " +
                    "Mangoes spread gradually throughout Asia and then to the rest of the world. Due to a mangos " +
                    "large center seed, the fruit relied on humans to transport them across the world."},
            {"peach", "The peach probably originated in China and then spread westward through Asia to the " +
                    "Mediterranean countries and later to other parts of Europe. The Spanish explorers took the " +
                    "peach to the New World, and as early as 1600 the fruit was found in Mexico."}
    };

    private ArrayList<String> cart = new ArrayList<String>();

    public static void main(String[] args){
        scanner = new Scanner(System.in);
        new Main().getStats();
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
            System.out.println("\t- "+ products[i][0]);
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
            if(products[i][0].equals(product)){
                System.out.println(products[i][1]);
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