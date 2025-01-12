package creational.singletonPattern;

import creational.singletonPattern.singletonPkg.Singleton;

public class SingletonMain {

    public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance("Geekific");
        System.out.println(singleton1.getData());

        Singleton singleton2 = Singleton.getInstance("Singleton");
        System.out.println(singleton2.getData());
    }
}
