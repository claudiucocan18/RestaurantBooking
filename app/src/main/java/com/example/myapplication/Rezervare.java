package com.example.myapplication;

import java.util.Objects;

public class Rezervare {

    String numeRestaurant;
    String utilizator;
    String data;
    String adresaRestaurant;
    String ora;
    int nrPersoane;

    public Rezervare() {
    }

    public Rezervare(String numeRestaurant, String utilizator, String data, String adresaRestaurant, String ora, int nrPersoane) {
        this.numeRestaurant = numeRestaurant;
        this.utilizator = utilizator;
        this.data = data;
        this.adresaRestaurant = adresaRestaurant;
        this.nrPersoane = nrPersoane;
        this.ora = ora;
    }

    public String getNumeRestaurant() {
        return numeRestaurant;
    }

    public void setNumeRestaurant(String numeRestaurant) {
        this.numeRestaurant = numeRestaurant;
    }

    public String getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(String utilizator) {
        this.utilizator = utilizator;
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

    public int getNrPersoane() {
        return nrPersoane;
    }

    public void setNrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "numeRestaurant='" + numeRestaurant + '\'' +
                ", utilizator='" + utilizator + '\'' +
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
        return nrPersoane == rezervare.nrPersoane && Objects.equals(numeRestaurant, rezervare.numeRestaurant) && Objects.equals(utilizator, rezervare.utilizator) && Objects.equals(data, rezervare.data) && Objects.equals(adresaRestaurant, rezervare.adresaRestaurant) && Objects.equals(ora, rezervare.ora);
    }


}
