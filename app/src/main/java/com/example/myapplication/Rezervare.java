package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Rezervare {

    //String id;
    String numeRestaurant;
    String data;
    String adresaRestaurant;
    String ora;
    String nrPersoane;
    String user;
    Boolean aprobata;
    String key;

    public Rezervare() {

    }

    public Rezervare(String numeRestaurant, String data, String adresaRestaurant, String ora, String nrPersoane, String user) {
        this.numeRestaurant = numeRestaurant;
        this.data = data;
        this.adresaRestaurant = adresaRestaurant;
        this.nrPersoane = nrPersoane;
        this.ora = ora;
        this.aprobata=false;
        this.user=user;
        this.key="default";
    }

    public Rezervare(String numeRestaurant, String data, String adresaRestaurant, String ora, String nrPersoane, String user, String key) {
        this.numeRestaurant = numeRestaurant;
        this.data = data;
        this.adresaRestaurant = adresaRestaurant;
        this.nrPersoane = nrPersoane;
        this.ora = ora;
        this.aprobata=false;
        this.user=user;
        this.key=key;

    }

    protected Rezervare(Parcel in) {
        numeRestaurant = in.readString();
        data = in.readString();
        adresaRestaurant = in.readString();
        ora = in.readString();
        nrPersoane = in.readString();
        user = in.readString();
        byte tmpAprobata = in.readByte();
        aprobata = tmpAprobata == 0 ? null : tmpAprobata == 1;
    }


    public String getNumeRestaurant() {
        return numeRestaurant;
    }

    public void setNumeRestaurant(String numeRestaurant) {
        this.numeRestaurant = numeRestaurant;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAdresaRestaurant() {
        return adresaRestaurant;
    }

    public void setAdresaRestaurant(String adresaRestaurant) {
        this.adresaRestaurant = adresaRestaurant;
    }

    public String getNrPersoane() {
        return nrPersoane;
    }

    public void setNrPersoane(String nrPersoane) {
        this.nrPersoane = nrPersoane;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Boolean getAprobata() {
        return aprobata;
    }

    public void setAprobata(Boolean aprobata) {
        this.aprobata = aprobata;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "numeRestaurant='" + numeRestaurant + '\'' +
                ", data='" + data + '\'' +
                ", adresaRestaurant='" + adresaRestaurant + '\'' +
                ", ora='" + ora + '\'' +
                ", nrPersoane=" + nrPersoane +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rezervare rezervare = (Rezervare) o;
        return Objects.equals(numeRestaurant, rezervare.numeRestaurant) && Objects.equals(data, rezervare.data) && Objects.equals(adresaRestaurant, rezervare.adresaRestaurant) && Objects.equals(ora, rezervare.ora) && Objects.equals(nrPersoane, rezervare.nrPersoane) && Objects.equals(user, rezervare.user) && Objects.equals(aprobata, rezervare.aprobata);
    }

}
