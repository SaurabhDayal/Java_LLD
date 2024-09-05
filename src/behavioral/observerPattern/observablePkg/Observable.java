package behavioral.observerPattern.observablePkg;

import behavioral.observerPattern.observerPkg.Observer;

public interface Observable {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
