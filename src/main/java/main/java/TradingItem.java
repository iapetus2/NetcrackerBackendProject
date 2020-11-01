package main.java;


import java.util.Objects;
import lombok.*;

public class TradingItem {

    @Getter
    @Setter
    private String itemName;
    @Getter
    @Setter
    private Long itemPrice;
    @Getter
    @Setter
    private TradingItemType itemCategory;

    public TradingItem(){}

    public TradingItem(String itemName, TradingItemType itemCategory, Long itemPrice) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
    }

    public TradingItem(String itemName){
        this.itemName = itemName;
    }

    @ToString
    public class TradingItemToString {
        private String itemName;
        private TradingItemType itemType;
        private Long itemPrice;
    }

    @EqualsAndHashCode
    public class TradingItemEqualsAndHashcode {
        private String itemName;
        private TradingItemType itemType;
        private Long itemPrice;
    }

}


