package entities;

import lombok.*;

@Data
public class TradingItem {

    private int id;
    private String itemName;
    private TradingItemType itemCategory = TradingItemType.DEFAULT;
    private Long itemPrice = 0L;

    public TradingItem(String itemName){
        this.itemName = itemName;
    }

    public TradingItem(String itemName, TradingItemType itemCategory, Long itemPrice) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
    }


//    @Getter
//    @Setter
//    private String itemName;
//    @Getter
//    @Setter
//    private Long itemPrice;
//    @Getter
//    @Setter
//    private entities.TradingItemType itemCategory;
//
//    public entities.TradingItem(){}
//
//    public entities.TradingItem(String itemName, entities.TradingItemType itemCategory, Long itemPrice) {
//        this.itemName = itemName;
//        this.itemCategory = itemCategory;
//        this.itemPrice = itemPrice;
//    }
//
//    public entities.TradingItem(String itemName){
//        this.itemName = itemName;
//    }
//
//    @ToString
//    public static class TradingItemToString {
//        private String itemName;
//        private entities.TradingItemType itemType;
//        private Long itemPrice;
//    }
//
//    @EqualsAndHashCode
//    public static class TradingItemEqualsAndHashcode {
//        private String itemName;
//        private entities.TradingItemType itemType;
//        private Long itemPrice;
//    }

}


