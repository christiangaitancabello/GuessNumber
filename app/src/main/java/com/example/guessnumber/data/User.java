package com.example.guessnumber.data;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String name;
    private String ntries;

    public User() {
    }

    public User(String name, String ntries) {
        this.name = name;
        this.ntries = ntries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNtries() {
        return ntries;
    }

    public void setNtries(String ntries) {
        this.ntries = ntries;
    }

    //region MÉTODOS PARCELABLE
    protected User(Parcel in) {
        name = in.readString();
        ntries = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(ntries);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    //endregion
}
