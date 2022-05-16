package com.example.a9.di.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a4_nretrofit.model.ResponseMoviesList
import com.example.a9.databinding.ActivityRetrofitBinding
import com.example.a9.di.retrofit.diretrofit.MovieAdapter
import com.example.a9.di.retrofit.repository.ApiRepository
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class RetrofitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitBinding

    @Inject
    lateinit var repository: ApiRepository

    @Inject
    lateinit var movieAdapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            progressBar.visibility = View.VISIBLE

            //Call api
            repository.getMovies().enqueue(object:Callback<ResponseMoviesList>{
                override fun onResponse(call: Call<ResponseMoviesList>, response: Response<ResponseMoviesList>) {
                    if (response.isSuccessful) {
                        response.body()?.let { itBody->
                            itBody.data?.let { itData->
                                movieAdapter.differ.submitList(itData)
                                recyclerRetrofit.apply {
                                    layoutManager = LinearLayoutManager(this@RetrofitActivity)
                                    adapter = movieAdapter
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseMoviesList>, t: Throwable) {
                    progressBar.visibility =View.GONE
                    Log.i("tag",t.message.toString())
                }

            })
        }
    }
}