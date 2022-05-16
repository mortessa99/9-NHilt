package com.example.a9.di.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mytable")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var title :String = ""
)

//اگر مقدار اولیه ندهیم موقع تزریق به خطا میخوریم
