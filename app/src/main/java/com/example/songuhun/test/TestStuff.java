package com.example.songuhun.test;

import com.example.songuhun.objects.Dish;
import com.example.songuhun.objects.DishCategory;
import com.example.songuhun.myapplication.R;
import com.example.songuhun.objects.Table;
import com.example.songuhun.objects.User;

import java.util.ArrayList;
import java.util.List;

public class TestStuff {

    public static void loadDishData(List<DishCategory> parentList){
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        DishCategory dishCategory = null;

        dishes.add(new Dish(R.mipmap.generic_icon
                ,"Bobó de Camarão"));
        dishes.add(new Dish(R.mipmap.generic_icon
                , "Filé à Osvaldo Aranha"));
        dishCategory = new DishCategory("Pratos Quentes", dishes);
        parentList.add(dishCategory);

        dishes = new ArrayList<Dish>();
        dishes.add(new Dish(R.mipmap.generic_icon
                , "Pizza de Chocolate"));
        dishes.add(new Dish(R.mipmap.generic_icon
                , "Pizza Napolitana"));
        dishCategory = new DishCategory("Pizzas", dishes);
        parentList.add(dishCategory);
    }
    
    public static final ArrayList<DishCategory> dishMenu = new ArrayList<>();

    public static Table currentTable;

    public static User currentUser = null;
}
