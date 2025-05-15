package aMachineCoding.designFileSystem;

import aMachineCoding.designFileSystem.fileSystem.FileSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileSystem fs = new FileSystem(); // Initialize the file system
        Scanner scanner = new Scanner(System.in); // Read user input from the console
        boolean isRunning = true; // aMachineCoding.designRateLimiter.Main loop control flag

        // Print available commands for user interaction
        System.out.println("File System Manager - Commands:");
        System.out.println("1. create <path> - Create a new path");
        System.out.println("2. write <path> <content> - Write content to a file");
        System.out.println("3. read <path> - Read content from a file");
        System.out.println("4. delete <path> - Delete a path");
        System.out.println("5. display - Show the entire file system structure");
        System.out.println("6. exit - Exit the program");

        // Command execution loop
        while (isRunning) {
            System.out.print("nEnter command: ");
            String input = scanner.nextLine().trim(); // Read a line of input
            String[] parts = input.split("\\s+", 3); // Split into max 3 parts: command, path, content

            if (parts.length == 0) continue; // Ignore empty lines

            String command = parts[0].toLowerCase(); // Normalize command name
            try {
                switch (command) {
                    case "create":
                        // Syntax: create <path>
                        if (parts.length >= 2) {
                            String path = parts[1];
                            boolean isCreated = fs.createPath(path);
                            System.out.println(isCreated ? "Path created successfully" : "Failed to create path");
                        } else {
                            System.out.println("Usage: create <path>");
                        }
                        break;

                    case "write":
                        // Syntax: write <path> <content>
                        if (parts.length >= 3) {
                            String path = parts[1];
                            String content = parts[2];
                            boolean isWritten = fs.setFileContent(path, content);
                            System.out.println(isWritten ? "Content written successfully" : "Failed to write content");
                        } else {
                            System.out.println("Usage: write <path> <content>");
                        }
                        break;

                    case "read":
                        // Syntax: read <path>
                        if (parts.length >= 2) {
                            String path = parts[1];
                            String content = fs.getFileContent(path);
                            if (content != null) {
                                System.out.println("Content: " + content);
                            } else {
                                System.out.println("Failed to read content");
                            }
                        } else {
                            System.out.println("Usage: read <path>");
                        }
                        break;

                    case "delete":
                        // Syntax: delete <path>
                        if (parts.length >= 2) {
                            String path = parts[1];
                            boolean isDeleted = fs.deletePath(path);
                            System.out.println(isDeleted ? "Path deleted successfully" : "Failed to delete path");
                        } else {
                            System.out.println("Usage: delete <path>");
                        }
                        break;

                    case "display":
                        // Syntax: display
                        System.out.println("nFile System Structure:");
                        fs.display();
                        break;

                    case "exit":
                        // Syntax: exit
                        isRunning = false;
                        System.out.println("Exiting...");
                        break;

                    default:
                        // Fallback for unknown commands
                        System.out.println("Unknown command. Available commands: create, write, read, delete, display, exit");
                }
            } catch (Exception e) {
                // Catch-all for unexpected runtime errors
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close(); // Release system resources
    }
}

/*
How to Use the File System - Example Commands:

1. Create a directory:
   create /documents

2. Create nested directories:
   create /documents/work/reports

3. Create a file:
   create /documents/notes.txt

4. Write content to a file:
   write /documents/notes.txt This is a test note.

5. Read content from a file:
   read /documents/notes.txt

6. Delete a file or directory:
   delete /documents/notes.txt
   delete /documents/work

7. Display the file system:
   display

8. Exit the program:
   exit
*/
