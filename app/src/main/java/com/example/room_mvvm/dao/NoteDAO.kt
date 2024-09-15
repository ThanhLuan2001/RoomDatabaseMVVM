package com.example.room_mvvm.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.room_mvvm.model.NoteModel

@Dao
interface NoteDAO {

    @Insert
    suspend fun insertNote(noteModel: NoteModel)

    @Update
    suspend fun updateNote(noteModel: NoteModel)

    @Delete
    suspend fun deleteNote(noteModel: NoteModel)

    @Query("SELECT * FROM note_table")
    fun getAllNote() : LiveData<List<NoteModel>>

    @Query("SELECT * FROM note_table WHERE title_col =:title")
    fun getNoteByTitle(title : String) : LiveData<List<NoteModel>>

}