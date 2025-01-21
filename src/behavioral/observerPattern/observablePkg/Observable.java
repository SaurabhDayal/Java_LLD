package behavioral.observerPattern.observablePkg;

import behavioral.observerPattern.observerPkg.Observer;

// Publisher << interface >>
public interface Observable {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();

    String getName();
}
