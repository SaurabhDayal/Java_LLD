package behavioral.observerPattern;

import behavioral.observerPattern.observablePkg.WeatherStation;
import behavioral.observerPattern.observerPkg.EmailObserver;
import behavioral.observerPattern.observerPkg.Observer;
import behavioral.observerPattern.observerPkg.SMSObserver;

public class ObserverMain {
    public static void main(String[] args) {
        // Create the weather station (subject)
        WeatherStation weatherStation = new WeatherStation();

        // Create observers
        Observer emailObserver = new EmailObserver("example1@example.com");
        Observer smsObserver = new SMSObserver("123-456-7890");

        // Register observers to the weather station
        weatherStation.registerObserver(emailObserver);
        weatherStation.registerObserver(smsObserver);

        // Simulate a weather update
        weatherStation.setWeatherUpdate("Sunny");

        // Remove SMS observer and update again
        weatherStation.removeObserver(smsObserver);
        weatherStation.setWeatherUpdate("Rainy");
    }
}
