package com.example.covid;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface  {
//    String BASE_URL="https://corona.lmao.ninja/v2/";
//    @GET("countries")

    String BASE_URL="https://c.m.163.com/ug/api/";
    @GET("wuhan/app/data/list-total")
    Call<ResponseBody> getcountrydata();
}