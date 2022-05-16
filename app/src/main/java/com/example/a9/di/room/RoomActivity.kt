package com.example.a9.di.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a9.databinding.ActivityRoomBinding
import com.example.a9.di.room.db.NoteAdapter
import com.example.a9.di.room.db.NoteModel
import com.example.a9.di.room.db.NoteRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RoomActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRoomBinding

    @Inject
    lateinit var repository: NoteRepository

    @Inject
    lateinit var noteModel: NoteModel

    @Inject
    lateinit var noteAdapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            button.setOnClickListener {
                //Save
                noteModel.id = 0
                noteModel.title = edtText.text.toString()
                repository.saveNote(noteModel)
                edtText.setText("") //خالی کردن فیلد تکست بعد از ذخیره کردن
                noteAdapter.differ.submitList(repository.getAll()) //نشان دادن لیست در لحظه
            }

                //Get Data
            noteAdapter.differ.submitList(repository.getAll())
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@RoomActivity)
                adapter = noteAdapter
            }

                //Clicked Items
            noteAdapter.whenClickedOnMyItem {
                Toast.makeText(this@RoomActivity, "${it.id} ${it.title}", Toast.LENGTH_SHORT).show()
            }

        }
    }
}