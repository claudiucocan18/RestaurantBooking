package com.example.myapplication;

public class Restaurant {

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
}
