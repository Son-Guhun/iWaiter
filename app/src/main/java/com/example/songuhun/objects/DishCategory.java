package com.example.songuhun.objects;


import java.util.ArrayList;

/**
 * Created by user on 2/27/16.
 */
public class DishCategory {
    private String name;
    private ArrayList<Dish> childList;

    public DishCategory(String name, ArrayList<Dish> childList) {
        this.name = name;
        this.childList = childList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Dish> getChildList() {
        return childList;
    }

    public void setChildList(ArrayList<Dish> childList) {
        this.childList = childList;
    }
}
