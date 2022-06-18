package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {

String imgURL;
String nume;
int nr_locuri;
String orar;
String zona;
String adresa;

    public Restaurant() {

    }

    public Restaurant(String imgURL, String nume, int nr_locuri, String orar, String zona, String adresa) {
        //6
        this.imgURL = imgURL;
        this.nume = nume;
        this.nr_locuri = nr_locuri;
        this.orar = orar;
        this.zona = zona;
        this.adresa = adresa;
    }


    protected Restaurant(Parcel in) {
        imgURL = in.readString();
        nume = in.readString();
        nr_locuri = in.readInt();
        orar = in.readString();
        zona = in.readString();
        adresa = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNr_locuri() {
        return nr_locuri;
    }

    public void setNr_locuri(int nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    public String getOrar() {
        return orar;
    }

    public void setOrar(String orar) {
        this.orar = orar;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imgURL);
        parcel.writeString(nume);
        parcel.writeInt(nr_locuri);
        parcel.writeString(orar);
        parcel.writeString(zona);
        parcel.writeString(adresa);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "imgURL='" + imgURL + '\'' +
                ", nume='" + nume + '\'' +
                ", nr_locuri=" + nr_locuri +
                ", orar='" + orar + '\'' +
                ", zona='" + zona + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
