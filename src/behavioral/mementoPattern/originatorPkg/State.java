package behavioral.mementoPattern.originatorPkg;

public class State {

    private int version;
    private String details;

    public State(int version, String details) {
        this.version = version;
        this.details = details;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Version: " + version + ", Details: " + details;
    }
}

