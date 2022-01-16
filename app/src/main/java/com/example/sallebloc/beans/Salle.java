package com.example.sallebloc.beans;

public class Salle {

    private String name;
    private int _id ;
    private String bloc;
    private String qrCode;

    public Salle(String name,String bloc) {
        this.name = name;
        this.bloc=bloc;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
