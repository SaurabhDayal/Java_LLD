package aMachineCoding.designFileSystem.CompositePattern;

public class File extends FileSystemNode {
    private String content; // Content stored in file
    private String extension; // File extension (e.g., txt, md)

    public File(String name) {
        super(name);
        this.extension = extractExtension(name);
    }

    private String extractExtension(String name) {
        int dotIndex = name.lastIndexOf('.');
        return (dotIndex > 0) ? name.substring(dotIndex + 1) : "";
    }

    public void setContent(String content) {
        this.content = content;
        updateModifiedTime(); // Update timestamp
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean isFile() {
        return true; // This is a file
    }

    @Override
    public void display(int depth) {
        String indent = " ".repeat(depth * 2); // Indent based on depth
        System.out.println(indent + "📄 " + getName()); // Display with file emoji
    }
}
