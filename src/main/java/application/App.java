package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
        entities.TradingItem a = new entities.TradingItem("lol", entities.TradingItemType.METALS, 100L);
        a.setItemPrice(200L);
        System.out.println(a.toString());
        System.out.println("Hello");
    }

}