import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cart cart = new Cart();
        Scanner scanner = new Scanner(System.in);
        String[] inputStrArr = scanner.nextLine().split(" ");
        for (String s : inputStrArr) {
            int price = Integer.parseInt(s);
            cart.addToCart(price);
        }

        System.out.println(cart.total);
    }

    public static class Cart {
        private int total;

        public Cart() {
            this.total = 0;
        }

        public int addToCart(int price) {
            this.total += price;
            return this.total;
        }

    }
}