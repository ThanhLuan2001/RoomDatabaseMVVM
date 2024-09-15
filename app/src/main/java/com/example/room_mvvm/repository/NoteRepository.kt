package com.example.room_mvvm.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.room_mvvm.dao.NoteDAO
import com.example.room_mvvm.database.NoteDatabase
import com.example.room_mvvm.model.NoteModel

class NoteRepository(application: Application) {

    private val noteDAO : NoteDAO

    init {
        val noteDatabase : NoteDatabase = NoteDatabase.getInstance(application)
        noteDAO = noteDatabase.getNoteDAO()
    }

    suspend fun insertNote(noteModel : NoteModel) = noteDAO.insertNote(noteModel)
    suspend fun updateNote(noteModel : NoteModel) = noteDAO.updateNote(noteModel)
    suspend fun deleteNote(noteModel : NoteModel) = noteDAO.deleteNote(noteModel)

    fun getAllNote() : LiveData<List<NoteModel>> = noteDAO.getAllNote()
}