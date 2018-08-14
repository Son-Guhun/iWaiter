package com.example.songuhun.utils;

import android.annotation.SuppressLint;
import android.support.v7.view.menu.MenuBuilder;
import android.view.Menu;

public final class Utilities {

    private Utilities(){
    }

    @SuppressLint("RestrictedApi")
    public static void allowIconsInOverflowMenu(Menu menu){
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
    }
}
