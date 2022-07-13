package com.example.myapplication.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Utilizator{

    String email;
    String parola;
    String tipCont;
    String cui;

    public Utilizator(String email, String parola, String tipCont) {
        this.email = email;
        this.parola = parola;
        this.tipCont = tipCont;
        this.cui = "empty_default";
    }

    public Utilizator(String email, String parola, String tipCont, String cui) {
        this.email = email;
        this.parola = parola;
        this.tipCont = tipCont;
        this.cui = cui;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getTipCont() {
        return tipCont;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public void setTipCont(String tipCont) {
        this.tipCont = tipCont;
    }

}
