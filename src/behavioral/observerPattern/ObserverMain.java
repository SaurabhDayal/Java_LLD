package behavioral.observerPattern;

import behavioral.observerPattern.observablePkg.NewsStation;
import behavioral.observerPattern.observablePkg.WeatherStation;
import behavioral.observerPattern.observerPkg.EmailObserver;
import behavioral.observerPattern.observerPkg.Observer;
import behavioral.observerPattern.observerPkg.SMSObserver;

public class ObserverMain {
    public static void main(String[] args) {

        // Create Observables aka Publishers
        WeatherStation weatherStation = new WeatherStation();
        NewsStation newsStation = new NewsStation();
        System.out.println();

        // create "example1@example.com" EMAIL weather station subscriber
        Observer weatherEmailObserver = new EmailObserver("example1@example.com", weatherStation);
        weatherStation.registerObserver(weatherEmailObserver); // Add email observer to weather station

        // create "example1@example.com" EMAIL news station subscriber
        Observer newsEmailObserver = new EmailObserver("example1@example.com", newsStation);
        newsStation.registerObserver(newsEmailObserver);       // Add email observer to news station

        // create "123-456-7890" SMS weather station subscribers
        Observer weatherSmsObserver = new SMSObserver("123-456-7890", weatherStation);
        weatherStation.registerObserver(weatherSmsObserver);   // Add SMS observer to weather station

        // create "123-456-7890" SMS news station subscribers
        Observer newsSmsObserver = new SMSObserver("123-456-7890", newsStation);
        newsStation.registerObserver(newsSmsObserver);         // Add SMS observer to news station

        // ---------------------------------------------------------------------------------------//

        // Simulate a weather update
        weatherStation.setWeatherUpdate("Sunny");              // Notify all observers of weather station about weather change
        System.out.println();

        // Simulate a news update
        newsStation.setNewsUpdate("Breaking News: Market Crash!"); // Notify all observers of news station about news update
        System.out.println();

        // Remove "123-456-7890" SMS Observer/Subscriber
        weatherStation.removeObserver(weatherSmsObserver);     // Remove SMS observer from weather station
        newsStation.removeObserver(newsSmsObserver);           // Remove SMS observer from news station

        // Simulate a weather update
        weatherStation.setWeatherUpdate("Rainy");              // Notify remaining observers of weather station about weather change
        System.out.println();

        // Simulate a news update
        newsStation.setNewsUpdate("Sports: Team A won the game!"); // Notify remaining observers of news station about news update
        System.out.println();
    }
}
