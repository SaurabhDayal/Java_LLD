package structural.flyweightPattern;

import structural.flyweightPattern.contextPkg.Store;

import java.util.List;
import java.util.Random;

public class FlyweightMain {

    private static final int BOOK_TYPES = 2;
    private static final int BOOKS_TO_INSERT = 100_000;

    public static void main(String[] args) {

        // Create a new store
        Store store = new Store();

        // Insert books into the store
        for (int i = 0; i < BOOKS_TO_INSERT / BOOK_TYPES; i++) {
            store.storeBook(getRandomName(), getRandomPrice(), "Action", "Follett", "Stuff");
            store.storeBook(getRandomName(), getRandomPrice(), "Fantasy", "Ingram", "Extra");
        }

        // Display the stored books
        store.displayBooks();

        // Output memory usage information
        System.out.println(BOOKS_TO_INSERT + " Books Inserted");
        System.out.println("==========================================");
        System.out.println("Memory Usage: ");
        System.out.println("Book Size (20 bytes) * " + BOOKS_TO_INSERT + " + BookTypes Size (30 bytes) * " + BOOK_TYPES);
        System.out.println("==========================================");

        // Memory calculation in MB
        float totalMemoryUsed = ((BOOKS_TO_INSERT * 20 + BOOK_TYPES * 30) / 1024.0f / 1024.0f);
        float expectedMemory = ((BOOKS_TO_INSERT * 50) / 1024.0f / 1024.0f);

        // Print the memory usage with two decimal places
        System.out.printf("Total: %.2f MB (instead of %.2f MB)%n", totalMemoryUsed, expectedMemory);
    }

    // getRandomName returns a random book name.
    private static String getRandomName() {
        List<String> books = List.of("book_1", "book_2", "book_3", "book_4", "book_5", "book_6", "book_7", "book_8", "book_9", "book_10");
        return books.get(new Random().nextInt(books.size()));
    }

    // getRandomPrice returns a random price between 10 and 200.
    private static double getRandomPrice() {
        return new Random().nextDouble(10, 200);
    }

}
