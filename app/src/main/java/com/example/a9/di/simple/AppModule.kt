package com.example.a9.di.simple

import android.content.Context
import com.example.a9.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    //Use from Context:in this case our string is in the res->Strings
    @Provides
    @Singleton
    @Named("APP_INFO")
    fun provideTextFromStrings(@ApplicationContext context: Context) : String {
        return context.getString(R.string.text_info)
    }

}