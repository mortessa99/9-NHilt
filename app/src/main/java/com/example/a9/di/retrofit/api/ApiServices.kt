package com.example.a9.di.retrofit.api

import com.example.a4_nretrofit.model.ResponseMoviesList
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET("movie")
    fun movieList():Call<ResponseMoviesList>
}