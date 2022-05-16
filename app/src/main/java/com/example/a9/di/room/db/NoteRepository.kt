package com.example.a9.di.room.db

import androidx.room.Entity
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NoteDao) {
    fun saveNote(noteModel: NoteModel) = dao.saveNote(noteModel)
    fun getAll()=dao.getAll()
}