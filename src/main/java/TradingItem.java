package main.java;


import java.util.Objects;

public class TradingItem {

    private String itemName;
    private String itemCategory; //could be stored in db
    private Double itemPrice;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public TradingItem(String itemName, String itemCategory, Double itemPrice) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "TradingItem{" +
                "itemName='" + itemName + '\'' +
                ", itemCategory='" + itemCategory + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingItem that = (TradingItem) o;
        return itemName.equals(that.itemName) &&
                Objects.equals(itemCategory, that.itemCategory) &&
                Objects.equals(itemPrice, that.itemPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemCategory, itemPrice);
    }

   /* public class Builder{

        public String setItemName;
        public Double setItemPrice = 0D;
        public String setItemCategory = null;

        private Builder(){

        }

        public Builder(String name){
            TradingItem.this.itemName = name;
        }

        public Builder setItemName(String name) {
            TradingItem.this.itemName = name;
            return this;
        }

        public Builder setItemCategory(String category) {
            TradingItem.this.itemCategory = category;
            return this;
        }

        public Builder setItemPrice(Double price) {
            TradingItem.this.itemPrice = price;
            return this;
        }

        public TradingItem build() {
            return TradingItem.this;
        }

    }
    private TradingItem(Builder builder){

        itemName = builder.setItemName;
        itemPrice = builder.setItemPrice;
        itemCategory = builder.setItemCategory;

    }

    */
}

