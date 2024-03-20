package org.primeTimeHotel.Domain_Model_Objects;

import java.util.Objects;

public class CartItem {


    private Item item;
    private int quantity;
    private int serialNumber;

    public CartItem() {
        item = null;
        quantity = -1;
        serialNumber = -1;
    }

    public CartItem(Item item, int quantity, int serialNumber) {
        setItem(item);
        setQuantity(quantity);
        setSerialNumber(serialNumber);
    }


    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public int getSerialNumber() { return serialNumber; }
    public void setSerialNumber(int serialNumber) { this.serialNumber = serialNumber; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartItem obj)) return false;
        return item.equals(obj.item) && (quantity == obj.quantity) && (serialNumber == obj.serialNumber);
    }

    @Override
    public int hashCode() { return Objects.hash(item, quantity, serialNumber);}
}
