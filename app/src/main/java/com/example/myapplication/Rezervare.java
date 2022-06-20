package com.example.myapplication;

public class Rezervare {

    String numeRestaurant;
    String utilizator;
    String data;
    String adresaRestaurant;
    int nrPersoane;

    public Rezervare() {
    }

    public Rezervare(String numeRestaurant, String utilizator, String data, String adresaRestaurant, int nrPersoane) {
        this.numeRestaurant = numeRestaurant;
        this.utilizator = utilizator;
        this.data = data;
        this.adresaRestaurant = adresaRestaurant;
        this.nrPersoane = nrPersoane;
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

    @Override
    public String toString() {
        return "Rezervare{" +
                "numeRestaurant='" + numeRestaurant + '\'' +
                ", utilizator='" + utilizator + '\'' +
                ", data='" + data + '\'' +
                ", adresaRestaurant='" + adresaRestaurant + '\'' +
                ", nrPersoane=" + nrPersoane +
                '}';
    }
}
