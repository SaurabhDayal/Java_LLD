package behavioral.observerPattern;

import behavioral.observerPattern.observablePkg.NewsStation;
import behavioral.observerPattern.observablePkg.WeatherStation;
import behavioral.observerPattern.observerPkg.EmailObserver;
import behavioral.observerPattern.observerPkg.Observer;
import behavioral.observerPattern.observerPkg.SMSObserver;

public class ObserverMain {
    public static void main(String[] args) {
        // Create the weather station and news station (subjects)
        WeatherStation weatherStation = new WeatherStation();
        NewsStation newsStation = new NewsStation();

        // Create observers with dynamic constructor injection for different types of updates
        Observer weatherEmailObserver = new EmailObserver("example1@example.com", "Weather");
        Observer weatherSmsObserver = new SMSObserver("123-456-7890", "Weather");

        Observer newsEmailObserver = new EmailObserver("example1@example.com", "News");
        Observer newsSmsObserver = new SMSObserver("123-456-7890", "News");

        // Register weather observers to the weather station
        weatherStation.registerObserver(weatherEmailObserver);
        weatherStation.registerObserver(weatherSmsObserver);

        // Register news observers to the news station
        newsStation.registerObserver(newsEmailObserver);
        newsStation.registerObserver(newsSmsObserver);

        // Simulate a weather update
        weatherStation.setWeatherUpdate("Sunny");
        System.out.println();

        // Simulate a news update
        newsStation.setNewsUpdate("Breaking News: Market Crash!");
        System.out.println();

        // Remove SMS observer and update again for both stations
        weatherStation.removeObserver(weatherSmsObserver);
        newsStation.removeObserver(newsSmsObserver);

        weatherStation.setWeatherUpdate("Rainy");
        System.out.println();

        newsStation.setNewsUpdate("Sports: Team A won the game!");
    }
}
