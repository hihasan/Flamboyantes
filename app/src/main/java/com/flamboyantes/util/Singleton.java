package com.flamboyantes.util;

import android.content.Context;
import androidx.fragment.app.Fragment;

public class Singleton {
    private static Singleton ourInstance = new Singleton();
    private Context context;
    private Fragment whichFragmentItIs;

    private int id;
    private String image, name, update_on_utc, short_description, total_price;
    private double price;

    private Singleton() {
    }

    public static Singleton getInstance() {
        return ourInstance;
    }

    public Context getContext() {
        if (context == null) {
            throw new RuntimeException("Context is null in Singleton singleton. Check if setContext() is called properly.");
        }
        return context;
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }



    public void setWhichFragmentItIs(Fragment whichFragmentItIs) {
        this.whichFragmentItIs = whichFragmentItIs;
    }

    public Fragment getWhichFragmentItIs() {
        return whichFragmentItIs;
    }

    public static Singleton getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(Singleton ourInstance) {
        Singleton.ourInstance = ourInstance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUpdate_on_utc() {
        return update_on_utc;
    }

    public void setUpdate_on_utc(String update_on_utc) {
        this.update_on_utc = update_on_utc;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_description(String short_description) {
        this.short_description = short_description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
