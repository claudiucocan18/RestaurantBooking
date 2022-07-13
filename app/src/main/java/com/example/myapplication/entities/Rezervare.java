package com.example.myapplication.entities;

import android.os.Parcel;

import java.util.Objects;

public class Rezervare {

    //String id;
    String numeRestaurant;
    String data;
    String adresaRestaurant;
    String ora;
    String nrPersoane;
    String user;
    String stare;
    String key;
    String telefon;

    public Rezervare() {
        this.numeRestaurant = "";
        this.data = "";
        this.adresaRestaurant = "";
        this.nrPersoane = "";
        this.ora = "";
        this.stare ="";
        this.user="";
        this.key="";
        this.telefon="";
    }

    public Rezervare(String numeRestaurant, String data, String adresaRestaurant, String ora, String nrPersoane, String user, String telefon) {
        this.numeRestaurant = numeRestaurant;
        this.data = data;
        this.adresaRestaurant = adresaRestaurant;
        this.nrPersoane = nrPersoane;
        this.ora = ora;
        this.stare ="Pending approval";
        this.user=user;
        this.telefon=telefon;
        this.key="default";
    }

    public Rezervare(String numeRestaurant, String data, String adresaRestaurant, String ora, String nrPersoane, String user, String telefon, String key) {
        this.numeRestaurant = numeRestaurant;
        this.data = data;
        this.adresaRestaurant = adresaRestaurant;
        this.nrPersoane = nrPersoane;
        this.ora = ora;
        this.stare ="Pending approval";
        this.user=user;
        this.telefon=telefon;
        this.key=key;

    }

    protected Rezervare(Parcel in) {
        numeRestaurant = in.readString();
        data = in.readString();
        adresaRestaurant = in.readString();
        ora = in.readString();
        nrPersoane = in.readString();
        user = in.readString();
        stare = in.readString();
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

    public String getStare() {
        return stare;
    }

    public void setStare(String stare) {
        this.stare = stare;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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
        return Objects.equals(numeRestaurant, rezervare.numeRestaurant) && Objects.equals(data, rezervare.data) && Objects.equals(adresaRestaurant, rezervare.adresaRestaurant) && Objects.equals(ora, rezervare.ora) && Objects.equals(nrPersoane, rezervare.nrPersoane) && Objects.equals(user, rezervare.user) && Objects.equals(stare, rezervare.stare);
    }

}
