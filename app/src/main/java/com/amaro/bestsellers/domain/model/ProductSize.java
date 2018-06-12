package com.amaro.bestsellers.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ProductSize implements Parcelable {

    @SerializedName("available")
    public boolean available;
    @SerializedName("size")
    public String size;

    protected ProductSize(Parcel in) {
        available = in.readInt() == 0 ? false : true;
        size = in.readString();
    }

    public static final Creator<ProductSize> CREATOR = new Creator<ProductSize>() {
        @Override
        public ProductSize createFromParcel(Parcel in) {
            return new ProductSize(in);
        }

        @Override
        public ProductSize[] newArray(int size) {
            return new ProductSize[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(available ? 1 : 0);
        dest.writeString(size);
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}