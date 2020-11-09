package org.maktab.woocommercemarket.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("price")
    @Expose
    private String mPrice;
//    @SerializedName("price")
//    @Expose
//    private Uri mURLPicture;

    public Product(String name, String price) {
        mName = name;
        mPrice = price;
//        mURLPicture = urlPicture;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

//    public Uri getURLPicture() {
//        return mURLPicture;
//    }
//
//    public void setURLPicture(Uri URLPicture) {
//        mURLPicture = URLPicture;
//    }
}
