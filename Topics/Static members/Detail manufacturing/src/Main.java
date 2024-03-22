import java.util.Scanner;

class ManufacturingController {
    public static int numberOfProducts = 0;

    public static String requestProduct(String product) {
        numberOfProducts++;
        return String.format("%d. Requested %s", numberOfProducts, product);
    }

    public static int getNumberOfProducts() {
        return numberOfProducts;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String product = scanner.nextLine();
            System.out.println(ManufacturingController.requestProduct(product));
            System.out.println(ManufacturingController.getNumberOfProducts());
        }
    }
}