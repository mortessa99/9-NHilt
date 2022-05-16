package com.example.a9.di.room.diroom

import android.content.Context
import androidx.room.Room
import com.example.a9.di.room.db.NoteDatabase
import com.example.a9.di.room.db.NoteModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context,NoteDatabase::class.java,"mydatabase")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideDao(db:NoteDatabase) = db.noteDao()

    //Entity یا مدل مان چون عملیات سنگینی ندارد نیازی به نوشتن سینگلتون نیست.بنویسید هم مشکلی ندارد.
    @Provides
    fun provideEntity()=NoteModel()
}