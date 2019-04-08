package com.iteso.sesion9.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {

    private int idCity;
    private String name;

    public City(int idCity, String name) {
        this.idCity = idCity;
        this.name = name;
    }

    public City() {

    }


    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.idCity);
        dest.writeString(this.name);
    }

    protected City(Parcel in) {
        this.idCity = in.readInt();
        this.name = in.readString();
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
