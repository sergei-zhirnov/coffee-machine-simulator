import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",");

        Book book = new Book(input[0], input[1]);
        System.out.println(book.getBookInfo());
    }
}

class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }


    public String getBookInfo() {
        return title + " by " + author;
    }
}