package com.itzy.android.shopping.data;

import java.io.Serializable;

public class ShoppingItem implements Serializable{
    private String image;
    private String name;
    private String price;
    private String origin;

    public ShoppingItem(String image, String name, String price, String origin) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.origin = origin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "ShoppingItem{" +
                "image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }
}

