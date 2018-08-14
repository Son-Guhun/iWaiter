package com.example.songuhun.objects;

import java.math.BigDecimal;

public class Dish {
    private int icon;
    private String name;
    private BigDecimal price;

    public Dish(int icon, String name) {
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() { return price; }

    public void setPrice(String price) { this.price = new BigDecimal(price); }

    public void setPrice(BigDecimal price) { this.price = price; }

}
