package com.flamboyantes.model.products;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AllNewProductArray {
    @SerializedName("products")
    @Expose
    private ArrayList <AllNewPeoductsDataModel>  allNewPeoductsDataModel;

    public ArrayList<AllNewPeoductsDataModel> getAllNewPeoductsDataModel() {
        return allNewPeoductsDataModel;
    }

    public void setAllNewPeoductsDataModel(ArrayList<AllNewPeoductsDataModel> allNewPeoductsDataModel) {
        this.allNewPeoductsDataModel = allNewPeoductsDataModel;
    }
}
