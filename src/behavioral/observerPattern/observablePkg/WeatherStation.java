package behavioral.observerPattern.observablePkg;

import behavioral.observerPattern.observerPkg.Observer;
import java.util.ArrayList;
import java.util.List;

// Concrete Subject Class
public class WeatherStation implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private String weatherUpdate;
    private final String name = "Weather";  // Observable name

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
            observer.update(weatherUpdate);
        }
    }

    public String getName() {
        return name;
    }

    // Method to change the weather and notify observers
    public void setWeatherUpdate(String weatherUpdate) {
        this.weatherUpdate = weatherUpdate;
        notifyObservers();
    }
}
