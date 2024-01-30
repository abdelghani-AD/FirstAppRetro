package com.example.firstappretro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    //Methode 1 :
    @GET("posts/7")
    public Call<Modele> getPost1();
    //Methode 2 :
    @GET("posts/{id}")
    public Call<Modele> getPost2(@Path("id")int id);
    @GET("posts")
    public Call<List<Modele>> getPostes();

}
