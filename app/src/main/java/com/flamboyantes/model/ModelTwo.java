package com.flamboyantes.model;

public class ModelTwo {
    int img;
    String name;
    String ammount;

    public ModelTwo(int img, String name, String ammount) {
        this.img = img;
        this.name = name;
        this.ammount = ammount;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmmount() {
        return ammount;
    }

    public void setAmmount(String ammount) {
        this.ammount = ammount;
    }
}
