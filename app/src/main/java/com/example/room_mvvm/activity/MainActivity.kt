package com.example.room_mvvm.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.room_mvvm.R
import com.example.room_mvvm.adapter.NoteAdapter
import com.example.room_mvvm.model.NoteModel
import com.example.room_mvvm.view_model.NoteViewModel

class MainActivity : AppCompatActivity() {
    lateinit var rcvNote: RecyclerView
    lateinit var btnAdd: Button
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(
            this,
            NoteViewModel.NoteViewModelFactory(this.application)
        )[NoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rcvNote = findViewById(R.id.rcv_note)
        btnAdd = findViewById(R.id.btn_add)
        initControls()
        initEvent()
    }

    private fun initEvent() {
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initControls() {
        val adapter: NoteAdapter = NoteAdapter(this@MainActivity, onItemUpdate, onItemDelete)
        rcvNote.setHasFixedSize(true)
        rcvNote.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rcvNote.adapter = adapter
        noteViewModel.getAllNote().observe(this, Observer {
            adapter.setListNote(it)
        })
    }

    private val onItemUpdate: (NoteModel) -> Unit = {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("UPDATE", it)
        startActivity(intent)
    }

    private val onItemDelete: (NoteModel) -> Unit = {
        noteViewModel.deleteNote(it)
    }
}