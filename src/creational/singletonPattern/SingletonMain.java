package creational.singletonPattern;

import creational.singletonPattern.singletonPkg.Singleton;

public class SingletonMain {

    public static void main(String[] args) {

        System.out.println(Singleton.getInstance("Geekific"));

        Singleton singleton = Singleton.getInstance("Singleton");

        System.out.println(singleton);
        System.out.println(singleton.getData());
    }
}
