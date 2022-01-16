package com.example.sallebloc.instance;



import com.example.sallebloc.beans.Occupation;
import com.example.sallebloc.beans.Results;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {
    String BASE_URL = "https://salle-bloc-exam.herokuapp.com/";
    @GET("salle/salles")
    Call<List<Results>> getsuperHeroes();

    @POST("occupation/post")
    Call<List<Results>> createOccupation(@Body Occupation occupation);

    @DELETE("occupation/delete1/{id}")
    Call<List<Results>> deleteOccupation(@Path("id") String id);
}
