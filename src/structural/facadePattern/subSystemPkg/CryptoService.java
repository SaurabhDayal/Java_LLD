package structural.facadePattern.subSystemPkg;

public abstract class CryptoService {

    private DatabaseService databaseService;
    private SomeComplexStuff complexStuff;

    public static class SomeComplexStuff {
    }

    public abstract void buyCurrency(User user, double amount);
}
