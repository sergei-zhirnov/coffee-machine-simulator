import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int initialValue = scanner.nextInt();
        int additionValue = scanner.nextInt();
        int multiplicationValue = scanner.nextInt();

        Calculator calculator = new Calculator(initialValue);
        calculator.add(additionValue);
        calculator.multiply(multiplicationValue);

        System.out.println(calculator.getValue());
        System.out.println(Calculator.showHistory());
    }
}

class Calculator {
    private int value;
    private static int history = 0;

    public Calculator(int value) {
        this.value = value;
    }



    public void add(int num) {
        this.value += num;
        history++;
    }

    public void multiply(int num) {
        this.value *= num;
        history++;
    }

    public static int showHistory() {
        return history;
    }

    public int getValue() {
        return this.value;
    }
}