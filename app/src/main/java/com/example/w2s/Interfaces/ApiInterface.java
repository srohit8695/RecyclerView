package com.example.w2s.Interfaces;

import com.example.w2s.Models.Info;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/posts")
    Call<List<Info>> getDatas();
}
