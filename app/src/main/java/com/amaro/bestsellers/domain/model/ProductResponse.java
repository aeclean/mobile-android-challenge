package com.amaro.bestsellers.domain.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductResponse {
    @SerializedName("products")
    @Expose
    private List<Product> data = new ArrayList<>();

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }
}
