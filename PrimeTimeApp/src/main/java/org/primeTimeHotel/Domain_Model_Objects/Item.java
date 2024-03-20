package org.primeTimeHotel.Domain_Model_Objects;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Item {
    private int id;
    private double price;
    private String name;
    private String description;
    private ImageIcon image;

    private static int idCounter = 0;

    public Item(){
        this.id = idCounter;
        idCounter++;
    }
    public Item(String itemName, String itemDescription, int itemId, double itemPrice, Image img){
        this.name = itemName;
        this.description = itemDescription;
        this.id = itemId;
        this.price = itemPrice;
        this.image = img;
    }

    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setId(int id) {this.id = id;}
    public void setPrice(double price) {this.price = price;}
    public void setImage(Image image) {this.image = image;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public int getID(){return this.id;}
    public double getPrice() {return price;}
    public Image getImage() {return image;}

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Item item = (Item) object;
        return id == item.id && Double.compare(price, item.price) == 0 && Objects.equals(name, item.name) && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, name, description);
    }




}
