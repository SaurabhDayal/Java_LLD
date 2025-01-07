package structural.flyweightPattern.factoryPkg;

public class BookType {

    private final String type;
    private final String distributor;
    private final String otherData;

    public BookType(String type, String distributor, String otherData) {
        this.type = type;
        this.distributor = distributor;
        this.otherData = otherData;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "type='" + type + '\'' +
                ", distributor='" + distributor + '\'' +
                ", otherData='" + otherData + '\'' +
                '}';
    }

    public String getOtherData() {
        return otherData;
    }

    public String getDistributor() {
        return distributor;
    }

    public String getType() {
        return type;
    }
}

/*
public record BookType(String type, String distributor, String otherData) {

    @Override
    public String toString() {
        return "BookType{" +
                "type='" + type + '\'' +
                ", distributor='" + distributor + '\'' +
                ", otherData='" + otherData + '\'' +
                '}';
    }
}
 */