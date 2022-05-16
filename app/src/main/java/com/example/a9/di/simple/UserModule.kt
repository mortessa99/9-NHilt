package com.example.a9.di.simple

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UserModule {
    // String I
    @Provides
    @Singleton
    @Named("USER_NAME")
    fun provideUserName() : String = "Morteza"
    //


    //String II
    @Provides
    //@SiteName
    @Named("SITE_NAME")
    fun provideSiteName() : String {
        return "mortezza99.ir"
    }
    //
}