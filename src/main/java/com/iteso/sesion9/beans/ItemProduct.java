package com.iteso.sesion9.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemProduct  implements Parcelable {

    private int code;
    private int image;
    private String title;
    private String description;
    private int category;
    private int store;

    public ItemProduct() {
    }

    public ItemProduct(int code, int image, String title, String description, int category, int store) {
        this.code = code;
        this.image = image;
        this.title = title;
        this.description = description;
        this.category = category;
        this.store = store;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "ItemProduct{" +
                "code=" + code +
                ", image=" + image +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", store=" + store +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeInt(this.image);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeInt(this.category);//this.category, flags
        dest.writeInt(this.store);//this.store, flags
    }

    protected ItemProduct(Parcel in) {
        this.code = in.readInt();
        this.image = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.store = in.readParcelable(Store.class.getClassLoader());
    }

    public static final Creator<ItemProduct> CREATOR = new Creator<ItemProduct>() {
        @Override
        public ItemProduct createFromParcel(Parcel source) {
            return new ItemProduct(source);
        }

        @Override
        public ItemProduct[] newArray(int size) {
            return new ItemProduct[size];
        }
    };
}
