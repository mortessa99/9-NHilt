package com.example.a9.di.retrofit.diretrofit

import com.example.a9.di.BASE_URL
import com.example.a9.di.NAMED_BODY
import com.example.a9.di.NAMED_HEADER
import com.example.a9.di.NETWORK_TIMEOUT
import com.example.a9.di.retrofit.api.ApiServices
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideConnectionTimeOut() = NETWORK_TIMEOUT

    //این برای اعمال تنظیمات خاصی است که گاهی بک اند دولوپر فراموش میکند به ما بگوید و بهتر است از قبل مدیریت شود
    @Provides
    @Singleton
    fun provideGson() : Gson = GsonBuilder().setLenient().create()

    //بسته به اطلاعاتی که میخاهیم از هدر یا بادی بدست بیاوریم.معمولا فقط با بادی کار داریم ولی اینجا برای هردو نوشتیم.دقت کنید که اینترسپتورها برای لاگ کردن خطاها و اطلاعات هستند نه چیز دیگری
    @Provides
    @Singleton
    @Named(NAMED_HEADER)
    fun provideHeaderInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.HEADERS
    }

    @Provides
    @Singleton
    @Named(NAMED_BODY)
    fun provideBodyInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideClient(time:Long,@Named(NAMED_HEADER)header:HttpLoggingInterceptor,@Named(NAMED_BODY)body:HttpLoggingInterceptor)
        = OkHttpClient.Builder()
        .addInterceptor(header)
        .addInterceptor(body)
        .connectTimeout(time,TimeUnit.SECONDS)
        .readTimeout(time,TimeUnit.SECONDS)
        .writeTimeout(time,TimeUnit.SECONDS)
        .retryOnConnectionFailure(true) //تلاش برای اخرین بار.میتوانید ننویسید
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl:String , gson:Gson , client:OkHttpClient) :ApiServices
        =Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(ApiServices::class.java)

}