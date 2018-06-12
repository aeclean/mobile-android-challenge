package com.amaro.bestsellers.domain.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableString;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product implements Parcelable {

    public String name;
    public String style;
    @SerializedName("code_color")
    public String codeColor;
    @SerializedName("color_slug")
    public String colorSlug;
    @SerializedName("regular_price")
    public String regularPrice;
    @SerializedName("actual_price")
    public String actualPrice;
    @SerializedName("discount_percentage")
    public String discountPercentage;

    public String image;

    @SerializedName("on_sale")
    private boolean onSale;

    ArrayList<ProductSize> sizes;

    protected Product(Parcel in) {
        name = in.readString();
        style = in.readString();
        codeColor = in.readString();
        colorSlug = in.readString();
        regularPrice = in.readString();
        actualPrice = in.readString();
        discountPercentage = in.readString();
        image = in.readString();
        onSale = in.readInt() != 0;
        sizes = in.readArrayList(ProductSize.class.getClassLoader());
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public boolean isOnSale() {
        return onSale;
    }

    public String getAvailableSizes() {
        StringBuilder sb = new StringBuilder();

        for(ProductSize size: sizes) {
            if(!size.isAvailable())
                sb.append(String.format("%s|",size.size));
        }

        if(sb.toString().isEmpty())
            return "";

        return sb.toString().substring(0, sb.toString().length()-1);
    }

    public Uri getDisplayImage() {
        return Uri.parse(image);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(style);
        dest.writeString(codeColor);
        dest.writeString(colorSlug);
        dest.writeString(regularPrice);
        dest.writeString(actualPrice);
        dest.writeString(discountPercentage);
        dest.writeString(image);
        dest.writeInt(isOnSale() ? 1 : 0);
        dest.writeList(sizes);
    }
}
