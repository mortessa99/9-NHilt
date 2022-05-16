package com.example.a9.di.retrofit.repository

import com.example.a9.di.retrofit.api.ApiServices
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ApiRepository @Inject constructor(private val api:ApiServices){
    fun getMovies() = api.movieList()
}