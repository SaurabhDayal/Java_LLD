package aMachineCoding.designFileSystem.fileSystem;

import aMachineCoding.designFileSystem.CompositePattern.Directory;
import aMachineCoding.designFileSystem.CompositePattern.File;
import aMachineCoding.designFileSystem.CompositePattern.FileSystemNode;

public class FileSystem {
    private FileSystemNode root; // Root of the virtual file system

    public FileSystem() {
        this.root = new Directory("/"); // Root node is initialized as a directory named "/"
    }

    public boolean isValidFilePath(String path) {
        // Path is valid if it's non-null and starts with a "/"
        return path != null && path.startsWith("/");
    }

    public boolean createPath(String path) {
        if (!isValidFilePath(path)) return false; // Invalid path
        String[] pathComponents = path.split("/"); // Split by '/'
        FileSystemNode current = root;

        // Traverse to the parent of the node to be created
        for (int i = 1; i < pathComponents.length - 1; i++) {
            String component = pathComponents[i];
            if (component.isEmpty()) continue;

            // If the directory doesn't exist, create it
            if (!current.hasChild(component)) {
                FileSystemNode newDir = new Directory(component); // Create intermediate directory
                current.addChild(component, newDir);
            }

            FileSystemNode child = current.getChild(component);
            if (child.isFile()) return false; // Cannot navigate through a file
            current = child;
        }

        // Create the actual file or directory
        String lastComponent = pathComponents[pathComponents.length - 1];
        if (lastComponent.isEmpty() || current.hasChild(lastComponent)) return false; // Duplicate or invalid name

        // Determine if it's a file (has a '.') or directory
        FileSystemNode newNode = lastComponent.contains(".") ? new File(lastComponent) : new Directory(lastComponent);
        current.addChild(lastComponent, newNode); // Add the new node to its parent
        return true;
    }

    private FileSystemNode getNode(String path) {
        if (!isValidFilePath(path)) return null; // Invalid path
        if (path.equals("/")) return root; // Root path
        String[] pathComponents = path.split("/"); // Split path
        FileSystemNode current = root;

        // Traverse through the path
        for (int i = 1; i < pathComponents.length; i++) {
            String component = pathComponents[i];
            if (component.isEmpty()) continue;
            if (!current.hasChild(component)) return null; // Path doesn't exist
            current = current.getChild(component); // Move to the next node
        }
        return current; // Return found node
    }

    public boolean deletePath(String path) {
        if (!isValidFilePath(path) || path.equals("/")) return false; // Can't delete root

        String parentPath = getParentPath(path); // Get path to parent
        FileSystemNode parent = getNode(parentPath); // Find parent node
        if (parent == null || parent.isFile()) return false; // Can't delete if parent doesn't exist or is a file

        String lastComponent = path.substring(path.lastIndexOf('/') + 1); // Get the node name to delete
        return parent.removeChild(lastComponent); // Remove the child node from its parent
    }

    private String getParentPath(String path) {
        // Extract parent path by trimming last segment
        int lastSlash = path.lastIndexOf('/');
        if (lastSlash <= 0) return "/";
        return path.substring(0, lastSlash);
    }

    public void display() {
        root.display(0); // Recursively display the structure starting from root with indent level 0
    }

    public boolean setFileContent(String path, String content) {
        FileSystemNode node = getNode(path); // Find node at the given path
        if (node == null || !node.isFile()) return false; // Must be a valid file
        File file = (File) node;
        file.setContent(content); // Set file content
        return true;
    }

    public String getFileContent(String path) {
        FileSystemNode node = getNode(path); // Find node at the given path
        if (node == null || !node.isFile()) return null; // Must be a valid file
        File file = (File) node;
        return file.getContent(); // Return file's content
    }
}
