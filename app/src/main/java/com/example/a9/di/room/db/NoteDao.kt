package com.example.a9.di.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    fun saveNote(note:NoteModel)

    @Query("SELECT * FROM mytable")
    fun getAll():MutableList<NoteModel>
}