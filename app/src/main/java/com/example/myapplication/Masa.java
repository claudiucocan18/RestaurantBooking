package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Masa implements Parcelable  {
    Boolean libera;
    int nr_locuri;

    public Masa() {

    }

    public Masa(Boolean libera, int nr_locuri) {
        this.libera = libera;
        this.nr_locuri = nr_locuri;
    }


    protected Masa(Parcel in) {
        byte tmpLibera = in.readByte();
        libera = tmpLibera == 0 ? null : tmpLibera == 1;
        nr_locuri = in.readInt();
    }

    public static final Creator<Masa> CREATOR = new Creator<Masa>() {
        @Override
        public Masa createFromParcel(Parcel in) {
            return new Masa(in);
        }

        @Override
        public Masa[] newArray(int size) {
            return new Masa[size];
        }
    };

    public Boolean getLibera() {
        return libera;
    }

    public void setLibera(Boolean libera) {
        this.libera = libera;
    }



    @Override
    public String toString() {
        return "Masa{" +
                "libera=" + libera +
                ", nr_locuri=" + nr_locuri +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (libera == null ? 0 : libera ? 1 : 2));
        parcel.writeInt(nr_locuri);
    }

    public int getNr_locuri() {
        return nr_locuri;
    }

    public void setNr_locuri(int nr_locuri) {
        this.nr_locuri = nr_locuri;
    }
};

