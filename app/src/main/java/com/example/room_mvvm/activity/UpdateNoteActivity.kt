package com.example.room_mvvm.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.room_mvvm.R
import com.example.room_mvvm.model.NoteModel
import com.example.room_mvvm.view_model.NoteViewModel

class UpdateNoteActivity : AppCompatActivity() {
    lateinit var edtTitle: EditText
    lateinit var edtDescription: EditText
    lateinit var btnUpdate: Button

    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)
        edtTitle = findViewById(R.id.edt_title)
        edtDescription = findViewById(R.id.edt_description)
        btnUpdate = findViewById(R.id.btn_update)

        val noteModel = intent.getSerializableExtra("UPDATE") as NoteModel
        edtTitle.setText(noteModel.title)
        edtDescription.setText(noteModel.description)

        btnUpdate.setOnClickListener {

            noteModel.title = edtTitle.text.toString()
            noteModel.description = edtDescription.text.toString()
            noteViewModel.updateNote(noteModel)
            finish()
        }
    }
}