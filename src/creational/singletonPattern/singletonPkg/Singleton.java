package creational.singletonPattern.singletonPkg;

public class Singleton {

    private final String data; // Immutable field to store the singleton's data.
    private static volatile Singleton instance; // Volatile to ensure visibility across threads.

    private Singleton(String data) {
        this.data = data; // Assign the provided data to the final field.
    }

    public static Singleton getInstance(String data) {

        Singleton result = instance; // Local reference for performance improvement.

        if (result == null) { // First check to avoid unnecessary synchronization.

            synchronized (Singleton.class) { // Lock to ensure thread-safe access.

                result = instance; // Recheck instance within the synchronized block.

                if (result == null) { // Second check to confirm instance is still null.

                    instance = result = new Singleton(data); // Create the singleton instance.
                }
            }
        }
        
        return result; // Return the singleton instance.
    }

    public String getData() {
        return data; // Return the stored data.
    }
}
