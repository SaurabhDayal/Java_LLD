package aMachineCoding.designFileSystem.CompositePattern;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class representing a node in the file system.
 * Can be either a File or a Directory (Composite pattern).
 */
public abstract class FileSystemNode {
    private String name; // Name of the file or directory
    private Map<String, FileSystemNode> children; // Holds children (only used if it's a directory)
    private LocalDateTime createdAt; // Timestamp when node was created
    private LocalDateTime modifiedAt; // Timestamp when node was last modified

    // Constructor initializes the node with a name and timestamps
    public FileSystemNode(String name) {
        this.name = name;
        this.children = new HashMap<>(); // Children map, even if unused in File
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }
    
    // Abstract method to identify if the node is a file (true for File, false for Directory)
    public abstract boolean isFile();

    // Abstract method to display the node (used in tree view)
    public abstract void display(int depth);

    // Adds a child node (file or directory) to this node
    public void addChild(String name, FileSystemNode child) {
        this.children.put(name, child);
        this.modifiedAt = LocalDateTime.now(); // Update modification time
    }

    // Checks if a child with the given name exists
    public boolean hasChild(String name) {
        return this.children.containsKey(name);
    }

    // Retrieves a specific child node by name
    public FileSystemNode getChild(String name) {
        return this.children.get(name);
    }

    // Removes a child by name and updates modified time
    public boolean removeChild(String name) {
        if (hasChild(name)) {
            children.remove(name);
            return true;
        }
        return false;
    }

    // Getter for the name of the node
    public String getName() {
        return name;
    }

    // Returns all children of the node (used by Directory)
    public Collection<FileSystemNode> getChildren() {
        return children.values();
    }

    // Returns creation time
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Returns last modified time
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    // Updates the modified timestamp (e.g., on write or structure change)
    protected void updateModifiedTime() {
        this.modifiedAt = LocalDateTime.now();
    }
}
