package org.maktab.woocommercemarket.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("price")
    @Expose
    private String mPrice;
    @SerializedName("description")
    @Expose
    private String mDescription;
    @SerializedName("images")
    @Expose
    private List<ProductImage> mImages;

    public Product(int id, String name, String price, String description, List<ProductImage> images) {
        mId = id;
        mName = name;
        mPrice = price;
        mDescription = description;
        mImages = images;
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

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<ProductImage> getImages() {
        return mImages;
    }

    public void setImages(List<ProductImage> images) {
        mImages = images;
    }

    public static Product getLoadingInstance() {
        List<ProductImage> productImages = new ArrayList<>();
        return new Product(0,"loading","loading","Loading",productImages);
    }

    @Override
    public String toString() {
        return "Product{" +
                "mName='" + mName + '\'' +
                ", mPrice='" + mPrice + '\'' +
                '}';
    }
}
