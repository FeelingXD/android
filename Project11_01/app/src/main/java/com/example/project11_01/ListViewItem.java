package com.example.project11_01;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable iconDrawable;
    private String titlStr;
    private String descStr;


    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
    }


    public String getTitlStr() {
        return titlStr;
    }

    public void setTitlStr(String titlStr) {
        this.titlStr = titlStr;
    }


    public String getDescStr() {
        return descStr;
    }

    public void setDescStr(String descStr) {
        this.descStr = descStr;
    }
}
