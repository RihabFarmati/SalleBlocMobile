package com.example.sallebloc.beans;

import com.google.gson.annotations.SerializedName;

public class Results {
    @SerializedName("name")
    private String superName;
    @SerializedName("bloc")
    private String bloc;
    @SerializedName("_id")
    private String _id;

    public Results(String name,String bloc) {
        this.bloc=bloc;
        this.superName = name;
    }

    public String getName() {
        return superName;
    }
    public String getId() {
        return _id;
    }
    public String getBloc() {
        return bloc;
    }
}


