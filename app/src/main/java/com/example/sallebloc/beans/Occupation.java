package com.example.sallebloc.beans;

public class Occupation {
    String date;
    String crenaux;
    String salle;
    String _id;

    public Occupation(String date, String crenaux, String salle) {
        this.date = date;
        this.crenaux = crenaux;
        this.salle = salle;
        this._id="";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCrenaux() {
        return crenaux;
    }

    public void setCrenaux(String crenaux) {
        this.crenaux = crenaux;
    }

    public String getSalle() {
        return salle;
    }

    public void setSalle(String salle) {
        this.salle = salle;
    }
}
