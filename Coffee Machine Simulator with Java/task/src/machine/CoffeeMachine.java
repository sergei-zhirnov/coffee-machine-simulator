package machine;

import java.util.Objects;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RealMachine gaggia = new RealMachine();

        while (gaggia.state != MachineState.EXIT) {
            switch (gaggia.state) {
                case MENU:
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                    break;
                case DRINK:
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, " +
                            "3 - cappuccino, back - to main menu:");
                    break;
                case FILL_WATER:
                    System.out.println("Write how many ml of water you want to add:");
                    break;
                case FILL_MILK:
                    System.out.println("Write how many ml of milk you want to add:");
                    break;
                case FILL_COFFEE:
                    System.out.println("Write how many grams of coffee beans you want to add:");
                    break;
                case FILL_CUPS:
                    System.out.println("Write how many disposable cups you want to add:");
                    break;
                default:
                    System.out.println("Oops! Something wrong happened...");
                    break;
            }
            gaggia.processInput(scanner.nextLine());
        }

    }

}

class RealMachine {
    MachineState state = MachineState.MENU;

    int water = 400;
    int milk = 540;
    int coffee = 120;
    int cups = 9;
    int money = 550;

    public void processInput(String input) {
        if (this.state == MachineState.MENU) {
            if (Objects.equals(input, "buy")) {
                this.state = MachineState.DRINK;
            } else if (Objects.equals(input, "fill")) {
                this.state = MachineState.FILL_WATER;
            } else if (Objects.equals(input, "take")) {
                giveMoney();
            } else if (Objects.equals(input, "remaining")) {
                printState();
            } else if (Objects.equals(input, "exit")) {
                this.state = MachineState.EXIT;
            } else {
                System.out.println("WRONG INPUT!");
            }
        } else if (this.state == MachineState.DRINK) {
            buyCoffee(input);
            this.state = MachineState.MENU;
        } else if (this.state == MachineState.FILL_WATER) {
            this.water += Integer.parseInt(input);
            this.state = MachineState.FILL_MILK;
        } else if (this.state == MachineState.FILL_MILK) {
            this.milk += Integer.parseInt(input);
            this.state = MachineState.FILL_COFFEE;
        } else if (this.state == MachineState.FILL_COFFEE) {
            this.coffee += Integer.parseInt(input);
            this.state = MachineState.FILL_CUPS;
        } else if (this.state == MachineState.FILL_CUPS) {
            this.cups += Integer.parseInt(input);
            this.state = MachineState.MENU;
        }
    }



    public void giveMoney() {
        int moneyToGive = this.money;
        this.money = 0;
        System.out.println("I gave you $" + moneyToGive);
    }

    public void printState() {
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money
                                
                """, this.water, this.milk, this.coffee,
                this.cups, this.money);
    }

    public int calculateRemainCups(CoffeeType coffeeType) {

        int milkCups = 0;
        int waterCups = 0;
        int coffeeCups = 0;
        int preresult = 0;
        int result = 0;
        switch (coffeeType) {
            case ESPRESSO:
                waterCups = this.water / 250;
                coffeeCups = this.coffee / 16;
                preresult = Math.min(Math.min(waterCups, coffeeCups), this.cups);
                if (waterCups == 0) {
                    result = -10;
                } else if (coffeeCups == 0) {
                    result = -30;
                } else if (this.cups == 0) {
                    result = -40;
                } else {
                    result = preresult;
                }
                break;
            case LATTE:
                waterCups = this.water / 350;
                coffeeCups = this.coffee / 20;
                milkCups = this.milk / 75;
                preresult = Math.min(Math.min(Math.min(milkCups, waterCups), coffeeCups), this.cups);
                if (waterCups == 0) {
                    result = -10;
                } else if (milkCups == 0) {
                    result = -20;
                } else if (coffeeCups == 0) {
                    result = -30;
                } else if (this.cups == 0) {
                    result = -40;
                } else {
                    result = preresult;
                }
                break;
            case CAPPUCCINO:
                waterCups = this.water / 200;
                coffeeCups = this.coffee / 12;
                milkCups = this.milk / 100;
                preresult = Math.min(Math.min(Math.min(milkCups, waterCups), coffeeCups), this.cups);
                if (waterCups == 0) {
                    result = -10;
                } else if (milkCups == 0) {
                    result = -20;
                } else if (coffeeCups == 0) {
                    result = -30;
                } else if (this.cups == 0) {
                    result = -40;
                } else {
                    result = preresult;
                }
                break;
            default:
                break;
        }
        return result;
    }


    public void prepareDrink(CoffeeType coffeeType) {
        switch (coffeeType) {
            case ESPRESSO:
                this.water -= 250;
                this.coffee -= 16;
                this.cups -= 1;
                this.money += 4;
                break;
            case LATTE:
                this.water -= 350;
                this.milk -= 75;
                this.coffee -= 20;
                this.cups -= 1;
                this.money += 7;
                break;
            case CAPPUCCINO:
                this.water -= 200;
                this.milk -= 100;
                this.coffee -= 12;
                this.cups -= 1;
                this.money += 6;
                break;
            default:
                break;
        }
    }


    public void buyCoffee(String input) {

        if (Objects.equals(input, "back")) {
            return;
        } else if (!Objects.equals(input, "1")
                && !Objects.equals(input, "2")
                && !Objects.equals(input, "3")) {
            System.out.println("WRONG INPUT!");
            return;
        }

        CoffeeType drink = switch (input) {
            case "1" -> CoffeeType.ESPRESSO;
            case "2" -> CoffeeType.LATTE;
            case "3" -> CoffeeType.CAPPUCCINO;
            default -> CoffeeType.UNKNOWN;
        };
        int remain = calculateRemainCups(drink);
        switch (remain) {
            case -10:
                System.out.println("Sorry, not enough water!");
                break;
            case -20:
                System.out.println("Sorry, not enough milk!");
                break;
            case -30:
                System.out.println("Sorry, not enough coffee beans!");
                break;
            case -40:
                System.out.println("Sorry, not enough disposable cups!");
                break;
            default:
                System.out.println("I have enough resources, making you a coffee!");
                prepareDrink(drink);

        }

    }

}

enum MachineState {
    MENU, DRINK, FILL_WATER, FILL_MILK, FILL_COFFEE, FILL_CUPS, EXIT
}

enum CoffeeType {
    ESPRESSO, LATTE, CAPPUCCINO, UNKNOWN
}