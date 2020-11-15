package org.maktab.woocommercemarket.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("id")
    @Expose
    private String mId;
    @SerializedName("image")
    @Expose
    private Image mImage;
    @SerializedName("count")
    @Expose
    private int mCount;
    @SerializedName("display")
    @Expose
    private String mDisplay;

    public Category(String name, String id, Image image, int count, String display) {
        mName = name;
        mId = id;
        mImage = image;
        mCount = count;
        mDisplay = display;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public Image getImage() {
        return mImage;
    }

    public void setImage(Image image) {
        mImage = image;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public String getDisplay() {
        return mDisplay;
    }

    public void setDisplay(String display) {
        mDisplay = display;
    }
}
