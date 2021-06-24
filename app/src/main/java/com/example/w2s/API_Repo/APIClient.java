package com.example.w2s.API_Repo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASEURL="https://jsonplaceholder.typicode.com";

    private static Retrofit retrofit=null;

    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit =new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
