package com.example.songuhun.objects;

import com.example.songuhun.objects.Order;

import java.util.ArrayList;

public class Table {
    private ArrayList<Order> ordersList;
    private String identifier;

    public Table(String identifier){
        ordersList = new ArrayList<>();
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public ArrayList<Order> getOrders() {
        return ordersList;
    }
}
