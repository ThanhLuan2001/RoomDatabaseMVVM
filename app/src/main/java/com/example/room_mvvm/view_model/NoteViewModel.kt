package com.example.room_mvvm.view_model

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.room_mvvm.model.NoteModel
import com.example.room_mvvm.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : ViewModel() {
    private val noteRepository : NoteRepository = NoteRepository(application)

    fun insertNote(noteModel : NoteModel) = viewModelScope.launch {
        noteRepository.insertNote(noteModel)
    }

    fun updateNote(noteModel : NoteModel) = viewModelScope.launch {
        noteRepository.updateNote(noteModel)
    }

    fun deleteNote(noteModel : NoteModel) = viewModelScope.launch {
        noteRepository.deleteNote(noteModel)
    }

    fun getAllNote() : LiveData<List<NoteModel>> = noteRepository.getAllNote()

    class NoteViewModelFactory(private val application : Application) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return NoteViewModel(application) as T
            }
            throw IllegalArgumentException("Unable construct viewModel")
        }
    }
}