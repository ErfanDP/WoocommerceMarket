package org.maktab.woocommercemarket.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Image implements Serializable {
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("src")
    @Expose
    private String mSrc;
    @SerializedName("name")
    @Expose
    private String mName;

    public Image(int id, String src, String name) {
        mId = id;
        mSrc = src;
        mName = name;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getSrc() {
        return mSrc;
    }

    public void setSrc(String src) {
        mSrc = src;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
