package behavioral.observerPattern.observablePkg;

import behavioral.observerPattern.observerPkg.Observer;
import java.util.ArrayList;
import java.util.List;

// Concrete Subject Class
public class NewsStation implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private String newsUpdate;
    private final String name = "News";  // Observable name

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(newsUpdate);
        }
    }

    public String getName() {
        return name;
    }

    // Method to change the news and notify observers
    public void setNewsUpdate(String newsUpdate) {
        this.newsUpdate = newsUpdate;
        notifyObservers();
    }
}
